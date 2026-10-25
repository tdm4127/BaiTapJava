<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Trang nhân viên: ghép công cụ, bảng, thông báo và popup. --%>

<div class="page-container employee-page">
    <div class="page-heading">
        <div class="page-title-row">
            <div>
                <h1><i class="bi bi-people-fill"></i> Quản lý nhân viên</h1>
            </div>
        </div>
    </div>

<%-- Ghép thành phần con của trang employee. --%>
    <jsp:include page="employee-toolbar.jsp" />
    <jsp:include page="employee-message.jsp" />
    <jsp:include page="employee-table.jsp" />
</div>

<jsp:include page="employee-modal.jsp" />
<jsp:include page="employee-view-modal.jsp" />
<jsp:include page="/view/common/delete-modal.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/employee.js"></script>
