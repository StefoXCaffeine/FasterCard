package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet(name = "CardStatusServlet", value = "/CardStatusServlet")
public class CardStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject Jlocation = new JSONObject();
        CartaServices cartaServ = new CartaServices();
        String numCarta = request.getParameter("cardNumber");
        String operation = request.getParameter("operation");
        String message = "";
        boolean status = false;
        boolean cardExists = false;
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
        if(!cardExists){
            message = "Errore, la carta non esiste";
        }else if(operation.equals("Blocca")){
            if(!status){
                message = "Errore, la carta è già bloccata";
            }
            else{
                try {
                    cartaServ.updateCardStatus(numCarta, false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "La carta è stata bloccata!";
            }
        }else if(operation.equals("Sblocca")){
            if(status){
                message = "Errore, la carta è già sbloccata";
            }
            else{
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