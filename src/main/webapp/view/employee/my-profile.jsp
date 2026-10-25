<%@page import="model.Employee"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Hồ sơ cá nhân: hiển thị tài khoản; thông tin khác--%>
<div class="employee-dashboard">
    <section id="employee-profile" class="ed-view">
        <h1>Hồ sơ cá nhân</h1>
        <p class="ed-muted">Thông tin tài khoản và nhân viên của bạn.</p>
        <div class="ed-panel ed-profile">
            <div class="ed-profile-head">
                <i class="bi bi-person-circle" aria-hidden="true"></i>
                <div><h2>${sessionScope.username}</h2><p>Tài khoản nhân viên</p></div>
            </div>
            <div class="ed-profile-grid">
                <%Employee employee = (Employee) request.getAttribute("employee");%>
                <div><span>Tên đăng nhập</span><strong>${sessionScope.username}</strong></div>
                <div><span>Quyền</span><strong>Nhân viên</strong></div>
                <div><span>Họ và tên</span><strong><%=employee.getEmployeeName()%></strong></div>
                <div><span>Mã nhân viên</span><strong><%=String.format("EMP%03d", employee.getEmployeeId())%></strong></div>
                <div><span>Chức vụ</span><strong><%=employee.getPosition()%></strong></div>
                <div><span>Email</span><strong><%= employee.getEmail() != null ? employee.getEmail() : "—"%></strong></div>
                <div><span>Số điện thoại</span><strong><%= employee.getPhoneNumber() != null ? employee.getPhoneNumber() : "—"%></strong></div>
            </div>
        </div>
    </section>
</div>
