package Controller;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LogoutServlet", value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che gestisce il logout
        HttpSession session = request.getSession(false); //Viene richiesta la sessione solamente se esiste già
        if(session != null) {//Se la sessione esiste vengono eliminati gli attributi e viene invalidata
            session.removeAttribute("currentUser");
            session.invalidate();
        }
        //Redirect all'index
        response.sendRedirect("/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/View/errorPage.jsp").forward(request, response);
    }
}