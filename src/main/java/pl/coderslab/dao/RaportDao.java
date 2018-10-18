package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.BenefitRaport;
import pl.coderslab.model.WorkRaport;

import java.sql.*;
import java.util.ArrayList;

public class RaportDao {

    private static String GENERETE_WORK_RAPORT = "SELECT employees.name, employees.surname, SUM(orders.time) AS sum FROM employees JOIN orders ON employees.id = orders.employee_id WHERE orders.started > ? AND orders.started < ? GROUP BY employees.id";
    private static String GENERETE_BENEFIT_RAPORT = "SELECT cost, parts, employee_salary, time FROM orders WHERE started > ? AND started < ?";

    public static ArrayList<WorkRaport> getWorkRaport(Date from, Date to){
        ArrayList<WorkRaport> workRaport = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(GENERETE_WORK_RAPORT);
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                WorkRaport raport = new WorkRaport();
                raport.setEmployee(resultSet.getString("name") + " " + resultSet.getString("surname"));
                raport.setWorkedTime(resultSet.getDouble("sum"));
                workRaport.add(raport);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return workRaport;
    }

    public static BenefitRaport getBenefitRaport(Date from, Date to){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(GENERETE_BENEFIT_RAPORT);
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            ResultSet resultSet = preparedStatement.executeQuery();
            double costSum = 0.0;
            double partsSum = 0.0;
            double employee_salarySum = 0.0;
            double timeSum = 0.0;
            double benefitSum = 0.0;
            while (resultSet.next()){
                double cost = resultSet.getDouble("cost");
                double parts = resultSet.getDouble("parts");
                double time = resultSet.getDouble("time");
                double employee_salary = resultSet.getDouble("employee_salary");
                costSum += cost;
                partsSum += parts;
                employee_salarySum += employee_salary * time;
                timeSum += time;
                double benefit = cost - parts - employee_salary * time;
                benefitSum += benefit;
            }
            BenefitRaport benefitRaport = new BenefitRaport();
            benefitRaport.setCost(costSum);
            benefitRaport.setParts(partsSum);
            benefitRaport.setEmployee_salary(employee_salarySum);
            benefitRaport.setTime(timeSum);
            benefitRaport.setBenefit(benefitSum);
            return benefitRaport;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
