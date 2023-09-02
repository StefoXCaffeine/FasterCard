package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CheckCreditServlet", value = "/CheckCreditServlet")
public class CheckCreditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che controlla il credito di una carta. Se la carta è bloccata oppure non esiste, non è poss
        CartaServices cartaServ = new CartaServices();
        request.setCharacterEncoding("UTF-8");
        String numCarta = request.getParameter("cardNumber");
        String message;
        boolean cardExists; //flag che indica se la carta esiste o no
        try {
            cardExists=cartaServ.cardExists(numCarta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(cardExists) { //La carta esiste
            boolean cardStatus;
            try {
                cardStatus=cartaServ.getCardStatus(numCarta);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (!cardStatus) { //Carta bloccata, impossibile controllare credito
                message = "La carta è bloccata, contattare un amministratore per sbloccarla.";
            }else{ //Credito disponibile
                String credit = null;
                try {
                    credit = cartaServ.checkCredit(numCarta);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                message=credit+"$";
            }
        }else{ //Carta inesistente, impossibile controllare credito
            message = "Errore! La carta non esiste.";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/creditCheckedPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}