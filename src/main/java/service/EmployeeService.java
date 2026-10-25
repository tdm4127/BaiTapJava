// Service nhân viên: kiểm tra dữ liệu và tạo tài khoản khi thêm mới.
package service;

import dao.AccountDao;
import dao.EmployeeDao;
import java.util.List;
import model.Employee;
import enums.EmployeeResult;
import model.Account;

public class EmployeeService {

    private EmployeeDao empDao = new EmployeeDao();
    private static final int PAGE_SIZE = 10;

    public List<Employee> getAllEmployees() {
        return empDao.findAll();
    }

    public EmployeeResult addEmployee(Employee emp) {
        if (emp.getEmployeeName() == null || emp.getEmployeeName().trim().isEmpty()) {
            return EmployeeResult.EmptyName;
        } else if (emp.getPosition() == null || emp.getPosition().trim().isEmpty()) {
            return EmployeeResult.EmptyPosition;
        } else if (emp.getPhoneNumber() != null
                && !emp.getPhoneNumber().trim().isEmpty()
                && !emp.getPhoneNumber().matches("\\d{10}")) {
            return EmployeeResult.InvalidPhoneNumber;
        } else {
            if (emp.getEmail() != null && emp.getEmail().trim().isEmpty()) {
                emp.setEmail(null);
            }
            if (emp.getPhoneNumber() != null && emp.getPhoneNumber().trim().isEmpty()) {
                emp.setPhoneNumber(null);
            }
            if (emp.getEmail() != null && empDao.findByEmail(emp.getEmail()) != null) {
                return EmployeeResult.DuplicateEmail;
            }
            if (emp.getPhoneNumber() != null && empDao.findByPhoneNumber(emp.getPhoneNumber()) != null) {
                return EmployeeResult.DuplicatePhoneNumber;
            }
            int employeeId = empDao.insert(emp);
            if (employeeId != -1) {
                String username = String.format("EMP%03d", employeeId);
                String password = username;
                String role = "employee";

                Account account = new Account(username, password, role, employeeId);
                AccountDao accDao = new AccountDao();

                boolean result = accDao.insert(account);

                if (result) {
                    return EmployeeResult.Success;
                }
            }
            return EmployeeResult.DatabaseError;
        }
    }

    public EmployeeResult updateEmployee(Employee emp) {
        if (emp.getEmployeeName() == null || emp.getEmployeeName().trim().isEmpty()) {
            return EmployeeResult.EmptyName;
        } else if (emp.getPosition() == null || emp.getPosition().trim().isEmpty()) {
            return EmployeeResult.EmptyPosition;
        } else if (emp.getPhoneNumber() != null
                && !emp.getPhoneNumber().trim().isEmpty()
                && !emp.getPhoneNumber().matches("\\d{10}")) {
            return EmployeeResult.InvalidPhoneNumber;
        } else {
            if (empDao.findById(emp.getEmployeeId()) == null) {
                return EmployeeResult.NotFound;
            }
            if (emp.getEmail() != null && emp.getEmail().trim().isEmpty()) {
                emp.setEmail(null);
            }
            if (emp.getPhoneNumber() != null && emp.getPhoneNumber().trim().isEmpty()) {
                emp.setPhoneNumber(null);
            }
            if (emp.getEmail() != null && empDao.findByEmailExceptEmployeeId(emp.getEmail(), emp.getEmployeeId()) != null) {
                return EmployeeResult.DuplicateEmail;
            }
            if (emp.getPhoneNumber() != null && empDao.findByPhoneNumberExceptEmployeeId(emp.getPhoneNumber(), emp.getEmployeeId()) != null) {
                return EmployeeResult.DuplicatePhoneNumber;
            }
            if (empDao.update(emp)) {
                return EmployeeResult.Success;
            }
            return EmployeeResult.DatabaseError;
        }
    }

    public EmployeeResult deleteEmployee(int employeeID) {
        if (empDao.findById(employeeID) == null) {
            return EmployeeResult.NotFound;
        }

        if (empDao.hasAssignmentsByEmployeeId(employeeID)) {
            return EmployeeResult.HasRelatedData;
        }

        if (empDao.delete(employeeID)) {
            return EmployeeResult.Success;
        }
        return EmployeeResult.DatabaseError;
    }

    public List<Employee> findEmployee(String keyword, String position, int page) {
        Integer employeeId = null;
        String employeeName = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("EMP".startsWith(kw) || kw.startsWith("EMP")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        employeeId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                employeeName = keyword.trim();
            }
        }

        int offset = (page - 1) * PAGE_SIZE;
        return empDao.findEmployee(employeeName, employeeId, position, offset, PAGE_SIZE);
    }

    public int countEmployees(String keyword, String position) {
        Integer employeeId = null;
        String employeeName = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            String kw = keyword.trim().toUpperCase();
            if ("EMP".startsWith(kw) || kw.startsWith("EMP")) {
                if (kw.length() > 3) {
                    String numberPart = kw.substring(3);
                    if (numberPart.matches("\\d{3}")) {
                        employeeId = Integer.parseInt(numberPart);
                    }
                }
            } else {
                employeeName = keyword.trim();
            }
        }

        return empDao.countEmployees(employeeName, employeeId, position);
    }

    public int calculateTotalPages(int totalRecords) {
        return (int) Math.ceil((double) totalRecords / PAGE_SIZE);
    }    
}
