<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Header dùng chung cho Admin và Employee. --%>
<header class="app-header">
    <div class="header-brand">
        <div class="header-brand-icon"><i class="bi bi-kanban-fill"></i></div>
        <div class="header-title">Quản lý phân công công việc</div>
    </div>

    <%-- Bấm thông tin tài khoản để mở menu. --%>
    <details class="header-account-menu">
        <summary class="header-user-toggle">
            <span class="header-avatar"><i class="bi bi-person-fill"></i></span>
            <span class="header-user-name">${sessionScope.role eq 'employee' ? 'Nhân viên' : 'Quản trị viên'}</span>
            <i class="bi bi-chevron-down header-chevron"></i>
        </summary>

        <div class="header-account-dropdown">
            <%-- Thông tin tài khoản đang đăng nhập. --%>
            <div class="header-account-info">
                <span class="header-account-avatar"><i class="bi bi-person-fill"></i></span>
                <div>
                    <div class="header-account-name">${sessionScope.employeeName}</div>
                    <div class="header-account-role">${sessionScope.role eq 'employee' ? 'Nhân viên' : 'Quản trị viên'}</div>
                </div>
            </div>

            <%-- Biểu mẫu đăng xuất: gửi POST đến LogoutServlet. --%>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" id="logoutButton" class="header-logout-btn">
                    <i class="bi bi-box-arrow-right"></i> Đăng xuất
                </button>
            </form>
        </div>
    </details>
</header>
