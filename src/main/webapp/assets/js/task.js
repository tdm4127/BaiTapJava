// Đổi ngày từ yyyy-MM-dd sang dd/MM/yyyy khi xem chi tiết.
function formatDate(value) {
    return value ? value.split("-").reverse().join("/") : "—";
}

// Xử lý popup và nút thao tác công việc.
function openViewModal(button) {
    const row = button.closest(".task-row");

    document.getElementById("viewTaskCode").innerText = "TSK" + String(row.dataset.taskId).padStart(3, "0");
    document.getElementById("viewTaskName").innerText = row.dataset.name;
    document.getElementById("viewTaskProject").innerText = row.dataset.projectName || "Chưa có dự án";
    document.getElementById("viewTaskDeadline").innerText = formatDate(row.dataset.deadline);
    document.getElementById("viewTaskStatus").innerText = translateTaskStatus(row.dataset.status);
    document.getElementById("viewTaskPriority").innerText = translateTaskPriority(row.dataset.priority);
    document.getElementById("viewTaskDescription").innerText = row.dataset.description || "Không có mô tả.";
}

function translateTaskStatus(status) {
    if (status === "Pending")
        return "Chưa bắt đầu";
    if (status === "In Progress")
        return "Đang thực hiện";
    if (status === "Completed")
        return "Hoàn thành";
    return status;
}

function translateTaskPriority(priority) {
    if (priority === "Low")
        return "Thấp";
    if (priority === "Medium")
        return "Trung bình";
    if (priority === "High")
        return "Cao";
    return priority;
}

function openAddModal() {
    document.getElementById("task-form").reset();
    document.getElementById("inputAction").value = "add";
    document.getElementById("modalTitle").innerText = "Thêm công việc";
}

function openEditModal(button) {
    const row = button.closest(".task-row");

    document.getElementById("inputAction").value = "edit";
    document.getElementById("modalTitle").innerText = "Cập nhật công việc";
    document.getElementById("displayId").value = "TSK" + String(row.dataset.taskId).padStart(3, "0");

    document.getElementById("inputTaskId").value = row.dataset.taskId;
    document.getElementById("inputTaskName").value = row.dataset.name;
    document.getElementById("inputDescription").value = row.dataset.description;
    document.getElementById("inputDeadline").value = row.dataset.deadline;
    document.getElementById("inputStatus").value = row.dataset.status;
    document.getElementById("inputPriority").value = row.dataset.priority;
    document.getElementById("inputProjectId").value = row.dataset.projectId;
}

function openDeleteModal(button) {
    const row = button.closest(".task-row");
    const tskCode = "TSK" + String(row.dataset.taskId).padStart(3, "0");

    document.getElementById("deleteMessage").innerText =
            "Bạn có chắc muốn xóa công việc " + tskCode + "?";

    const deleteId = document.getElementById("deleteId");
    deleteId.name = "taskId";
    deleteId.value = row.dataset.taskId;

    document.getElementById("deleteForm").action = "task";
}

let searchTimer;

function loadTasks(page = 1) {
    const keyword = document.getElementById("searchInput").value;
    const priority = document.getElementById("priorityFilter").value;
    const status = document.getElementById("statusFilter").value;
    const projectId = document.getElementById("projectFilter").value;

    fetch("task?keyword=" + encodeURIComponent(keyword)
            + "&priority=" + encodeURIComponent(priority)
            + "&status=" + encodeURIComponent(status)
            + "&projectId=" + encodeURIComponent(projectId)
            + "&page=" + page)
            .then(function (res) {
                return res.text();
            })
            .then(function (html) {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, "text/html");

                const newTable = doc.querySelector(".page-container .table-card");
                const currentTable = document.querySelector(".page-container .table-card");

                if (newTable && currentTable) {
                    currentTable.outerHTML = newTable.outerHTML;
                }

                const newFooter = doc.querySelector(".page-container .table-footer");
                const currentFooter = document.querySelector(".page-container .table-footer");

                if (newFooter && currentFooter) {
                    currentFooter.outerHTML = newFooter.outerHTML;
                }
            });
}

document.getElementById("searchInput").addEventListener("input", function () {
    clearTimeout(searchTimer);

    searchTimer = setTimeout(function () {
        loadTasks(1);
    }, 300);
});

document.getElementById("priorityFilter").addEventListener("change", function () { loadTasks(1); });
document.getElementById("statusFilter").addEventListener("change", function () { loadTasks(1); });
document.getElementById("projectFilter").addEventListener("change", function () { loadTasks(1); });

