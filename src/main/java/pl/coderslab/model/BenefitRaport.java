package pl.coderslab.model;

public class BenefitRaport {

    private double cost;
    private double parts;
    private double employee_salary;
    private double time;
    private double benefit;

    public BenefitRaport() {
    }

    public BenefitRaport(double cost, double parts, double employee_salary, double time, double benefit) {
        this.cost = cost;
        this.parts = parts;
        this.employee_salary = employee_salary;
        this.time = time;
        this.benefit = benefit;
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

    public double getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(double employee_salary) {
        this.employee_salary = employee_salary;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }
}
