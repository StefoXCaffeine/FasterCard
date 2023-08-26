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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/View/loginPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject Jlocation = new JSONObject();
        UtenteServices utenteServ = new UtenteServices();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PasswordEncrypt encrypt = new PasswordEncrypt();
        String encryptedPassword = encrypt.hashing(password);
        Utente user = new Utente();
        try {
            user = utenteServ.getUser(email, encryptedPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.getNome() == null){
            Jlocation.put("success", false);
            Jlocation.put("message","email o password errati");
            String location = Jlocation.toString();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(location);
        }
        else{
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60*5);
            session.setAttribute("currentUser", user);
            Jlocation.put("success", true);
            Jlocation.put("message", "Login effettuato con successo, benvenuto "+user.getNome());
            Jlocation.put("SessionID", session.getId());
            String location = Jlocation.toString();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(location);
            switch (user.getUserType()){
                case 0:
                    response.sendRedirect("View/adminPage.jsp");
                    break;

                case 1:
                    response.sendRedirect("View/merchantPage.jsp");
                    break;

                case 2:
                    response.sendRedirect("View/userPage.jsp");
                    break;

                default:
                    response.sendRedirect("index.jsp");
            }
        }
    }
}