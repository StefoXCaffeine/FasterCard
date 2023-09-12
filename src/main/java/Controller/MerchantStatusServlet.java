package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.UtenteServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "MerchantStatusServlet", value = "/MerchantStatusServlet")
public class MerchantStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che controlla lo stato del negoziante e lo aggiorna bloccandolo/sbloccandolo
        UtenteServices utenteServ = new UtenteServices();
        String email = request.getParameter("email");
        String operation = request.getParameter("operation");
        String message="";
        boolean status = false; //Flag che indica se il negoziante è bloccato o no
        boolean merchantExists = false; //Flag che indica se il negoziante esiste o no
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
        if(!merchantExists){//Il negoziante non esiste
            message = "Errore, il negoziante non esiste";
        }else if(operation.equals("Blocca")){//Il negoziante esiste, operazione "Blocca"
            if(!status){//Negoziante già bloccato
                message = "Errore, il negoziante è già bloccato";
            }
            else{//Negoziante bloccato correttamente
                try {
                    utenteServ.updateMerchantStatus(email, false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message = "Il negoziante è stato bloccato!";
            }
        }else if(operation.equals("Sblocca")){//Il negoziante esiste, operazione "Sblocca"
            if(status){//Negoziante già sbloccato
                message = "Errore, il negoziante è già sbloccato";
            }
            else{//Negoziante sbloccato correttamente
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