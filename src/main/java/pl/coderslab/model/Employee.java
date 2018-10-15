package pl.coderslab.model;

public class Employee {

    private int id;
    private String name;
    private String surname;
    private String address;
    private int phone;
    private String note;
    private double salary;

    public Employee() {
    }

    public Employee(String name, String surname, String address, int phone, double salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public Employee(String name, String surname, String address, int phone, String note, double salary) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
