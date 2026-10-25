<%@page import="enums.TaskResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Hiển thị thông báo thao tác công việc. --%>

<%
    TaskResult result = (TaskResult) session.getAttribute("result");

    if (result != null) {
        String msg = "";
        String alertClass = "alert-info";

        switch (result) {
            case EmptyTask:
                msg = "Tên công việc không được để trống.";
                alertClass = "alert-danger";
                break;
            case EmptyDeadline:
                msg = "Vui lòng chọn ngày hạn chót dự án.";
                alertClass = "alert-danger";
                break;
            case EmptyStatus:
                msg = "Vui lòng chọn trạng thái.";
                alertClass = "alert-danger";
                break;
            case EmptyPriority:
                msg = "Vui lòng chọn độ ưu tiên.";
                alertClass = "alert-danger";
                break;
            case ProjectNotFound:
                msg = "Không tìm thấy dự án.";
                alertClass = "alert-warning";
                break;
            case TaskNotFound:
                msg = "Không tìm thấy công việc.";
                alertClass = "alert-warning";
                break;
            case HasRelatedData:
                msg = "Không thể xóa vì công việc đang có dữ liệu liên quan.";
                alertClass = "alert-warning";
                break;
            case Success:
                String action = (String) session.getAttribute("action");

                switch (action) {
                    case "add":
                        msg = "Thêm công việc thành công.";
                        break;
                    case "edit":
                        msg = "Cập nhật công việc thành công.";
                        break;
                    case "delete":
                        msg = "Xóa công việc thành công.";
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
