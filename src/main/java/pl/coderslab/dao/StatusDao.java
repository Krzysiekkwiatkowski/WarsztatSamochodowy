package pl.coderslab.dao;

import pl.coderslab.DbUtil;
import pl.coderslab.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatusDao {

    private static String CREATE_STATUS = "INSERT INTO status(status) VALUES (?)";
    private static String EDIT_STATUS = "UPDATE status SET status = ? WHERE id = ?";
    private static String DELETE_STATUS = "DELETE FROM status WHERE id = ?";
    private static String LOAD_STATUS_BY_ID = "SELECT * FROM status WHERE id = ?";
    private static String LOAD_ALL_STATUS = "SELECT * FROM status";

    public Status createStatus(Status status){
        String[] generatedColumns = {"ID"};
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATUS, generatedColumns);
            preparedStatement.setString(1, status.getStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                status.setId(resultSet.getInt(1));
            }
            return status;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void editStatus(Status status){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_STATUS);
            preparedStatement.setString(1, status.getStatus());
            preparedStatement.setInt(2, status.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteStatus(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATUS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Status loadById(int id){
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_STATUS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setStatus(resultSet.getString("status"));
                return status;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Status> loadAll(){
        ArrayList<Status> statuses = new ArrayList<>();
        try {
            Connection connection = DbUtil.getConn();
            PreparedStatement preparedStatement = connection.prepareStatement(LOAD_ALL_STATUS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setStatus(resultSet.getString("status"));
                statuses.add(status);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return statuses;
    }
}
