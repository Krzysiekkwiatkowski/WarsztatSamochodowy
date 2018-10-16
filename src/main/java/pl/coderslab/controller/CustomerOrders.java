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

@WebServlet("/WarsztatSamochodowy/CustomerOrders")
public class CustomerOrders extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<ActiveOrder> orders = OrderDao.loadByCustomerId(id);
        request.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/CustomerOrders.jsp")
                .forward(request, response);
    }
}
