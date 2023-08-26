package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import Model.MovimentoServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import java.time.LocalDate;

@WebServlet(name = "UpdateBalanceServlet", value = "/UpdateBalanceServlet")
public class UpdateBalanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartaServices cartaServ = new CartaServices();
        MovimentoServices movServ = new MovimentoServices();
        request.setCharacterEncoding("UTF-8");
        String numCarta = request.getParameter("cardNumber");
        String operazione = request.getParameter("operation");
        float value = Float.parseFloat(request.getParameter("valore"));
        LocalDate actualDate = LocalDate.now();
        String message="";
        boolean exists = false; //True se la carta esiste, false altrimenti
        try {
            exists = cartaServ.cardExists(numCarta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(exists) {
            boolean transaction = false;        //True se la transazione è stata registrata nel DB, false altrimenti
            boolean op = false;                 //True se l'operazione è un bonifico, false per addebito
            boolean cardStatus;                 //True se la carta è attiva, false se è bloccata
            String newCredit;                   //Credito della carta dopo l'operazione
            float actualCredit = 0;             //Credito attuale carta
            if (operazione.equals("Bonifico")) {
                op = true;
            }

            try {
                actualCredit = Float.parseFloat(cartaServ.checkCredit(numCarta));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                cardStatus = cartaServ.getCardStatus(numCarta);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (!cardStatus) {
                message = "Errore! La carta è bloccata. Contattare un amministratore per sbloccarla ";
            } else if (!op && actualCredit < value) {
                message = "Attenzione! Il valore dell'addebito è superiore al credito attuale, movimento non effettuato";
            } else if (op) {
                try {
                    transaction = movServ.registerTransaction(actualDate, value, numCarta);
                    newCredit = cartaServ.updateBalance(numCarta, value, op);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                message = "Operazione di accredito eseguita con successo, nuovo credito:" + newCredit + "$";
            } else if (!op) {
                try {
                    transaction = movServ.registerTransaction(actualDate, -value, numCarta);
                    newCredit = cartaServ.updateBalance(numCarta, value, op);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "Operazione di addebito eseguita con successo, nuovo credito:" + newCredit + "$";
            }
        } else{
            message = "La carta inserita non esiste";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/cardStatusCheckedPage.jsp").forward(request, response);
    }
}