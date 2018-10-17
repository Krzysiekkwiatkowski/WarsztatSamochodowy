package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.ActiveOrder;
import pl.coderslab.model.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao {

    private static String CREATE_ORDER = "INSERT INTO orders(received, planned, started, employee_id, problem, repair, status_id, vehicle_id, cost, parts, employee_salary, time) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String EDIT_ORDER = "UPDATE orders SET received = ?, planned = ?, started = ?, employee_id = ?, problem = ?, repair = ?, status_id = ?, vehicle_id = ?, cost = ?, parts = ?, employee_salary = ?, time = ? WHERE id = ?";
    private static String DELETE_ORDER = "DELETE FROM orders WHERE id = ?";
    private static String LOAD_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    private static String LOAD_ORDERS_BY_EMPLOYEE_ID = "SELECT orders.id, orders.received, orders.planned, orders.started, orders.problem, orders.repair, status.status, vehicles.brand, vehicles.model FROM orders JOIN status ON orders.status_id=status.id JOIN vehicles ON orders.vehicle_id= vehicles.id WHERE orders.employee_id = ?";
    private static String LOAD_ORDERS_BY_CUSTOMER_ID = "SELECT orders.id, orders.received, orders.planned, orders.started, orders.problem, orders.repair, status.status, vehicles.brand, vehicles.model FROM orders JOIN status ON orders.status_id=status.id JOIN vehicles ON orders.vehicle_id= vehicles.id WHERE vehicles.customer_id = ?";
    private static String LOAD_ALL_ORDERS = "SELECT * FROM orders";
    private static String LOAD_ALL_ACTIVE_ORDERS = "SELECT orders.id, orders.received, orders.planned, orders.started, orders.problem, orders.repair, status.status, vehicles.brand, vehicles.model FROM orders JOIN status ON orders.status_id=status.id JOIN vehicles ON orders.vehicle_id= vehicles.id WHERE status_id < 4;";
    private static String LOAD_BASIC_ORDERS = "SELECT orders.id, orders.received, orders.planned, orders.started, orders.problem, orders.repair, status.status, vehicles.brand, vehicles.model from orders JOIN status ON orders.status_id=status.id JOIN vehicles ON orders.vehicle_id=vehicles.id ORDER BY id";
    private static String LOAD_HISTORY = "SELECT id, received, started, problem, repair, cost FROM orders WHERE status_id = 4 AND vehicle_id = ?";

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
                    order.setParts(resultSet.getDouble("parts"));
                }
                if (resultSet.getDouble("employee_salary") != 0.0) {
                    order.setEmployeeSalary(resultSet.getDouble("employee_salary"));
                }
                if (resultSet.getDouble("time") != 0.0) {
                    order.setTime(resultSet.getDouble("time"));
                }
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ActiveOrder> loadByEmployeeId(int id) {
        ArrayList<ActiveOrder> employeeOrders = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ORDERS_BY_EMPLOYEE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ActiveOrder employeeOrder = new ActiveOrder();
                employeeOrder.setId(resultSet.getInt("id"));
                employeeOrder.setReceived(resultSet.getDate("received"));
                employeeOrder.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    employeeOrder.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getString("problem") != null) {
                    employeeOrder.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    employeeOrder.setRepair(resultSet.getString("repair"));
                }
                employeeOrder.setStatus(resultSet.getString("status"));
                employeeOrder.setBrand(resultSet.getString("brand"));
                employeeOrder.setModel(resultSet.getString("model"));
                employeeOrders.add(employeeOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeOrders;
    }

    public static ArrayList<ActiveOrder> loadByCustomerId(int id) {
        ArrayList<ActiveOrder> customerOrders = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ORDERS_BY_CUSTOMER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ActiveOrder customerOrder = new ActiveOrder();
                customerOrder.setId(resultSet.getInt("id"));
                customerOrder.setReceived(resultSet.getDate("received"));
                customerOrder.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    customerOrder.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getString("problem") != null) {
                    customerOrder.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    customerOrder.setRepair(resultSet.getString("repair"));
                }
                customerOrder.setStatus(resultSet.getString("status"));
                customerOrder.setBrand(resultSet.getString("brand"));
                customerOrder.setModel(resultSet.getString("model"));
                customerOrders.add(customerOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerOrders;
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
                    order.setParts(resultSet.getDouble("parts"));
                }
                if (resultSet.getDouble("employee_salary") != 0.0) {
                    order.setEmployeeSalary(resultSet.getDouble("employee_salary"));
                }
                if (resultSet.getDouble("time") != 0.0) {
                    order.setTime(resultSet.getDouble("time"));
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static ArrayList<ActiveOrder> loadAllActive() {
        ArrayList<ActiveOrder> activeOrders = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_ACTIVE_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ActiveOrder activeOrder = new ActiveOrder();
                activeOrder.setId(resultSet.getInt("id"));
                activeOrder.setReceived(resultSet.getDate("received"));
                activeOrder.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    activeOrder.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getString("problem") != null) {
                    activeOrder.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    activeOrder.setRepair(resultSet.getString("repair"));
                }
                activeOrder.setStatus(resultSet.getString("status"));
                activeOrder.setBrand(resultSet.getString("brand"));
                activeOrder.setModel(resultSet.getString("model"));
                activeOrders.add(activeOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeOrders;
    }

    public static ArrayList<ActiveOrder> loadBasicOrders() {
        ArrayList<ActiveOrder> activeOrders = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_BASIC_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ActiveOrder activeOrder = new ActiveOrder();
                activeOrder.setId(resultSet.getInt("id"));
                activeOrder.setReceived(resultSet.getDate("received"));
                activeOrder.setPlanned(resultSet.getDate("planned"));
                if(resultSet.getDate("started") != null){
                    activeOrder.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getString("problem") != null) {
                    activeOrder.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    activeOrder.setRepair(resultSet.getString("repair"));
                }
                activeOrder.setStatus(resultSet.getString("status"));
                activeOrder.setBrand(resultSet.getString("brand"));
                activeOrder.setModel(resultSet.getString("model"));
                activeOrders.add(activeOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activeOrders;
    }

    public static ArrayList<Order> loadHistory(int vehicle_id) {
        ArrayList<Order> history = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_HISTORY);
            preparedStatement.setInt(1, vehicle_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setReceived(resultSet.getDate("received"));
                if(resultSet.getDate("started") != null){
                    order.setStarted(resultSet.getDate("started"));
                }
                if(resultSet.getString("problem") != null) {
                    order.setProblem(resultSet.getString("problem"));
                }
                if(resultSet.getString("repair") != null) {
                    order.setRepair(resultSet.getString("repair"));
                }
                if (resultSet.getDouble("cost") != 0.0) {
                    order.setCost(resultSet.getDouble("cost"));
                }
                history.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
