package pl.coderslab.controller;

import pl.coderslab.dao.RaportDao;
import pl.coderslab.model.BenefitRaport;
import pl.coderslab.model.WorkRaport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/WarsztatSamochodowy/Raports")
public class Raports extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UtF-8");
        String type = request.getParameter("type");
        if(type == null) {
            getServletContext().getRequestDispatcher("/Raports.jsp")
                    .forward(request, response);
        } else {
            if (type.equals("working")) {
                Date from = Date.valueOf(request.getParameter("from"));
                Date to = Date.valueOf(request.getParameter("to"));
                ArrayList<WorkRaport> workRaport = RaportDao.getWorkRaport(from, to);
                request.setAttribute("workRaport", workRaport);
                request.setAttribute("raport", "raport");
                getServletContext().getRequestDispatcher("/Raports.jsp")
                        .forward(request, response);
            } else if (type.equals("benefits")) {
                Date from = Date.valueOf(request.getParameter("from"));
                Date to = Date.valueOf(request.getParameter("to"));
                BenefitRaport benefitRaport = RaportDao.getBenefitRaport(from, to);
                request.setAttribute("raport", "raport");
                request.setAttribute("benefitRaport", benefitRaport);
                getServletContext().getRequestDispatcher("/Raports.jsp")
                        .forward(request, response);
            }
        }
    }
}
