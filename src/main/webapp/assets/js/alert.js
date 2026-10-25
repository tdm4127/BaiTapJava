// Tự ẩn thông báo sau một khoảng thời gian.
setTimeout(() => {
    const alert = document.querySelector(".alert");
    if (alert) {
        alert.remove();
    }
}, 3000);