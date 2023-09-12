package Controller;

import java.io.*;
import java.sql.SQLException;

import Model.UtenteServices;
import Model.Utente;
import Utils.PasswordEncrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Se viene effettuata una richiesta GET alla servlet di login, si viene riportati all'index
        getServletContext().getRequestDispatcher("/View/errorPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che gestisce il login di un utente, se mail e password corrispondono viene creata la sessione a cui viene legato l'oggetto utente
        UtenteServices utenteServ = new UtenteServices();
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        PasswordEncrypt encrypt = new PasswordEncrypt();
        String encryptedPassword = encrypt.hashing(password); //SHA-256 dell'hash MD5 della password immessa nel form
        Utente user = new Utente();
        try {
            user = utenteServ.getUser(email, encryptedPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.getNome() == null){//Email o password errati, redirect al form di login con alert di errore
            request.setAttribute("userLogged", false);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else{//Email e password corretti
            int userType = user.getUserType();
            if(userType==1){//Controlla se l'utente è un negoziante
                int merchBlock = user.getNegBlock();
                if(merchBlock == 0){ //Negoziante bloccato, ritorna all'index con alert di errore
                    request.setAttribute("merchantBlock", true);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
            }

            //Utente loggato correttamente, creazione della sessione con oggetto utente
            HttpSession session = request.getSession();
            //session.setMaxInactiveInterval(60*5);
            session.setAttribute("currentUser", user);

            //Switch che effettua il redirect dell'utente verso la pagina personale corretta secondo la tipologia di utente
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