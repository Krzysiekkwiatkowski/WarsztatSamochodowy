package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy/Employees")
public class Employees extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String idString = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String note = request.getParameter("note");
        double salary = Double.parseDouble(request.getParameter("salary"));
        if (idString == null) {
            if (!note.equals("")) {
                Employee employee = new Employee(name, surname, address, phone, note, salary);
                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.createEmployee(employee);
            } else {
                Employee employee = new Employee(name, surname, address, phone, salary);
                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.createEmployee(employee);
            }
        } else {
            int id = Integer.parseInt(idString);
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = new Employee(name, surname, address, phone, salary);
            if (note.equals("null")) {
                note = null;
            }
            employee.setNote(note);
            employee.setId(id);
            employeeDao.editEmployee(employee);
        }
        response.sendRedirect("/WarsztatSamochodowy/Employees");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String action = request.getParameter("action");
        if (action == null) {
            ArrayList<Employee> employees = EmployeeDao.loadAll();
            request.setAttribute("employees", employees);
            getServletContext().getRequestDispatcher("/Employees.jsp")
                    .forward(request, response);
        } else if (action.equals("add")) {
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Employees\" method=\"post\">\n" +
                    "    Name: <input type=\"text\" name=\"name\"></br>\n" +
                    "    Surname: <input type=\"text\" name=\"surname\"></br>\n" +
                    "    Address: <input type=\"text\" name=\"address\"></br>\n" +
                    "    Phone: <input type=\"number\" name=\"phone\"></br>\n" +
                    "    Note: <input type=\"text\" name=\"note\"></br>\n" +
                    "    Salary: <input type=\"number\" min=\"0.01\" step=\"0.01\" name=\"salary\"></br>\n" +
                    "    <input type=\"submit\" value=\"Dodaj\">\n" +
                    "</form>");
        } else if (action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Employee employee = EmployeeDao.loadById(id);
            response.getWriter().append("<form action=\"/WarsztatSamochodowy/Employees\" method=\"post\">\n" +
                    "    <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
                    "    Name: <input type=\"text\" name=\"name\" value=\"" + employee.getName() + "\"></br>\n" +
                    "    Surname: <input type=\"text\" name=\"surname\"  value=\"" + employee.getSurname() + "\"></br>\n" +
                    "    Address: <input type=\"text\" name=\"address\" value=\"" + employee.getAddress() + "\"></br>\n" +
                    "    Phone: <input type=\"number\" name=\"phone\" value=\"" + employee.getPhone() + "\"></br>\n" +
                    "    Note: <input type=\"text\" name=\"note\" value=\"" + employee.getNote() + "\"></br>\n" +
                    "    Salary: <input type=\"number\" min=\"0.01\" step=\"0.01\" name=\"salary\" value=\"" + employee.getSalary() + "\"></br>\n" +
                    "    <input type=\"submit\" value=\"Edytuj\">\n" +
                    "</form>");
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            EmployeeDao.deleteEmployee(id);
            response.sendRedirect("/WarsztatSamochodowy/Employees");
        }
    }
}
