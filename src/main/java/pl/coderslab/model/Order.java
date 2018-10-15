package pl.coderslab.model;

import java.sql.Date;

public class Order {

    private int id;
    private Date received;
    private Date planned;
    private Date started;
    private int employee_id;
    private String problem;
    private String repair;
    private int status_id;
    private int vehicle_id;
    private double cost;
    private double parts;
    private double employeeSalary;
    private double time;

    public Order() {
    }

    public Order(Date received, Date planned, String problem, String repair, int status_id, int vehicle_id) {
        this.received = received;
        this.planned = planned;
        this.problem = problem;
        this.repair = repair;
        this.status_id = status_id;
        this.vehicle_id = vehicle_id;
    }

    public Order(Date received, Date planned, Date started, int employee_id, String problem, String repair, int status_id, int vehicle_id, double employeeSalary) {
        this.received = received;
        this.planned = planned;
        this.started = started;
        this.employee_id = employee_id;
        this.problem = problem;
        this.repair = repair;
        this.status_id = status_id;
        this.vehicle_id = vehicle_id;
        this.employeeSalary = employeeSalary;
    }

    public Order(Date received, Date planned, Date started, int employee_id, String problem, String repair, int status_id, int vehicle_id, double cost, double parts, double employeeSalary, double time) {
        this.received = received;
        this.planned = planned;
        this.started = started;
        this.employee_id = employee_id;
        this.problem = problem;
        this.repair = repair;
        this.status_id = status_id;
        this.vehicle_id = vehicle_id;
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

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
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

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
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

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
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
