<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Trang phân công: ghép thanh công cụ, thông báo, bảng và các modal. --%>

<div class="page-container assignment-page">
    <div class="page-heading">
        <div class="page-title-row">
            <div>
                <h1><i class="bi bi-person-check-fill"></i> Quản lý phân công</h1>
            </div>
        </div>
    </div>
<%-- Ghép thành phần con của trang assignment. --%>
    <jsp:include page="assignment-message.jsp" />
    <jsp:include page="assignment-toolbar.jsp" />
    <jsp:include page="assignment-table.jsp" />
</div>

<jsp:include page="assignment-modal.jsp" />
<jsp:include page="assignment-view-modal.jsp" />
<jsp:include page="/view/common/delete-modal.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/assignment.js"></script>
