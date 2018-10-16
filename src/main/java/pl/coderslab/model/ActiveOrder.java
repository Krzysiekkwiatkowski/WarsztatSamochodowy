package pl.coderslab.model;

import java.sql.Date;

public class ActiveOrder {

    private int id;
    private Date received;
    private Date planned;
    private Date started;
    private String problem;
    private String repair;
    private String status;
    private String brand;
    private String model;

    public ActiveOrder() {
    }

    public ActiveOrder(int id, Date received, Date planned, Date started, String problem, String repair, String status, String brand, String model) {
        this.id = id;
        this.received = received;
        this.planned = planned;
        this.started = started;
        this.problem = problem;
        this.repair = repair;
        this.status = status;
        this.brand = brand;
        this.model = model;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
