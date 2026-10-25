CREATE DATABASE WorkAssignmentManagement
GO

USE WorkAssignmentManagement
GO

CREATE TABLE Employee (
    EmployeeId INT IDENTITY(1,1) PRIMARY KEY,
    EmployeeName NVARCHAR(100) NOT NULL,
    Position NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NULL,
    PhoneNumber NVARCHAR(100) NULL
)
GO

CREATE UNIQUE INDEX UQ_Employee_Email
ON Employee(Email) WHERE Email IS NOT NULL;
GO

CREATE UNIQUE INDEX UQ_Employee_PhoneNumber
ON Employee(PhoneNumber) WHERE PhoneNumber IS NOT NULL;
GO

CREATE TABLE Project (
    ProjectId INT IDENTITY(1,1) PRIMARY KEY,
    ProjectName NVARCHAR(100) NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    [Description] NVARCHAR(MAX) NULL
)
GO

CREATE TABLE Task (
    TaskId INT IDENTITY(1,1) PRIMARY KEY,
    TaskName NVARCHAR(100) NOT NULL,
    [Description] NVARCHAR(MAX) NULL,
    Deadline DATE NOT NULL,
    [Status] NVARCHAR(30) NOT NULL,
    [Priority] NVARCHAR(20) NOT NULL,
    ProjectId INT NOT NULL,
    CONSTRAINT FK_Task_Project FOREIGN KEY (ProjectId) REFERENCES Project(ProjectId)
)
GO

CREATE TABLE Assignment (
    AssignmentId INT IDENTITY(1,1) PRIMARY KEY,
    TaskId INT NOT NULL,
    EmployeeId INT NOT NULL,
    AssignedDate DATE NOT NULL,
    CONSTRAINT FK_Assignment_Task FOREIGN KEY (TaskId) REFERENCES Task(TaskId),
    CONSTRAINT FK_Assignment_Employee FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),
    CONSTRAINT UQ_Assignment_Task_Employee UNIQUE (TaskId, EmployeeId)
)
GO

CREATE TABLE Account (
    Username NVARCHAR(50) PRIMARY KEY,
    [Password] NVARCHAR(255) NOT NULL,
    [Role] NVARCHAR(20) NOT NULL,
    EmployeeId INT NOT NULL UNIQUE,
    CONSTRAINT FK_Account_Employee FOREIGN KEY (EmployeeId) REFERENCES Employee(EmployeeId),
    CONSTRAINT CK_Account_Role CHECK ([Role] IN ('admin', 'manager', 'employee'))
);
GO

INSERT INTO Employee (EmployeeName, Position, Email, PhoneNumber)
VALUES
(N'Nguyễn Văn An', N'Quản lý', 'an@email.com', '0901000001'),
(N'Trần Minh Bình', N'Lập trình viên', 'binh@email.com', '0901000002'),
(N'Lê Hoàng Cường', N'Lập trình viên', 'cuong@email.com', '0901000003'),
(N'Phạm Thu Dung', N'Kiểm thử viên', 'dung@email.com', '0901000004'),
(N'Hoàng Đức Em', N'Thiết kế', 'em@email.com', '0901000005'),
(N'Võ Minh Phúc', N'Lập trình viên', 'phuc@email.com', '0901000006'),
(N'Đặng Ngọc Hân', N'Kiểm thử viên', 'han@email.com', '0901000007'),
(N'Bùi Quốc Huy', N'Thiết kế', 'huy@email.com', '0901000008'),
(N'Đỗ Thanh Lan', N'Phân tích viên', 'lan@email.com', '0901000009'),
(N'Ngô Gia Minh', N'Lập trình viên', 'minh@email.com', '0901000010');
GO

INSERT INTO Project (ProjectName, StartDate, EndDate, [Description])
VALUES
(N'Hệ thống quản lý nhân viên', '2026-09-01', '2026-11-30', N'Xây dựng hệ thống quản lý thông tin nhân viên'),
(N'Website bán hàng', '2026-09-15', '2026-12-15', N'Xây dựng website bán hàng trực tuyến'),
(N'Hệ thống quản lý công việc', '2026-10-01', '2027-01-15', N'Xây dựng hệ thống quản lý và phân công công việc');
GO

INSERT INTO Task (TaskName, [Description], Deadline, [Status], [Priority], ProjectId)
VALUES
(N'Thiết kế cơ sở dữ liệu', N'Thiết kế các bảng và quan hệ', '2026-09-20', N'Hoàn thành', N'Cao', 1),
(N'Tạo giao diện nhân viên', N'Xây dựng giao diện quản lý nhân viên', '2026-10-05', N'Hoàn thành', N'Trung bình', 1),
(N'Xây dựng chức năng tìm kiếm', N'Tìm kiếm và lọc danh sách nhân viên', '2026-10-20', N'Đang thực hiện', N'Cao', 1),
(N'Thiết kế trang sản phẩm', N'Xây dựng giao diện danh sách sản phẩm', '2026-10-15', N'Đang thực hiện', N'Trung bình', 2),
(N'Xây dựng giỏ hàng', N'Xử lý thêm và xóa sản phẩm trong giỏ hàng', '2026-11-05', N'Chưa bắt đầu', N'Cao', 2),
(N'Kiểm thử website', N'Kiểm thử các chức năng của website', '2026-12-01', N'Chưa bắt đầu', N'Trung bình', 2),
(N'Thiết kế Dashboard', N'Xây dựng giao diện tổng quan hệ thống', '2026-10-25', N'Đang thực hiện', N'Trung bình', 3),
(N'Xây dựng chức năng phân công', N'Phân công Task cho Employee', '2026-11-10', N'Chưa bắt đầu', N'Cao', 3),
(N'Xây dựng chức năng đăng nhập', N'Đăng nhập và phân quyền người dùng', '2026-11-25', N'Chưa bắt đầu', N'Cao', 3),
(N'Thống kê tiến độ', N'Thống kê trạng thái và tiến độ công việc', '2026-12-10', N'Chưa bắt đầu', N'Thấp', 3);
GO

INSERT INTO Assignment (TaskId, EmployeeId, AssignedDate)
VALUES
(1, 2, '2026-09-05'),
(2, 3, '2026-09-15'),
(3, 6, '2026-10-01'),
(4, 5, '2026-09-20'),
(5, 2, '2026-10-10'),
(6, 4, '2026-10-15'),
(6, 7, '2026-10-15'),
(7, 8, '2026-10-05'),
(8, 6, '2026-10-10'),
(9, 10, '2026-10-15'),
(10, 9, '2026-10-20');
GO

INSERT INTO Account (Username, [Password], [Role], EmployeeId)
VALUES
('admin',   '123456', 'admin',    1),
('EMP002',  'EMP002', 'employee', 2),
('EMP003',  'EMP003', 'employee', 3),
('EMP004',  'EMP004', 'employee', 4),
('EMP005',  'EMP005', 'employee', 5),
('EMP006',  'EMP006', 'employee', 6),
('EMP007',  'EMP007', 'employee', 7),
('EMP008',  'EMP008', 'employee', 8),
('EMP009',  'EMP009', 'employee', 9),
('EMP010',  'EMP010', 'employee', 10);
GO