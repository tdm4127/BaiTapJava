<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Trang đăng nhập: gửi username/password đến LoginServlet. --%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập - Quản lý phân công công việc</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/base.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/imgs/favicon.png">
    </head>

    <body>
        <main class="login-page">
            <section class="login-card">
                <div class="login-brand">
                    <div class="login-brand-icon">
                        <i class="bi bi-kanban"></i>
                    </div>
                </div>

                <div class="login-header">
                    <h1>Đăng nhập</h1>
                    <p>Hệ thống quản lý phân công công việc</p>
                </div>

                <%-- Form gửi thông tin đăng nhập về Servlet. --%>
                <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-field">
                        <label class="form-label" for="username">Tên đăng nhập</label>
                        <div class="input-group">
                            <span class="input-group-text">
                                <i class="bi bi-person"></i>
                            </span>
                            <input type="text" name="username" id="username" class="form-control"
                                   placeholder="Nhập tên đăng nhập">
                        </div>
                    </div>

                    <div class="form-field">
                        <label class="form-label" for="password">Mật khẩu</label>
                        <div class="input-group">
                            <span class="input-group-text">
                                <i class="bi bi-lock"></i>
                            </span>
                            <input type="password" name="password" id="password" class="form-control"
                                   placeholder="Nhập mật khẩu">
                        </div>
                    </div>
                    
                    <div class="text-danger text-center mb-3">
                        ${error}
                    </div>
                    
                    <button type="submit" class="btn btn-primary login-button">
                        <i class="bi bi-box-arrow-in-right me-1"></i>
                        Đăng nhập
                    </button>
                </form>

                <div class="login-footer">
                    Work Assignment Management
                </div>
            </section>
        </main>
    </body>
</html>
