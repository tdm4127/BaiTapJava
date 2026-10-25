<%@page import="enums.AssignmentResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Hiển thị thông báo sau thao tác phân công. --%>
<%
    AssignmentResult result = (AssignmentResult) session.getAttribute("result");
    if (result != null) {
        String msg = "";
        String alertClass = "alert-danger";
        switch (result) {
            case EmptyTask:
                msg = "Vui lòng chọn công việc.";
                break;
            case EmptyEmployee:
                msg = "Vui lòng chọn nhân viên.";
                break;
            case EmptyAssignedDate:
                msg = "Vui lòng chọn ngày phân công.";
                break;
            case AssignmentNotFound:
                msg = "Không tìm thấy phân công.";
                break;
            case TaskNotFound:
                msg = "Không tìm thấy công việc.";
                break;
            case EmployeeNotFound:
                msg = "Không tìm thấy nhân viên.";
                break;
            case Success:
                String action = (String) session.getAttribute("action");
                if ("add".equals(action)) {
                    msg = "Thêm phân công thành công.";
                } else if ("edit".equals(action)) {
                    msg = "Cập nhật phân công thành công.";
                } else if ("delete".equals(action)) {
                    msg = "Xóa phân công thành công.";
                }
                alertClass = "alert-success";
                break;
            case DatabaseError:
                msg = "Không thể xử lý yêu cầu. Vui lòng thử lại.";
                break;
        }
        session.removeAttribute("result");
        session.removeAttribute("action");
%>
<div class="alert <%= alertClass %> alert-dismissible fade show" role="alert">
    <%= msg %>
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Đóng"></button>
</div>
<% } %>
