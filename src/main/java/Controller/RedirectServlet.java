package Controller;

import java.io.*;

import Model.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RedirectServlet", value = "/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente)session.getAttribute("currentUser");
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}