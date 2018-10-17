package pl.coderslab.controller;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy/CustomerVehicles")
public class CustomerVehicles extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        String registration = request.getParameter("registration");
        String stringInspection = request.getParameter("inspection");
        Date inspection = null;
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        String stringId = request.getParameter("id");
        if(stringId == null){
            Vehicle vehicle = new Vehicle(brand, model, year, registration, inspection, customer_id);
            if(!stringInspection.equals("")){
                inspection = Date.valueOf(stringInspection);
            }
            vehicle.setInspection(inspection);
            VehicleDao vehicleDao = new VehicleDao();
            vehicleDao.createVehicle(vehicle);
        } else {
            int id = Integer.parseInt(stringId);
            Vehicle vehicle = VehicleDao.loadById(id);
            if(stringInspection.equals("")){
                inspection = null;
            } else {
                inspection = Date.valueOf(stringInspection);
            }
            vehicle.setBrand(brand);
            vehicle.setModel(model);
            vehicle.setYear(year);
            vehicle.setRegistration(registration);
            vehicle.setInspection(inspection);
            VehicleDao vehicleDao = new VehicleDao();
            vehicleDao.editVehicle(vehicle);
        }
        response.sendRedirect("/WarsztatSamochodowy/CustomerVehicles?id=" + customer_id);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        if(action == null) {
            ArrayList<Vehicle> vehicles = VehicleDao.loadByCustomerId(id);
            request.setAttribute("id", id);
            request.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/CustomerVehicles.jsp")
                    .forward(request, response);
        } else if(action.equals("add")) {
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/CustomerVehicles\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"customer_id\" value=\"" + id + "\">\n" +
                    "    Brand: <input type=\"text\" name=\"brand\"></br>\n" +
                    "    Model: <input type=\"text\" name=\"model\"></br>\n" +
                    "    Year: <input type=\"number\" name=\"year\"></br>\n" +
                    "    Registration: <input type=\"text\" name=\"registration\"></br>\n" +
                    "    Inspection: <input type=\"date\" name=\"inspection\"></br>\n" +
                    "    <input type=\"submit\" value=\"Dodaj\">\n" +
                    "</form>");
        } else if(action.equals("edit")) {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            Vehicle vehicle = VehicleDao.loadById(vehicleId);
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/CustomerVehicles\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\"" + vehicleId + "\">\n" +
                    "    <input type=\"hidden\" name=\"customer_id\" value=\"" + id + "\">\n" +
                    "    Brand: <input type=\"text\" name=\"brand\" value=\"" + vehicle.getBrand() + "\"></br>\n" +
                    "    Model: <input type=\"text\" name=\"model\" value=\"" + vehicle.getModel() + "\"></br>\n" +
                    "    Year: <input type=\"number\" name=\"year\" value=\"" + vehicle.getYear() + "\"></br>\n" +
                    "    Registration: <input type=\"text\" name=\"registration\" value=\"" + vehicle.getRegistration() + "\"></br>\n" +
                    "    Inspection: <input type=\"date\" name=\"inspection\" value=\"" + vehicle.getInspection() + "\"></br>\n" +
                    "    <input type=\"submit\" value=\"Edytuj\">\n" +
                    "</form>");
        } else if(action.equals("delete")) {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            VehicleDao.deleteVehicle(vehicleId);
            response.sendRedirect("/WarsztatSamochodowy/CustomerVehicles?id=" + id);
        }
    }
}
