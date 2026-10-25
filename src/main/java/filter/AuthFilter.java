package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// Kiểm tra đăng nhập và quyền truy cập trước khi vào Servlet.
@WebFilter(urlPatterns = {"/dashboard", "/employee", "/project", "/task",
    "/assignment", "/my-profile", "/my-tasks", "/view/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length());

        // Các JSP giao diện phải được mở thông qua Servlet.
        if (path.startsWith("/view/")) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null
                || session.getAttribute("role") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String role = (String) session.getAttribute("role");
        boolean employeePage = path.equals("/my-profile") || path.equals("/my-tasks");
        boolean dashboard = path.equals("/dashboard");

        // Employee chỉ vào trang cá nhân; Admin chỉ vào trang quản lý.
        if ((employeePage && !"employee".equals(role))
                || (!employeePage && !dashboard && !"admin".equals(role))
                || (dashboard && !"admin".equals(role) && !"employee".equals(role))) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}
