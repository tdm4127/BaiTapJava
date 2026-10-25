<%@page import="enums.ProjectResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Hiển thị thông báo sau thao tác dự án. --%>

<%
    ProjectResult result = (ProjectResult) session.getAttribute("result");

    if (result != null) {
        String msg = "";
        String alertClass = "alert-info";

        switch (result) {
            case EmptyName:
                msg = "Tên dự án không được để trống.";
                alertClass = "alert-danger";
                break;
            case EmptyStartDate:
                msg = "Vui lòng chọn ngày bắt đầu dự án.";
                alertClass = "alert-danger";
                break;
            case EmptyEndDate:
                msg = "Vui lòng chọn ngày kết thúc dự án.";
                alertClass = "alert-danger";
                break;
            case NotFound:
                msg = "Không tìm thấy dự án.";
                alertClass = "alert-warning";
                break;
            case HasRelatedData:
                msg = "Không thể xóa vì dự án đang có dữ liệu liên quan.";
                alertClass = "alert-warning";
                break;
            case Success:
                String action = (String) session.getAttribute("action");

                switch (action) {
                    case "add":
                        msg = "Thêm dự án thành công.";
                        break;
                    case "edit":
                        msg = "Cập nhật dự án thành công.";
                        break;
                    case "delete":
                        msg = "Xóa dự án thành công.";
                        break;
                }
                alertClass = "alert-success";
                break;
            case DatabaseError:
                msg = "Không thể xử lý yêu cầu. Vui lòng thử lại.";
                alertClass = "alert-danger";
                break;
        }
        session.removeAttribute("result");
        session.removeAttribute("action");
%>

<div class="alert <%= alertClass%> alert-dismissible fade show" role="alert">
    <%= msg%>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Đóng"></button>
</div>

<%
    }
%>
