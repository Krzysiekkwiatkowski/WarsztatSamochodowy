package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao {

    private static String CREATE_CUSTOMER = "INSERT INTO customers(name, surname) VALUES(?,?)";
    private static String EDIT_CUSTOMER = "UPDATE customers SET name = ?, surname = ? WHERE id = ?";
    private static String DELETE_CUSTOMER = "DELETE FROM customers WHERE id = ?";
    private static String LOAD_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private static String LOAD_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static String LOAD_ALL_CUSTOMERS_MATCH = "SELECT * FROM customers WHERE surname LIKE ?";

    public Customer createCustomer(Customer customer){
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CUSTOMER, generatedColumns);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                customer.setId(resultSet.getInt(1));
            }
            return customer;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void editCustomer(Customer customer){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setInt(3, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Customer loadById(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                return customer;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Customer> loadAll(){
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_CUSTOMERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customers.add(customer);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    public static ArrayList<Customer> loadAllMatch(String surname){
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_CUSTOMERS_MATCH);
            preparedStatement.setString(1, "%" + surname + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customers.add(customer);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }
}
