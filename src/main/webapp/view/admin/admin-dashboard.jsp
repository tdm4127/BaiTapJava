<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Dashboard Admin: ba số liệu nhanh và bốn lối vào quản lý. --%>

<div class="home-dashboard">
    <%-- Lời chào Admin. --%>
    <div class="home-welcome">
        <div>
            <span class="home-eyebrow">TỔNG QUAN QUẢN LÝ</span>
            <h1>Chào mừng trở lại, Admin!</h1>
            <p>Quản lý nhân viên, dự án, công việc và phân công từ một nơi.</p>
        </div>
        <div class="home-illustration" aria-hidden="true">
            <i class="bi bi-kanban-fill"></i>
        </div>
    </div>

    <%-- Ba số liệu do Servlet cung cấp. --%>
    <div class="home-quick-stats">
        <article class="home-quick-stat">
            <span><i class="bi bi-people"></i></span>
            <div><strong>${employeeCount}</strong><small>Nhân viên</small></div>
        </article>
        <article class="home-quick-stat">
            <span><i class="bi bi-folder2-open"></i></span>
            <div><strong>${projectCount}</strong><small>Dự án</small></div>
        </article>
        <article class="home-quick-stat">
            <span><i class="bi bi-list-check"></i></span>
            <div><strong>${taskCount}</strong><small>Công việc</small></div>
        </article>
    </div>

    <%-- Bốn liên kết đến các trang quản lý. --%>
    <div class="home-module-grid">
        <a class="home-module-card module-employee" href="${pageContext.request.contextPath}/employee">
            <span class="home-module-icon"><i class="bi bi-people-fill"></i></span>
            <strong>Quản lý nhân viên</strong>
            <p>Thêm, sửa, xem danh sách nhân viên và thông tin liên hệ.</p>
            <span class="home-module-action">Xem nhân viên <i class="bi bi-arrow-right"></i></span>
        </a>

        <a class="home-module-card module-project" href="${pageContext.request.contextPath}/project">
            <span class="home-module-icon"><i class="bi bi-folder-fill"></i></span>
            <strong>Quản lý dự án</strong>
            <p>Theo dõi dự án, thời gian và số công việc của từng dự án.</p>
            <span class="home-module-action">Xem dự án <i class="bi bi-arrow-right"></i></span>
        </a>

        <a class="home-module-card module-task" href="${pageContext.request.contextPath}/task">
            <span class="home-module-icon"><i class="bi bi-clipboard-check-fill"></i></span>
            <strong>Quản lý công việc</strong>
            <p>Theo dõi hạn chót, trạng thái, độ ưu tiên và dự án liên quan.</p>
            <span class="home-module-action">Xem công việc <i class="bi bi-arrow-right"></i></span>
        </a>

        <a class="home-module-card module-assignment" href="${pageContext.request.contextPath}/assignment">
            <span class="home-module-icon"><i class="bi bi-person-check-fill"></i></span>
            <strong>Quản lý phân công</strong>
            <p>Phân công nhân viên vào công việc và theo dõi người phụ trách.</p>
            <span class="home-module-action">Xem phân công <i class="bi bi-arrow-right"></i></span>
        </a>
    </div>
</div>
