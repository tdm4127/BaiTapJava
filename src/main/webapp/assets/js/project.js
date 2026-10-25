// Đổi ngày từ yyyy-MM-dd sang dd/MM/yyyy khi xem chi tiết.
function formatDate(value) {
    return value ? value.split("-").reverse().join("/") : "—";
}

// Xử lý popup và nút thao tác dự án.
function openViewModal(button) {
    const row = button.closest(".project-row");

    document.getElementById("viewProjectCode").innerText =
            "PRJ" + String(row.dataset.id).padStart(3, "0");
    document.getElementById("viewProjectName").innerText = row.dataset.name;
    document.getElementById("viewProjectStart").innerText = formatDate(row.dataset.start);
    document.getElementById("viewProjectEnd").innerText = formatDate(row.dataset.end);
    document.getElementById("viewProjectDuration").innerText = row.dataset.duration + " ngày";
    document.getElementById("viewProjectTaskCount").innerText = row.dataset.taskCount + " công việc";
    document.getElementById("viewProjectCompleted").innerText = row.dataset.completedCount + " công việc";
    document.getElementById("viewProjectProgress").innerText = row.dataset.progress + "%";
    document.getElementById("viewProjectDescription").innerText = row.dataset.description || "Không có mô tả.";

    const taskList = document.getElementById("viewProjectTaskList");
    const taskTotal = document.getElementById("viewProjectTaskTotal");
    const template = document.getElementById("projectTaskTemplates");
    const taskRows = template.content.querySelectorAll('tr[data-project-id="' + row.dataset.id + '"]');

    taskList.innerHTML = "";
    taskTotal.innerText = taskRows.length + " công việc";

    if (taskRows.length === 0) {
        taskList.innerHTML = '<tr><td colspan="5" class="empty-row">Dự án chưa có công việc.</td></tr>';
        return;
    }

    taskRows.forEach(taskRow => {
        taskList.appendChild(taskRow.cloneNode(true));
    });
}

function openAddModal() {
    document.getElementById("project-form").reset();
    document.getElementById("inputAction").value = "add";
    document.getElementById("modalTitle").innerText = "Thêm dự án";

    const today = new Date();
    const tomorrow = new Date();
    tomorrow.setDate(today.getDate() + 1);

    document.getElementById("inputStartDate").value = today.toISOString().split("T")[0];
    document.getElementById("inputEndDate").value = tomorrow.toISOString().split("T")[0];

    calculateDuration();
}

function openEditModal(button) {
    const row = button.closest(".project-row");

    document.getElementById("inputAction").value = "edit";
    document.getElementById("modalTitle").innerText = "Cập nhật dự án";
    document.getElementById("displayId").value = "PRJ" + String(row.dataset.id).padStart(3, "0");
    document.getElementById("inputProjectId").value = row.dataset.id;
    document.getElementById("inputProjectName").value = row.dataset.name;
    document.getElementById("inputStartDate").value = row.dataset.start;
    document.getElementById("inputEndDate").value = row.dataset.end;
    document.getElementById("inputDescription").value = row.dataset.description;

    calculateDuration();
}

function openDeleteModal(button) {
    const row = button.closest(".project-row");
    const prjCode = "PRJ" + String(row.dataset.id).padStart(3, "0");

    document.getElementById("deleteMessage").innerText =
            "Bạn có chắc muốn xóa dự án " + prjCode + "?";

    const deleteId = document.getElementById("deleteId");
    deleteId.name = "projectId";
    deleteId.value = row.dataset.id;

    document.getElementById("deleteForm").action = "project";
}

function calculateDuration() {
    const start = document.getElementById("inputStartDate").value;
    const end = document.getElementById("inputEndDate").value;
    const duration = document.getElementById("inputDuration");

    if (!start || !end) {
        duration.value = "";
        return;
    }

    const startDate = new Date(start);
    const endDate = new Date(end);
    const diffTime = endDate - startDate;
    const diffDays = diffTime / (1000 * 60 * 60 * 24);

    duration.value = diffDays;
}

document.getElementById("inputStartDate").addEventListener("change", calculateDuration);
document.getElementById("inputEndDate").addEventListener("change", calculateDuration);

let searchTimer;

function loadProjects(page = 1) {
    const keyword = document.getElementById("searchInput").value;
    const startDate = document.getElementById("startDateFilter").value;
    const endDate = document.getElementById("endDateFilter").value;

    fetch("project?keyword=" + encodeURIComponent(keyword)
            + "&startDate=" + encodeURIComponent(startDate)
            + "&endDate=" + encodeURIComponent(endDate)
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
        loadProjects(1);
    }, 300);
});

document.getElementById("startDateFilter").addEventListener("change", function () { loadProjects(1); });
document.getElementById("endDateFilter").addEventListener("change", function () { loadProjects(1); });