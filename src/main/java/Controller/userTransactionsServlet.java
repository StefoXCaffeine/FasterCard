package Controller;

import java.io.*;

import Model.Utente;
import Utils.PasswordEncrypt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Movimento;
import Model.MovimentoServices;
import org.json.JSONObject;

@WebServlet(name = "userTransactionsServlet", value = "/userTransactionsServlet")
public class userTransactionsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Servlet che restituisce la lista dei movimenti effettuati sulle carte dell'utente
        //L'ID dell'utente viene preso dalla sessione corrente e utilizzato per ottenere tutti i movimenti
        HttpSession session = request.getSession(false);
        Utente user = (Utente) session.getAttribute("currentUser");
        int id = user.getId();
        request.setCharacterEncoding("UTF-8");
        MovimentoServices movServ = new MovimentoServices();
        List<Movimento> movs;
        try {
            movs =  movServ.getTransactions(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("movements", movs); //Salvataggio della lista movimenti nella richiesta
        getServletContext().getRequestDispatcher("/View/reportPage.jsp").forward(request, response);
    }
}