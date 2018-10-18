package pl.coderslab.model;

public class WorkRaport {

    private String employee;
    private double workedTime;

    public WorkRaport() {
    }

    public WorkRaport(String employee, double workedTime) {
        this.employee = employee;
        this.workedTime = workedTime;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public double getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(double workedTime) {
        this.workedTime = workedTime;
    }
}
