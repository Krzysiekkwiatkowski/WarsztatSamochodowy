package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDao {

    private static String CREATE_VEHICLE = "INSERT INTO vehicles(brand, model, year, registration, inspection, customer_id) VALUES (?,?,?,?,?,?)";
    private static String EDIT_VEHICLE = "UPDATE vehicles SET brand = ?, model = ?, year = ?, registration = ?, inspection =?, customer_id = ? WHERE id = ?";
    private static String DELETE_VEHICLE = "DELETE FROM vehicles WHERE id = ?";
    private static String LOAD_VEHICLE_BY_ID = "SELECT * FROM vehicles WHERE id = ?";
    private static String LOAD_VEHICLES_BY_CUSTOMER_ID = "SELECT * FROM vehicles WHERE customer_id = ?";
    private static String LOAD_ALL_VEHICLES = "SELECT * FROM vehicles";

    public Vehicle createVehicle(Vehicle vehicle){
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VEHICLE, generatedColumns);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getRegistration());
            if(vehicle.getInspection() != null){
                preparedStatement.setDate(5, vehicle.getInspection());
            } else {
                preparedStatement.setNull(5, Types.DATE);
            }
            preparedStatement.setInt(6, vehicle.getCustomer_id());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                vehicle.setId(resultSet.getInt(1));
            }
            return vehicle;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void editVehicle(Vehicle vehicle){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_VEHICLE);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getRegistration());
            if(vehicle.getInspection() != null){
                preparedStatement.setDate(5, vehicle.getInspection());
            } else {
                preparedStatement.setNull(5, Types.DATE);
            }
            preparedStatement.setInt(6, vehicle.getCustomer_id());
            preparedStatement.setInt(7, vehicle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteVehicle(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Vehicle loadById(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_VEHICLE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setRegistration(resultSet.getString("registration"));
                if(resultSet.getDate("inspection") != null){
                    vehicle.setInspection(resultSet.getDate("inspection"));
                }
                vehicle.setCustomer_id(resultSet.getInt("customer_id"));
                return vehicle;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Vehicle> loadByCustomerId(int id){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_VEHICLES_BY_CUSTOMER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setRegistration(resultSet.getString("registration"));
                if(resultSet.getDate("inspection") != null){
                    vehicle.setInspection(resultSet.getDate("inspection"));
                }
                vehicles.add(vehicle);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

    public static ArrayList<Vehicle> loadAll(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_VEHICLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setYear(resultSet.getInt("year"));
                vehicle.setRegistration(resultSet.getString("registration"));
                if(resultSet.getDate("inspection") != null){
                    vehicle.setInspection(resultSet.getDate("inspection"));
                }
                vehicle.setCustomer_id(resultSet.getInt("customer_id"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return vehicles;
    }

}
