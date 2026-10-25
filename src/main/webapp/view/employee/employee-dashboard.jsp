<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Tổng quan nhân viên: đếm trạng thái công việc do Servlet cung cấp. --%>
<div class="employee-dashboard">
    <section id="employee-overview" class="ed-view">
        <div class="ed-hero">
            <div>
                <small>KHÔNG GIAN NHÂN VIÊN</small>
                <h1>Chào mừng trở lại!</h1>
                <p>Tổng quan công việc được giao và tiến độ của bạn.</p>
            </div>
            <i class="bi bi-person-workspace ed-hero-icon" aria-hidden="true"></i>
        </div>
        <div class="ed-stats">
            <article class="ed-stat"><i class="bi bi-clipboard2"></i><div><strong>${notStartedCount}</strong><span>Chưa bắt đầu</span></div></article>
            <article class="ed-stat"><i class="bi bi-arrow-repeat"></i><div><strong>${inProgressCount}</strong><span>Đang thực hiện</span></div></article>
            <article class="ed-stat"><i class="bi bi-check2-circle"></i><div><strong>${completedCount}</strong><span>Hoàn thành</span></div></article>
        </div>
        <div class="ed-panel ed-intro">
            <h2>Không gian làm việc của bạn</h2>
            <p>Chọn <strong>Công việc của tôi</strong> để xem nhiệm vụ hoặc <strong>Hồ sơ cá nhân</strong>
                để xem tài khoản.</p>
        </div>
    </section>
</div>
