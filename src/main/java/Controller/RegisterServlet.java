package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.UtenteServices;
import Model.Utente;
import Utils.PasswordEncrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/View/registerPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteServices utenteServ = new UtenteServices();
        JSONObject Jlocation = new JSONObject();
        PasswordEncrypt encrypt = new PasswordEncrypt();

        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int userType = Integer.parseInt(request.getParameter("tipoUtente"));

        boolean exists = false;
        try {
            exists = utenteServ.alreadyRegistered(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(exists){ //Mail già esistente
            Jlocation.put("success", false);
            Jlocation.put("message", "Esiste già un utente associato a questa mail");
            String location = Jlocation.toString();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(location);
        }
        else{ //Registrazione corretta
            String encryptedPassword = encrypt.hashing(password);
            boolean insert=false;
            try {
                insert = utenteServ.registerUser(nome, cognome, email, encryptedPassword, userType);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(insert){ //Messaggio utente registrato
                Utente user = null;
                try {
                    user = utenteServ.getUser(email, encryptedPassword);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                Jlocation.put("success", true);
                Jlocation.put("message", "utente registrato correttamente");
                Jlocation.put("user", user);
                String location = Jlocation.toString();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(location);
            }
        }

    }
}