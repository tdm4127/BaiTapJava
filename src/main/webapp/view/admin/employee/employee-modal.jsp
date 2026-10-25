<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Form thêm/sửa nhân viên, POST về EmployeeServlet. --%>

<div class="modal fade" id="AddEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content app-modal">
            <%-- Form gửi dữ liệu về Servlet. --%>
<form method="post" action="employee" id="employee-form">
                <input type="hidden" name="action" id="inputAction">
                <input type="hidden" name="employeeId" id="inputEmployeeId">

                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Thêm nhân viên</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                </div>

                <div class="modal-body">
                    <div class="form-field">
                        <label class="form-label">Mã nhân viên</label>
                        <input type="text" id="displayId" class="form-control"
                               placeholder="Tự động tạo" readonly>
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Họ và tên <span class="required-mark">*</span>
                        </label>
                        <input type="text" name="employeeName" id="inputEmployeeName"
                               class="form-control" placeholder="Nhập họ và tên" required
                               oninvalid="this.setCustomValidity('Vui lòng nhập họ và tên')"
                               oninput="this.setCustomValidity('')">
                    </div>

                    <div class="form-field">
                        <label class="form-label">
                            Chức vụ <span class="required-mark">*</span>
                        </label>
                        <select id="inputPosition" name="position" class="form-select" required>
                            <option value="" selected disabled>-- Chọn chức vụ --</option>
                            <option value="Quản lý">Quản lý</option>
                            <option value="Lập trình viên">Lập trình viên</option>
                            <option value="Kiểm thử viên">Kiểm thử viên</option>
                            <option value="Thiết kế">Thiết kế</option>
                            <option value="Phân tích viên">Phân tích viên</option>
                        </select>
                    </div>

                    <div class="form-field">
                        <label class="form-label">Email</label>
                        <input type="email" name="email" id="inputEmail"
                               class="form-control" placeholder="Ví dụ: name@example.com">
                    </div>

                    <div class="form-field">
                        <label class="form-label">Số điện thoại</label>
                        <input type="text" name="phone" id="inputPhone"
                               class="form-control" placeholder="Ví dụ: 0987654321">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-light border" data-bs-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-check-lg me-1"></i> Lưu
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
