package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CardStatusServlet", value = "/CardStatusServlet")
public class CardStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/View/errorPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che controlla e aggiorna lo stato della carta selezionata bloccandola o sbloccandola
        CartaServices cartaServ = new CartaServices();
        String numCarta = request.getParameter("cardNumber");
        String operation = request.getParameter("operation");
        String message = "";
        boolean status = false; //flag che indica se la carta è bloccata o no
        boolean cardExists = false; //flag che indica se la carta esiste o no
        try {
            status = cartaServ.getCardStatus(numCarta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            cardExists = cartaServ.cardExists(numCarta);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!cardExists){ //Carta non esistente
            message = "Errore, la carta non esiste";
        }else if(operation.equals("Blocca")){ //Operazione selezionata "Blocca"
            if(!status){ //Carta già bloccata
                message = "Errore, la carta è già bloccata";
            }else{ //Carta bloccata correttamente
                try {
                    cartaServ.updateCardStatus(numCarta, false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "La carta è stata bloccata!";
            }
        }else if(operation.equals("Sblocca")){//Operazione selezionata "Sblocca"
            if(status){//Carta già sbloccata
                message = "Errore, la carta è già sbloccata";
            }else{//Carta sbloccata correttamente
                try {
                    cartaServ.updateCardStatus(numCarta, true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "La carta è stata sbloccata!";
            }
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/cardStatusCheckedPage.jsp").forward(request, response);
    }
}