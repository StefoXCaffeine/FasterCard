package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.CartaServices;
import Model.UtenteServices;
import Utils.CardNumberGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;
import java.time.LocalDate;

@WebServlet(name = "CreateCardServlet", value = "/CreateCardServlet")
public class CreateCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject Jlocation = new JSONObject();
        CartaServices cartaServ = new CartaServices();
        UtenteServices utenteServ = new UtenteServices();
        CardNumberGenerator cardGen = new CardNumberGenerator();
        String email = request.getParameter("email");
        float value= Float.parseFloat(request.getParameter("valore"));
        String message;
        int id;
        try {
            id = utenteServ.getuserIDByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(id!=0) {
            String numCarta = cardGen.generateCardNumber();
            String cvv = cardGen.generateCvv();
            LocalDate createDate = LocalDate.now();
            LocalDate expireDate = createDate.plusYears(5);
            request.setCharacterEncoding("UTF-8");

            try {
                cartaServ.createCard(numCarta, id, value, createDate, expireDate, cvv);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            message = "Carta registrata!";
        }
        else{
            message = "Errore! Carta non registrata ";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/cardCreatedPage.jsp").forward(request, response);
    }
}