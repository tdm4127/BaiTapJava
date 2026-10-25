<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Trang công việc: ghép toolbar, bảng và các modal. --%>

<div class="page-container task-page">
    <div class="page-heading">
        <div class="page-title-row">
            <div>
                <h1><i class="bi bi-clipboard-check-fill"></i> Quản lý công việc</h1>
            </div>
        </div>
    </div>
<%-- Ghép thành phần con của trang task. --%>
    <jsp:include page="task-message.jsp" />
    <jsp:include page="task-toolbar.jsp" />
    <jsp:include page="task-table.jsp" />
</div>

<jsp:include page="task-modal.jsp" />
<jsp:include page="task-view-modal.jsp" />
<jsp:include page="/view/common/delete-modal.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/task.js"></script>
