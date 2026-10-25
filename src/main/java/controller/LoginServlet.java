// Servlet đăng nhập: kiểm tra tài khoản và lưu thông tin vào Session.
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import service.AccountService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AccountService accService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accService.login(username, password);
        if (account == null) {
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
            return;
        }

        // Lưu thông tin đăng nhập để các trang dùng chung.
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", account.getRole());
        session.setAttribute("employeeId", account.getEmployeeId());
        session.setAttribute("employeeName", account.getEmployeeName());
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }
}
