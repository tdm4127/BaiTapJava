// Model Employee: chứa thông tin nhân viên.
package model;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String position;
    private String email;
    private String phoneNumber;       

    public Employee(String employeeName, String position, String email, String phoneNumber) {
        this.employeeName = employeeName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee(int employeeId, String employeeName, String position, String email, String phoneNumber) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}