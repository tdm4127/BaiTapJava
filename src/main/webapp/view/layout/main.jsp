<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String contentPage = (String) request.getAttribute("contentPage");

%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý phân công công việc</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css?v=20261025-1">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/imgs/favicon.png">
    </head>

    <body>
        <%-- Dùng chung Header và Sidebar. --%>
        <jsp:include page="/view/layout/header.jsp" />

        <div class="app-layout" id="appLayout">
            <jsp:include page="/view/layout/sidebar.jsp" />

            <%-- Nhúng trang được Servlet chọn; báo 404 nếu không có. --%>
            <main class="main-content">
                <%
                    if (contentPage == null || application.getResource(contentPage) == null) {
                        response.setStatus(404);
                %>
                <div class="error-box">
                    <div class="error-icon"><i class="bi bi-exclamation-triangle"></i></div>
                    <h1>Không tìm thấy trang</h1>
                    <p>Trang đang truy cập không tồn tại hoặc đã được di chuyển.</p>
                </div>
                <%
                    } else {
                %>
                <jsp:include page="<%= contentPage%>" />
                <%
                    }
                %>
            </main>
        </div>

        <script src="${pageContext.request.contextPath}/assets/js/layout.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/alert.js"></script>
    </body>
</html>
