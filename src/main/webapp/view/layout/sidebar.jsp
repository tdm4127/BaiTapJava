
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Sidebar: Admin có menu quản lý; Employee có ba mục. --%>
<%
    jakarta.servlet.http.HttpSession currentSession = request.getSession(false);
    String currentRole = currentSession == null ? null : (String) currentSession.getAttribute("role");
%>

<% if ("employee".equals(currentRole)) { %>
<aside class="sidebar employee-sidebar" aria-label="Điều hướng nhân viên">
    <div class="sidebar-title">NHÂN VIÊN</div>

    <%-- Mỗi liên kết gọi Servlet để hiển thị đúng trang Employee. --%>
    <a href="${pageContext.request.contextPath}/dashboard"
       class="sidebar-link employee-nav-link">
        <i class="bi bi-house-door"></i>
        <span>Tổng quan</span>
    </a>

    <a href="${pageContext.request.contextPath}/my-tasks"
       class="sidebar-link employee-nav-link">
        <i class="bi bi-clipboard-check"></i>
        <span>Công việc của tôi</span>
    </a>

    <a href="${pageContext.request.contextPath}/my-profile"
       class="sidebar-link employee-nav-link">
        <i class="bi bi-person-vcard"></i>
        <span>Hồ sơ cá nhân</span>
    </a>

</aside>
<% } else { %>
<%-- Menu quản trị: các đường dẫn gọi Servlet. --%>
<aside class="sidebar">
    <div class="sidebar-title">QUẢN LÝ</div>

    <a href="${pageContext.request.contextPath}/dashboard"
       class="sidebar-link">
        <i class="bi bi-house-door"></i>
        <span>Tổng quan</span>
    </a>

    <a href="${pageContext.request.contextPath}/employee"
       class="sidebar-link">
        <i class="bi bi-people"></i>
        <span>Nhân viên</span>
    </a>

    <a href="${pageContext.request.contextPath}/project"
       class="sidebar-link">
        <i class="bi bi-folder"></i>
        <span>Dự án</span>
    </a>

    <a href="${pageContext.request.contextPath}/task"
       class="sidebar-link">
        <i class="bi bi-list-check"></i>
        <span>Công việc</span>
    </a>

    <a href="${pageContext.request.contextPath}/assignment"
       class="sidebar-link">
        <i class="bi bi-person-check"></i>
        <span>Phân công</span>
    </a>

</aside>
<% } %>
