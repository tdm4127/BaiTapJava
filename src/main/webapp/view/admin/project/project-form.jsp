<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Trang dự án: ghép thanh công cụ, bảng và các modal. --%>

<div class="page-container project-page">
    <div class="page-heading">
        <div class="page-title-row">
            <div>
                <h1><i class="bi bi-folder-fill"></i> Quản lý dự án</h1>
            </div>
        </div>
    </div>
<%-- Ghép thành phần con của trang project. --%>
    <jsp:include page="project-message.jsp" />
    <jsp:include page="project-toolbar.jsp" />
    <jsp:include page="project-table.jsp" />
</div>

<jsp:include page="project-modal.jsp" />
<jsp:include page="project-view-modal.jsp" />
<jsp:include page="/view/common/delete-modal.jsp" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/project.js"></script>
