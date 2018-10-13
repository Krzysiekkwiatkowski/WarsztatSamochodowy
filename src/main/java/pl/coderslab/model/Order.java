package pl.coderslab.model;

import java.sql.Date;

public class Order {

    private int id;
    private Date receive;
    private Date planned;
    private Date started;
    private Employee employee;
    private String problem;
    private String repair;
    private String status;
    private Vehicle vehicle;
    private double cost;
    private double parts;
    private double employeeSalary;
    private double time;

    public Order() {
    }

    public Order(Date receive, Date planned, Date started, Employee employee, String problem, String repair, String status, Vehicle vehicle, double cost, double parts, double employeeSalary, double time) {
        this.receive = receive;
        this.planned = planned;
        this.started = started;
        this.employee = employee;
        this.problem = problem;
        this.repair = repair;
        this.status = status;
        this.vehicle = vehicle;
        this.cost = cost;
        this.parts = parts;
        this.employeeSalary = employeeSalary;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReceive() {
        return receive;
    }

    public void setReceive(Date receive) {
        this.receive = receive;
    }

    public Date getPlanned() {
        return planned;
    }

    public void setPlanned(Date planned) {
        this.planned = planned;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getParts() {
        return parts;
    }

    public void setParts(double parts) {
        this.parts = parts;
    }

    public double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
