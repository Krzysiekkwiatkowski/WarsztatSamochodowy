package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao {

    private static String CREATE_ORDER = "INSERT INTO orders(received, planned, started, employee_id, problem, repair, status_id, vehicle_id, cost, parts, employee_salary, time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String EDIT_ORDER = "UPDATE orders SET received = ?, planned = ?, started = ?, employee_id = ?, problem = ?, repair = ?, status_id = ?, vehicle_id = ?, cost = ?, parts = ?, employee_salary = ?, time = ? WHERE id = ?";
    private static String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static String LOAD_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static String LOAD_ALL_ORDERS = "SELECT * FROM orders";

    public Order createOrder(Order order) {
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER, generatedColumns);
            preparedStatement.setDate(1, order.getReceived());
            preparedStatement.setDate(2, order.getPlanned());
            if(order.getStarted() != null) {
                preparedStatement.setDate(3, order.getStarted());
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            if(order.getEmployee_id() != 0) {
                preparedStatement.setInt(4, order.getEmployee_id());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            if(order.getProblem() != null) {
                preparedStatement.setString(5, order.getProblem());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }
            if(order.getRepair() != null) {
                preparedStatement.setString(6, order.getRepair());
            } else {
                preparedStatement.setNull(6, Types.VARCHAR);
            }
            preparedStatement.setInt(7, order.getStatus_id());
            preparedStatement.setInt(8, order.getVehicle_id());
            if(order.getCost() != 0.0) {
                preparedStatement.setDouble(9, order.getCost());
            } else {
                preparedStatement.setNull(9, Types.DECIMAL);
            }
            if(order.getParts() != 0.0) {
                preparedStatement.setDouble(10, order.getParts());
            } else {
                preparedStatement.setNull(10, Types.DECIMAL);
            }
            if(order.getEmployeeSalary() != 0.0) {
                preparedStatement.setDouble(11, order.getEmployeeSalary());
            } else {
                preparedStatement.setNull(11, Types.DECIMAL);
            }
            if(order.getTime() != 0.0) {
                preparedStatement.setDouble(12, order.getTime());
            } else {
                preparedStatement.setNull(12, Types.DECIMAL);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editOrder(Order order) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_ORDER);
            preparedStatement.setDate(1, order.getReceived());
            preparedStatement.setDate(2, order.getPlanned());
            if(order.getStarted() != null) {
                preparedStatement.setDate(3, order.getStarted());
            } else {
                preparedStatement.setNull(3, Types.DATE);
            }
            if(order.getEmployee_id() != 0) {
                preparedStatement.setInt(4, order.getEmployee_id());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            if(order.getProblem() != null) {
                preparedStatement.setString(5, order.getProblem());
            } else {
                preparedStatement.setNull(5, Types.VARCHAR);
            }
            if(order.getRepair() != null) {
                preparedStatement.setString(6, order.getRepair());
            } else {
                preparedStatement.setNull(6, Types.VARCHAR);
            }
            preparedStatement.setInt(7, order.getStatus_id());
            preparedStatement.setInt(8, order.getVehicle_id());
            if(order.getCost() != 0.0) {
                preparedStatement.setDouble(9, order.getCost());
            } else {
                preparedStatement.setNull(9, Types.DECIMAL);
            }
            if(order.getParts() != 0.0) {
                preparedStatement.setDouble(10, order.getParts());
            } else {
                preparedStatement.setNull(10, Types.DECIMAL);
            }
            if(order.getEmployeeSalary() != 0.0) {
                preparedStatement.setDouble(11, order.getEmployeeSalary());
            } else {
                preparedStatement.setNull(11, Types.DECIMAL);
            }
            if(order.getTime() != 0.0) {
                preparedStatement.setDouble(12, order.getTime());
            } else {
                preparedStatement.setNull(12, Types.DECIMAL);
            }
            preparedStatement.setInt(13, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteOrder(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Order loadById(int id) {
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ORDER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setReceived(resultSet.getDate("received"));
                order.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    order.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getInt("employee_id") != 0) {
                    order.setEmployee_id(resultSet.getInt("employee_id"));
                }
                if(resultSet.getString("problem") != null) {
                    order.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    order.setRepair(resultSet.getString("repair"));
                }
                order.setStatus_id(resultSet.getInt("status_id"));
                order.setVehicle_id(resultSet.getInt("vehicle_id"));
                if (resultSet.getDouble("cost") != 0.0) {
                    order.setCost(resultSet.getDouble("cost"));
                }
                if (resultSet.getDouble("parts") != 0.0) {
                    order.setCost(resultSet.getDouble("parts"));
                }
                if (resultSet.getDouble("employee_salary") != 0.0) {
                    order.setCost(resultSet.getDouble("employee_salary"));
                }
                if (resultSet.getDouble("time") != 0.0) {
                    order.setCost(resultSet.getDouble("time"));
                }
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Order> loadAll() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setReceived(resultSet.getDate("received"));
                order.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    order.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getInt("employee_id") != 0) {
                    order.setEmployee_id(resultSet.getInt("employee_id"));
                }
                if(resultSet.getString("problem") != null) {
                    order.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    order.setRepair(resultSet.getString("repair"));
                }
                order.setStatus_id(resultSet.getInt("status_id"));
                order.setVehicle_id(resultSet.getInt("vehicle_id"));
                if (resultSet.getDouble("cost") != 0.0) {
                    order.setCost(resultSet.getDouble("cost"));
                }
                if (resultSet.getDouble("parts") != 0.0) {
                    order.setCost(resultSet.getDouble("parts"));
                }
                if (resultSet.getDouble("employee_salary") != 0.0) {
                    order.setCost(resultSet.getDouble("employee_salary"));
                }
                if (resultSet.getDouble("time") != 0.0) {
                    order.setCost(resultSet.getDouble("time"));
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
