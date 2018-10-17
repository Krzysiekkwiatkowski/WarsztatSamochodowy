package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.ActiveOrder;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy/Orders")
public class Orders extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String stringId= request.getParameter("id");
        String stringReceived = request.getParameter("received");
        String stringPlanned = request.getParameter("planned");
        String stringStarted = request.getParameter("started");
        String problem = request.getParameter("problem");
        String repair = request.getParameter("repair");
        String stringStatusId = request.getParameter("status_id");
        String stringVehicleId = request.getParameter("vehicle_id");
        Date received = null;
        Date planned = null;
        Date started = null;
        if(!stringReceived.equals("")){
            received = Date.valueOf(stringReceived);
        }
        if(!stringPlanned.equals("")){
            planned = Date.valueOf(stringPlanned);
        }
        if(!stringStarted.equals("")){
            started = Date.valueOf(stringStarted);
        }
        int employee_id = 0;
        int status_id = 0;
        int vehicle_id = 0;
        String stringEmployeeId = request.getParameter("employee_id");
        if(!stringEmployeeId.equals("")){
            employee_id = Integer.parseInt(stringEmployeeId);
        }
        if(!stringStatusId.equals("")){
            status_id = Integer.parseInt(stringStatusId);
        }
        if(!stringVehicleId.equals("")){
            vehicle_id = Integer.parseInt(stringVehicleId);
        }
        double cost = 0.0;
        double parts = 0.0;
        double employeeSalary = 0.0;
        double time = 0.0;
        String stringCost = request.getParameter("cost");
        String stringParts = request.getParameter("parts");
        String stringEmployeeSalary = request.getParameter("employeeSalary");
        String stringTime = request.getParameter("time");
        if(!stringCost.equals("")){
            cost = Double.parseDouble(stringCost);
        }
        if(!stringParts.equals("")){
            parts = Double.parseDouble(stringParts);
        }
        if(!stringEmployeeSalary.equals("")){
            employeeSalary = Double.parseDouble(stringEmployeeSalary);
        }
        if(!stringTime.equals("")){
            time = Double.parseDouble(stringTime);
        }
        if(stringId == null){
            Order order = new Order(received, planned, started, employee_id, problem, repair, status_id, vehicle_id, cost, parts, employeeSalary, time);
            OrderDao orderDao = new OrderDao();
            orderDao.createOrder(order);
        } else {
            int id = Integer.parseInt(stringId);
            Order order = new Order(received, planned, started, employee_id, problem, repair, status_id, vehicle_id, cost, parts, employeeSalary, time);
            order.setId(id);
            OrderDao orderDao = new OrderDao();
            orderDao.editOrder(order);
        }
        response.sendRedirect("/WarsztatSamochodowy/Orders");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if(action == null) {
            ArrayList<ActiveOrder> orders = OrderDao.loadBasicOrders();
            request.setAttribute("orders", orders);
            getServletContext().getRequestDispatcher("/Orders.jsp")
                    .forward(request, response);
        } else if(action.equals("add")){
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Orders\" method=\"post\">\n" +
                    "    Received: <input type=\"text\" name=\"received\"></br>\n" +
                    "    Planned: <input type=\"text\" name=\"planned\"></br>\n" +
                    "    Started: <input type=\"text\" name=\"started\"></br>\n" +
                    "    Employee's id: <input type=\"number\" name=\"employee_id\"></br>\n" +
                    "    Problem: <input type=\"text\" name=\"problem\"></br>\n" +
                    "    Repair: <input type=\"text\" name=\"repair\"></br>\n" +
                    "    Status id: <input type=\"number\" name=\"status_id\"></br>\n" +
                    "    Vehicle id: <input type=\"number\" name=\"vehicle_id\"></br>\n" +
                    "    Cost: <input type=\"number\" min=\"0.01\" step=\"0.01\" name=\"cost\"></br>\n" +
                    "    Parts: <input type=\"number\" min=\"0.01\" step=\"0.01\" name=\"parts\"></br>\n" +
                    "    Empoyee's salary: <input type=\"number\" min=\"0.01\" step=\"0.01\" name=\"employeeSalary\"></br>\n" +
                    "    Time: <input type=\"number\" min=\"0.5\" step=\"0.5\" name=\"time\"></br>\n" +
                    "    <input type=\"submit\" value=\"Dodaj\">\n" +
                    "</form>");
        } else if(action.equals("edit")){
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = OrderDao.loadById(id);
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Orders\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                    "    Received: <input type=\"date\" name=\"received\" value=\"" + order.getReceived() + "\"></br>\n" +
                    "    Planned: <input type=\"date\" name=\"planned\" value=\"" + order.getPlanned() + "\"></br>\n" +
                    "    Started: <input type=\"date\" name=\"started\" value=\"" + order.getStarted() + "\"></br>\n" +
                    "    Employee's id: <input type=\"number\" name=\"employee_id\" value=\"" + order.getEmployee_id() + "\"></br>\n" +
                    "    Problem: <input type=\"text\" name=\"problem\" value=\"" + order.getProblem() + "\"></br>\n" +
                    "    Repair: <input type=\"text\" name=\"repair\" value=\"" + order.getRepair() + "\"></br>\n" +
                    "    Status id: <input type=\"number\" name=\"status_id\" value=\"" + order.getStatus_id() + "\"></br>\n" +
                    "    Vehicle id: <input type=\"number\" name=\"vehicle_id\" value=\"" + order.getVehicle_id() + "\"></br>\n" +
                    "    Cost: <input type=\"number\" step=\"0.01\" name=\"cost\" value=\"" + order.getCost() + "\"></br>\n" +
                    "    Parts: <input type=\"number\" step=\"0.01\" name=\"parts\" value=\"" + order.getParts() + "\"></br>\n" +
                    "    Empoyee's salary: <input type=\"number\" step=\"0.01\" name=\"employeeSalary\" value=\"" + order.getEmployeeSalary() + "\"></br>\n" +
                    "    Time: <input type=\"number\" step=\"0.5\" name=\"time\" value=\"" + order.getTime() + "\"></br>\n" +
                    "    <input type=\"submit\" value=\"Edytuj\">\n" +
                    "</form>");
        } else if(action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            OrderDao.deleteOrder(id);
            response.sendRedirect("/WarsztatSamochodowy/Orders");
        } else if(action.equals("details")){
            int id = Integer.parseInt(request.getParameter("id"));
            Order order = OrderDao.loadById(id);
            request.setAttribute("order", order);
            getServletContext().getRequestDispatcher("/Details.jsp")
                    .forward(request, response);
        }
    }
}
