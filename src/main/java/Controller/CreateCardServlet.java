package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import Model.UtenteServices;
import Utils.CardNumberGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.time.LocalDate;

@WebServlet(name = "CreateCardServlet", value = "/CreateCardServlet")
public class CreateCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che crea una carta legata all'utente tramite mail e con un credito iniziale
        CartaServices cartaServ = new CartaServices();
        UtenteServices utenteServ = new UtenteServices();
        CardNumberGenerator cardGen = new CardNumberGenerator();
        String email = request.getParameter("email");
        float value= Float.parseFloat(request.getParameter("valore"));
        String message;
        int id; //ID dell'utente a cui è legata la carta, recuperato a partire dalla mail passata nel form dall'admin
        try {
            id = utenteServ.getuserIDByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id!=0) {//Se l'utente esiste viene registrata la carta
            String numCarta = cardGen.generateCardNumber(); //Numero della carta generato tramite regex
            String cvv = cardGen.generateCvv(); //Cvv(codice a 3 cifre) della carta generato tramite regex
            LocalDate createDate = LocalDate.now();
            LocalDate expireDate = createDate.plusYears(5);//La data di scadenza impostata 5 anni dopo la data di creazione
            request.setCharacterEncoding("UTF-8");

            try {
                cartaServ.createCard(numCarta, id, value, createDate, expireDate, cvv);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            message = "Carta registrata!";
        }
        else{//Se l'utente non esiste la carta non viene registrata
            message = "Errore! Carta non registrata ";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/cardCreatedPage.jsp").forward(request, response);
    }
}