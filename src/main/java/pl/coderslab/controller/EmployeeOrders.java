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

@WebServlet("/WarsztatSamochodowy/EmployeeOrders")
public class EmployeeOrders extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ArrayList<ActiveOrder> employeeOrders = OrderDao.loadByEmployeeId(id);
        request.setAttribute("employeeOrders", employeeOrders);
        getServletContext().getRequestDispatcher("/EmployeeOrders.jsp")
                .forward(request, response);
    }
}
