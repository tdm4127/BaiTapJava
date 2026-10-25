// Model Account: chứa thông tin tài khoản.
package model;

public class Account {
    private String username;
    private String password;
    private String role;
    private int employeeId;   
    private String employeeName;

    public Account(String username, String password, String role, int employeeId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.employeeId = employeeId;
    }

    public Account(String username, String password, String role, int employeeId, String employeeName) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
