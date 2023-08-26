package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.UtenteServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet(name = "MerchantStatusServlet", value = "/MerchantStatusServlet")
public class MerchantStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject Jlocation = new JSONObject();
        UtenteServices utenteServ = new UtenteServices();
        String email = request.getParameter("email");
        String operation = request.getParameter("operation");
        String message="";
        boolean status = false;
        boolean merchantExists = false;
        try {
            status = utenteServ.getMerchantStatus(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            merchantExists = utenteServ.alreadyRegistered(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(!merchantExists){
            message = "Errore, il negoziante non esiste";
        }else if(operation.equals("Blocca")){
            if(!status){
                message = "Errore, il negoziante è già bloccato";
            }
            else{
                try {
                    utenteServ.updateMerchantStatus(email, false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "Il negoziante è stato bloccato!";
            }
        }else if(operation.equals("Sblocca")){
            if(status){
                message = "Errore, il negoziante è già sbloccato";
            }
            else{
                try {
                    utenteServ.updateMerchantStatus(email, true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "Il negoziante è stato sbloccato!";
            }
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/View/merchantStatusCheckedPage.jsp").forward(request, response);
    }
}