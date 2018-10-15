package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {

    private static String CREATE_EMPLOYEE = "INSERT INTO employees(name, surname, address, phone, note, salary) VALUES(?,?,?,?,?,?)";
    private static String EDIT_EMPLOYEE = "UPDATE employees SET name = ?, surname = ?, address = ?, phone = ?, note = ?, salary = ? WHERE id = ?";
    private static String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";
    private static String LOAD_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static String LOAD_ALL_EMPLOYEES = "SELECT * FROM employees";

    public Employee createEmployee(Employee employee){
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EMPLOYEE, generatedColumns);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setInt(4, employee.getPhone());
            if(employee.getNote() != null){
                preparedStatement.setString(5, employee.getNote());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                employee.setId(resultSet.getInt(1));
            }
            return employee;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void editEmployee(Employee employee){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_EMPLOYEE);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setInt(4, employee.getPhone());
            if(employee.getNote() != null){
                preparedStatement.setString(5, employee.getNote());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }
            preparedStatement.setDouble(6, employee.getSalary());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Employee loadById(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhone(resultSet.getInt("phone"));
                if(resultSet.getString("note") != null){
                    employee.setNote(resultSet.getString("note"));
                }
                employee.setSalary(resultSet.getDouble("salary"));
                return employee;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Employee> loadAll(){
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_EMPLOYEES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhone(resultSet.getInt("phone"));
                if(resultSet.getString("note") != null){
                    employee.setNote(resultSet.getString("note"));
                }
                employee.setSalary(resultSet.getDouble("salary"));
                employees.add(employee);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return employees;
    }
}
