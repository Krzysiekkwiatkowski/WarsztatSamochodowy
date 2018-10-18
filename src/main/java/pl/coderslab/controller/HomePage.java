package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.ActiveOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy")
public class HomePage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        ArrayList<ActiveOrder> activeOrders = OrderDao.loadAllActive();
        request.setAttribute("activeOrders", activeOrders);
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }
}
