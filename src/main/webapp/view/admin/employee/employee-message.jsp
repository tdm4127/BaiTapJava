<%@page import="enums.EmployeeResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Thông báo kết quả thêm/sửa/xóa nhân viên. --%>

<%
    EmployeeResult result = (EmployeeResult) session.getAttribute("result");

    if (result != null) {
        String msg = "";
        String alertClass = "alert-info";

        switch (result) {
            case EmptyName:
                msg = "Họ và tên không được để trống.";
                alertClass = "alert-danger";
                break;
            case EmptyPosition:
                msg = "Vui lòng chọn chức vụ.";
                alertClass = "alert-danger";
                break;
            case InvalidPhoneNumber:
                msg = "Số điện thoại phải gồm 10 chữ số.";
                alertClass = "alert-danger";
                break;
            case DuplicateEmail:
                msg = "Email đã tồn tại.";
                alertClass = "alert-warning";
                break;
            case DuplicatePhoneNumber:
                msg = "Số điện thoại đã tồn tại.";
                alertClass = "alert-warning";
                break;
            case NotFound:
                msg = "Không tìm thấy nhân viên.";
                alertClass = "alert-warning";
                break;
            case HasRelatedData:
                msg = "Không thể xóa vì nhân viên đang có dữ liệu liên quan.";
                alertClass = "alert-warning";
                break;
            case Success:
                String action = (String) session.getAttribute("action");

                switch (action) {
                    case "add":
                        msg = "Thêm nhân viên thành công.";
                        break;
                    case "edit":
                        msg = "Cập nhật nhân viên thành công.";
                        break;
                    case "delete":
                        msg = "Xóa nhân viên thành công.";
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
