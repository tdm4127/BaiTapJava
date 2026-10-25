<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Form thêm/sửa dự án. --%>

<div class="modal fade" id="AddEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content app-modal">
            <%-- Form gửi dữ liệu về Servlet. --%>
<form method="post" action="project" id="project-form">
                <input type="hidden" name="projectId" id="inputProjectId">
                <input type="hidden" name="action" id="inputAction">

                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Thêm dự án</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                </div>

                <div class="modal-body">
                    <div class="project-form-content">

                        <div class="form-field">
                            <label class="form-label">Mã dự án</label>
                            <input type="text" id="displayId" class="form-control"
                                   placeholder="Tự động tạo" readonly>
                        </div>

                        <div class="form-field">
                            <label class="form-label">
                                Tên dự án <span class="required-mark">*</span>
                            </label>
                            <input type="text" name="projectName" id="inputProjectName"
                                   class="form-control" placeholder="Nhập tên dự án" required>
                        </div>

                        <div class="project-date-group">
                            <div class="date-field">
                                <label class="form-label">
                                    Ngày bắt đầu <span class="text-danger">*</span>
                                </label>
                                <input type="date" name="startDate" id="inputStartDate"
                                       class="form-control">
                            </div>

                            <div class="date-field">
                                <label class="form-label">
                                    Ngày kết thúc <span class="text-danger">*</span>
                                </label>
                                <input type="date" name="endDate" id="inputEndDate"
                                       class="form-control">
                            </div>

                            <div class="duration-field">
                                <label class="form-label">Thời lượng</label>
                                <div class="input-group">
                                    <input type="text" id="inputDuration"
                                           class="form-control" readonly>
                                    <span class="input-group-text">ngày</span>
                                </div>
                            </div>
                        </div>

                        <div class="form-field">
                            <label class="form-label">Mô tả</label>
                            <textarea name="description" id="inputDescription"
                                      class="form-control" rows="3"
                                      placeholder="Nhập mô tả dự án"></textarea>
                        </div>

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
