// Servlet nhân viên: nhận thao tác thêm, sửa, xóa và tìm kiếm.
package controller;

import enums.EmployeeResult;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Employee;
import service.EmployeeService;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService service = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String position = request.getParameter("position");
        String pageParam = request.getParameter("page");

        int currentPage = 1;
        if (pageParam != null && !pageParam.trim().isEmpty()) {
            try {
                currentPage = Integer.parseInt(pageParam);
                if (currentPage < 1) {
                    currentPage = 1;
                }
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }

        int totalRecords = service.countEmployees(keyword, position);
        int totalPages = service.calculateTotalPages(totalRecords);

        if (totalPages == 0) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        List<Employee> employees = service.findEmployee(keyword, position, currentPage);

        request.setAttribute("employees", employees);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("contentPage", "/view/admin/employee/employee-form.jsp");
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String employeeName = request.getParameter("employeeName");
        String position = request.getParameter("position");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String action = request.getParameter("action");
        EmployeeResult result = null;

        switch (action) {
            case "add":
                Employee empAdd = new Employee(employeeName, position, email, phone);
                result = service.addEmployee(empAdd);
                break;
            case "edit":
                int employeeIdEdit = Integer.parseInt(request.getParameter("employeeId"));
                Employee empEdit = new Employee(employeeIdEdit, employeeName, position, email, phone);
                result = service.updateEmployee(empEdit);
                break;
            case "delete":
                int employeeIdDelete = Integer.parseInt(request.getParameter("employeeId"));
                result = service.deleteEmployee(employeeIdDelete);
                break;
        }
        request.getSession().setAttribute("result", result);
        request.getSession().setAttribute("action", action);
        response.sendRedirect(request.getContextPath() + "/employee");
    }
}
