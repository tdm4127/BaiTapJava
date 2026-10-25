package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employee;
import dao.EmployeeDao;

@WebServlet("/my-profile")
public class MyProfileServlet extends HttpServlet {
    private EmployeeDao empDao = new EmployeeDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // AuthFilter đã kiểm tra đăng nhập; lấy đúng hồ sơ từ Session.
        HttpSession session = request.getSession(false);
        Integer employeeId = (Integer) session.getAttribute("employeeId");
        Employee employee = empDao.findById(employeeId);
        request.setAttribute("employee", employee);
        request.setAttribute("contentPage", "/view/employee/my-profile.jsp");
        request.getRequestDispatcher("/view/layout/main.jsp").forward(request, response);
    }
}
