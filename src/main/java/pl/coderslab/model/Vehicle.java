package pl.coderslab.model;

import java.sql.Date;

public class Vehicle {

    private int id;
    private String brand;
    private String model;
    private int year;
    private String registration;
    private Date inspection;
    private int customer_id;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, int year, String registration, Date inspection, int vehicle_id) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.registration = registration;
        this.inspection = inspection;
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getInspection() {
        return inspection;
    }

    public void setInspection(Date inspection) {
        this.inspection = inspection;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setVehicle_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
