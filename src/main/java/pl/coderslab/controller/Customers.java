package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy/Customers")
public class Customers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        if(idString == null){
            Customer customer = new Customer(name, surname);
            CustomerDao customerDao = new CustomerDao();
            customerDao.createCustomer(customer);
        } else {
            int id = Integer.parseInt(idString);
            Customer customer = new Customer(name, surname);
            customer.setId(id);
            CustomerDao customerDao = new CustomerDao();
            customerDao.editCustomer(customer);
        }
        response.sendRedirect("/WarsztatSamochodowy/Customers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if(action == null){
            ArrayList<Customer> customers = CustomerDao.loadAll();
            request.setAttribute("customers", customers);
            getServletContext().getRequestDispatcher("/Customers.jsp")
                    .forward(request, response);
        } else if(action.equals("add")){
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Customers\" method=\"post\">\n" +
                    "    Name: <input type=\"text\" name=\"name\">\n" +
                    "    Surname: <input type=\"text\" name=\"surname\">\n" +
                    "    <input type=\"submit\" value=\"Dodaj\">\n" +
                    "</form>");
        } else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = CustomerDao.loadById(id);
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Customers\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                    "    Name: <input type=\"text\" name=\"name\" value=\"" + customer.getName() + "\">\n" +
                    "    Surname: <input type=\"text\" name=\"surname\" value=\"" + customer.getSurname() + "\">\n" +
                    "    <input type=\"submit\" value=\"Edytuj\">\n" +
                    "</form>");
        } else if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            CustomerDao.deleteCustomer(id);
            response.sendRedirect("/WarsztatSamochodowy/Customers");
        }
    }
}
