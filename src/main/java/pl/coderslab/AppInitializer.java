package pl.coderslab;

public class AppInitializer {

    private String CREATE_TABLE_CUSTOMER = "CREATE TABLE customers (id INT AUTO_INCREMENT, name VARCHAR(30), surname VARCHAR(30), PRIMARY KEY(id));\n";
    private String CREATE_TABLE_EMPLOYEE = "CREATE TABLE employees (id INT AUTO_INCREMENT, name VARCHAR(30), surname VARCHAR(30), address VARCHAR(100), phone INT(11), note VARCHAR(100), salary DECIMAL(5,2),PRIMARY KEY(id));";
    private String CREATE_TABLE_ORDER = "CREATE TABLE orders (id INT AUTO_INCREMENT, received DATE, planned DATE, started DATE, employee_id INT, problem VARCHAR(100), repair VARCHAR(100), status_id INT, vehicle_id INT, cost DECIMAL(6,2), parts DECIMAL(6,2), employee_salary DECIMAL(5,2), time DECIMAL(6,2), PRIMARY KEY(id), FOREIGN KEY(vehicle_id) REFERENCES vehicles(id), FOREIGN KEY(employee_id) REFERENCES employees(id), FOREIGN KEY(status_id) REFERENCES status(id));\n";
    private String CREATE_TABLE_STATUS = "CREATE TABLE status (id INT AUTO_INCREMENT, status VARCHAR(30), PRIMARY KEY(id));\n";
    private String CREATE_TABLE_VEHICLE = "CREATE TABLE vehicles (id INT AUTO_INCREMENT, brand VARCHAR(30), model VARCHAR(30), year INT(4), registration VARCHAR(10) UNIQUE, inspection DATE, customer_id INT, PRIMARY KEY(id), FOREIGN KEY(customer_id) REFERENCES customers(id));";

}
