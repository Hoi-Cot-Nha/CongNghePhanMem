USE QuanLyHocSinh;
GO
INSERT INTO DoiTuongUuTien (MaDT, TenDT, TiLeGiamHocPhi) 
VALUES 
('DT00', N'Thường', 0.0),
('DT01', N'Hộ nghèo', 0.5), 
('DT02', N'Con thương binh', 1.0);

-- 2. Thêm Giáo viên (để làm GVCN)
INSERT INTO GiaoVien (MaGV, HoTen, NgaySinh, SDT) 
VALUES 
('GV01', N'Nguyễn Bá Đạt', '1985-05-20', '0901234567'), 
('GV02', N'Trần Thu Trang', '1990-11-15', '0912345678'),
('GV03', N'Phạm Minh Tuấn', '1988-02-10', '0987654321'),
('GV04', N'Lê Thị Mai', '1992-07-25', '0977123456'),
('GV05', N'Hoàng Văn Nam', '1980-12-30', '0966234567'),
('GV06', N'Vũ Phương Thảo', '1995-04-12', '0955345678'),
('GV07', N'Đặng Quốc Bảo', '1987-09-05', '0944456789'),
('GV08', N'Bùi Tuyết Nhung', '1991-01-20', '0933567890'),
('GV09', N'Ngô Gia Huy', '1984-06-15', '0922678901'),
('GV10', N'Đỗ Thùy Linh', '1993-08-28', '0911789012'),
('GV11', N'Trương Công Định', '1982-03-14', '0900890123'),
('GV12', N'Phan Thanh Bình', '1989-10-10', '0899901234');

-- 3. Thêm Lớp
INSERT INTO Lop (MaLop, TenLop, NienKhoa, MaGVCN) 
VALUES 
-- Khối 10 (Niên khóa 2023-2026)
('10A1', N'10A1', '2023-2026', 'GV01'), 
('10A2', N'10A2', '2023-2026', 'GV02'),
('10A3', N'10A3', '2023-2026', 'GV03'),
('10A4', N'10A4', '2023-2026', 'GV04'),
('10A5', N'10A5', '2023-2026', 'GV05'),
('10A6', N'10A6', '2023-2026', 'GV06'),
('10A7', N'10A7', '2023-2026', 'GV07'),

-- Khối 11 (Niên khóa 2022-2025)
('11A1', N'11A1', '2022-2025', 'GV08'),
('11A2', N'11A2', '2022-2025', 'GV09'),

-- Khối 12 (Niên khóa 2021-2024)
('12A1', N'12A1', '2021-2024', 'GV10'),
('12A2', N'12A2', '2021-2024', 'GV11'),
('12A3', N'12A3', '2021-2024', 'GV12');
go
-- 4. THÊM HỌC SINH (50 học sinh phân bổ đều cho 12 lớp)
-- Lưu ý: DT00 = Thường, DT01 = Hộ nghèo, DT02 = Con thương binh

-- KHỐI 10 (Sinh năm 2008)
INSERT INTO HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) VALUES 
('HS001', N'Nguyễn Thị Mai', '2008-10-01', N'Nữ', N'Hà Nội', '10A1', 'DT00'),
('HS002', N'Trần Thị Lan', '2008-02-20', N'Nữ', N'Bắc Ninh', '10A1', 'DT01'),
('HS003', N'Lê Văn Tuấn', '2008-01-28', N'Nam', N'Thái Nguyên', '10A2', 'DT02'),
('HS004', N'Phạm Văn Minh', '2008-05-03', N'Nam', N'Hải Phòng', '10A2', 'DT00'),
('HS005', N'Hoàng Thị Thu', '2008-07-11', N'Nữ', N'Nam Định', '10A3', 'DT00'),
('HS006', N'Vũ Thị Hoa', '2008-01-15', N'Nữ', N'Thái Bình', '10A3', 'DT02'),
('HS007', N'Võ Văn Dũng', '2008-10-13', N'Nam', N'Hưng Yên', '10A4', 'DT00'),
('HS008', N'Đặng Văn Thành', '2008-05-24', N'Nam', N'Hải Dương', '10A4', 'DT00'),
('HS009', N'Bùi Văn Hùng', '2008-09-27', N'Nam', N'Vĩnh Phúc', '10A5', 'DT00'),
('HS010', N'Đỗ Thị Hằng', '2008-07-08', N'Nữ', N'Hà Nam', '10A5', 'DT02'),
('HS011', N'Nguyễn Thị Phương', '2008-04-19', N'Nữ', N'Hà Nội', '10A6', 'DT00'),
('HS012', N'Trần Văn Đạt', '2008-03-21', N'Nam', N'Bắc Ninh', '10A6', 'DT01'),
('HS013', N'Lê Văn Khoa', '2008-05-26', N'Nam', N'Thái Nguyên', '10A7', 'DT00'),
('HS014', N'Phạm Văn Hải', '2008-04-01', N'Nam', N'Hải Phòng', '10A7', 'DT00'),
('HS015', N'Hoàng Văn Nam', '2008-05-22', N'Nam', N'Nam Định', '10A1', 'DT00'),
('HS016', N'Vũ Văn Phong', '2008-04-06', N'Nam', N'Thái Bình', '10A2', 'DT00'),
('HS017', N'Võ Thị Ngọc', '2008-11-26', N'Nữ', N'Hưng Yên', '10A3', 'DT00');

-- KHỐI 11 (Sinh năm 2007)
INSERT INTO HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) VALUES 
('HS018', N'Đặng Văn Sơn', '2007-10-06', N'Nam', N'Hải Dương', '11A1', 'DT02'),
('HS019', N'Bùi Văn Thắng', '2007-12-03', N'Nam', N'Vĩnh Phúc', '11A1', 'DT00'),
('HS020', N'Đỗ Văn Long', '2007-12-01', N'Nam', N'Hà Nam', '11A1', 'DT00'),
('HS021', N'Nguyễn Thị Trâm', '2007-06-16', N'Nữ', N'Hà Nội', '11A1', 'DT02'),
('HS022', N'Trần Văn Kiên', '2007-03-27', N'Nam', N'Bắc Ninh', '11A2', 'DT00'),
('HS023', N'Lê Thị Hương', '2007-03-06', N'Nữ', N'Thái Nguyên', '11A2', 'DT02'),
('HS024', N'Phạm Thị Oanh', '2007-04-04', N'Nữ', N'Hải Phòng', '11A2', 'DT02'),
('HS025', N'Hoàng Thị Yến', '2007-09-25', N'Nữ', N'Nam Định', '11A2', 'DT02'),
('HS026', N'Vũ Thị Hà', '2007-03-20', N'Nữ', N'Thái Bình', '11A1', 'DT01'),
('HS027', N'Võ Văn Lâm', '2007-11-30', N'Nam', N'Hưng Yên', '11A2', 'DT00'),
('HS028', N'Đặng Văn Tú', '2007-04-07', N'Nam', N'Hải Dương', '11A1', 'DT01'),
('HS029', N'Bùi Thị Thảo', '2007-06-15', N'Nữ', N'Vĩnh Phúc', '11A2', 'DT00'),
('HS030', N'Đỗ Văn Tiến', '2007-03-21', N'Nam', N'Hà Nam', '11A1', 'DT00'),
('HS031', N'Nguyễn Văn Quân', '2007-03-24', N'Nam', N'Hà Nội', '11A2', 'DT00');

-- KHỐI 12 (Sinh năm 2006)
INSERT INTO HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) VALUES 
('HS032', N'Trần Văn Bình', '2006-02-11', N'Nam', N'Bắc Ninh', '12A1', 'DT00'),
('HS033', N'Lê Văn Tâm', '2006-07-11', N'Nam', N'Thái Nguyên', '12A1', 'DT00'),
('HS034', N'Phạm Thị Linh', '2006-07-21', N'Nữ', N'Hải Phòng', '12A1', 'DT00'),
('HS035', N'Hoàng Văn Tùng', '2006-12-17', N'Nam', N'Nam Định', '12A2', 'DT00'),
('HS036', N'Vũ Văn Toàn', '2006-12-05', N'Nam', N'Thái Bình', '12A2', 'DT00'),
('HS037', N'Võ Văn Quyết', '2006-05-17', N'Nam', N'Hưng Yên', '12A2', 'DT00'),
('HS038', N'Đặng Văn Nghĩa', '2006-08-23', N'Nam', N'Hải Dương', '12A3', 'DT00'),
('HS039', N'Bùi Văn Trọng', '2006-02-05', N'Nam', N'Vĩnh Phúc', '12A3', 'DT00'),
('HS040', N'Đỗ Văn Cường', '2006-11-23', N'Nam', N'Hà Nam', '12A3', 'DT00'),
('HS041', N'Nguyễn Thị Quyên', '2006-04-29', N'Nữ', N'Hà Nội', '12A1', 'DT00'),
('HS042', N'Trần Văn Hoàng', '2006-06-16', N'Nam', N'Bắc Ninh', '12A2', 'DT00'),
('HS043', N'Lê Thị Thu', '2006-05-22', N'Nữ', N'Thái Nguyên', '12A3', 'DT00'),
('HS044', N'Phạm Thị Tuyết', '2006-08-06', N'Nữ', N'Hải Phòng', '12A1', 'DT00'),
('HS045', N'Hoàng Văn Khang', '2006-11-21', N'Nam', N'Nam Định', '12A2', 'DT00'),
('HS046', N'Vũ Văn Hưng', '2006-03-08', N'Nam', N'Thái Bình', '12A3', 'DT00'),
('HS047', N'Võ Văn Huy', '2006-05-29', N'Nam', N'Hưng Yên', '12A1', 'DT00'),
('HS048', N'Đặng Văn Hiếu', '2006-09-22', N'Nam', N'Hải Dương', '12A2', 'DT02'),
('HS049', N'Bùi Văn Hào', '2006-12-11', N'Nam', N'Vĩnh Phúc', '12A3', 'DT02'),
('HS050', N'Đỗ Thị Trang', '2006-06-25', N'Nữ', N'Hà Nam', '12A1', 'DT00');

USE QuanLyHocSinh;
GO

-- 1. Thêm dữ liệu cho bảng MÔN HỌC
INSERT INTO MonHoc (MaMH, TenMH) 
VALUES 
('MH01', N'Toán học'),
('MH02', N'Ngữ văn'),
('MH03', N'Tiếng Anh'),
('MH04', N'Vật lý'),
('MH05', N'Hóa học'),
('MH06', N'Sinh học'),
('MH07', N'Lịch sử'),
('MH08', N'Địa lý'),
('MH09', N'Giáo dục công dân'),
('MH10', N'Tin học');


ALTER TABLE PhongHoc 
ALTER COLUMN TinhTrang NVARCHAR(50); -- Bạn có thể đổi số 50 thành độ dài mong muốn
GO
-- 2. Thêm dữ liệu cho bảng PHÒNG HỌC
INSERT INTO PhongHoc (MaPhong, TenPhong, SucChua, LoaiPhong, TinhTrang)
VALUES 
('P101', N'Phòng 101', 45, N'Lý thuyết', N'Trống'),
('P102', N'Phòng 102', 45, N'Lý thuyết', N'Trống'),
('P201', N'Phòng 201', 45, N'Lý thuyết', N'Trống'),
('LAB1', N'Phòng Máy tính 1', 50, N'Thực hành', N'Trống'),
('LAB2', N'Phòng Thí nghiệm Hóa Sinh', 40, N'Thực hành', N'Trống');

-- 3. Thêm dữ liệu cho bảng TỔ HỢP MÔN
INSERT INTO ToHopMon (MaToHop, TenToHop)
VALUES
('KHTN', N'Khoa học Tự nhiên (Lý, Hóa, Sinh)'),
('KHXH', N'Khoa học Xã hội (Sử, Địa, GDCD)');

GO

USE QuanLyHocSinh;
GO

-- Xóa dữ liệu cũ (nếu có) để đảm bảo không bị lỗi trùng lặp khi chạy lại lệnh
DELETE FROM Diem;
GO

-- BƯỚC 1: TẠO ĐIỂM NGẪU NHIÊN CHO TẤT CẢ HỌC SINH VÀ MÔN HỌC (HỌC KỲ 1)
-- Sử dụng CROSS JOIN để ghép từng Học Sinh với từng Môn Học
INSERT INTO Diem (MaHS, MaMH, HocKy, Diem15p, Diem1Tiet, DiemGiuaKy, DiemCuoiKy)
SELECT 
    h.MaHS,
    m.MaMH,
    1 AS HocKy, -- Mặc định tạo dữ liệu cho Học kỳ 1
    
    -- Công thức sinh số thập phân ngẫu nhiên từ 0.0 đến 10.0 (làm tròn 1 chữ số)
    ROUND(RAND(CHECKSUM(NEWID())) * 10, 1) AS Diem15p, 
    ROUND(RAND(CHECKSUM(NEWID())) * 10, 1) AS Diem1Tiet,
    ROUND(RAND(CHECKSUM(NEWID())) * 10, 1) AS DiemGiuaKy,
    ROUND(RAND(CHECKSUM(NEWID())) * 10, 1) AS DiemCuoiKy
FROM 
    HocSinh h
CROSS JOIN 
    MonHoc m;
GO

-- BƯỚC 2: TÍNH TOÁN ĐIỂM TỔNG KẾT DỰA TRÊN CÁC ĐIỂM THÀNH PHẦN
-- Giả định hệ số: 15p (hs1), 1 Tiết (hs2), Giữa Kỳ (hs2), Cuối Kỳ (hs3). Tổng hệ số = 8
UPDATE Diem
SET DiemTongKet = ROUND((Diem15p + Diem1Tiet * 2 + DiemGiuaKy * 2 + DiemCuoiKy * 3) / 8.0, 1);
GO

USE QuanLyHocSinh;
GO

-- ==========================================
-- BƯỚC 1: TẠO DỮ LIỆU BẢNG HẠNH KIỂM
-- ==========================================
DELETE FROM HanhKiem;
GO
ALTER TABLE HanhKiem 
ALTER COLUMN XepLoai NVARCHAR(50); -- Bạn có thể đổi số 50 thành độ dài mong muốn
GO
INSERT INTO HanhKiem (MaHS, HocKy, NamHoc, XepLoai, NhanXet)
SELECT 
    MaHS,
    1 AS HocKy,
    N'2025-2026' AS NamHoc,
    -- Dùng hàm CHOOSE kết hợp NEWID() để bốc thăm ngẫu nhiên 1 trong 4 kết quả
    CHOOSE(ABS(CHECKSUM(NEWID())) % 4 + 1, N'Tốt', N'Khá', N'Trung bình', N'Tốt') AS XepLoai,
    N'Tuân thủ tốt nội quy trường lớp.' AS NhanXet
FROM 
    HocSinh;
GO
USE QuanLyHocSinh;
GO

-- ==========================================
-- BƯỚC 2: TẠO DỮ LIỆU BẢNG HỌC PHÍ (ĐÃ SỬA LỖI TÊN BIẾN)
-- ==========================================
DELETE FROM HocPhi;
GO

-- Khai báo mức học phí gốc
DECLARE @MucHocPhiGoc DECIMAL(18,0) = 2000000;

INSERT INTO HocPhi (MaHS, HocKy, NamHoc, TongTien, MienGiam, PhaiDong, TrangThai)
SELECT 
    hs.MaHS,
    1 AS HocKy,
    N'2025-2026' AS NamHoc,
    @MucHocPhiGoc AS TongTien,
    
    -- Tính tiền miễn giảm (Dùng ISNULL để ép về 0 nếu học sinh không thuộc đối tượng ưu tiên)
    ISNULL(dt.TiLeGiamHocPhi, 0) * @MucHocPhiGoc AS MienGiam,
    
    -- Tính tiền phải đóng
    @MucHocPhiGoc - (ISNULL(dt.TiLeGiamHocPhi, 0) * @MucHocPhiGoc) AS PhaiDong,
    
    -- Random 3 trạng thái: 1 = Chưa đóng, 2 = Đã đóng, 3 = Bảo lưu
    CHOOSE(ABS(CHECKSUM(NEWID())) % 3 + 1, N'Chưa đóng', N'Đã đóng', N'Bảo lưu') AS TrangThai
FROM 
    HocSinh hs
LEFT JOIN 
    DoiTuongUuTien dt ON hs.MaDT = dt.MaDT;
GO

USE QuanLyHocSinh;
GO

-- ==========================================
-- 1. BẢNG TÀI KHOẢN
-- ==========================================
DELETE FROM TaiKhoan;
INSERT INTO TaiKhoan (TenDangNhap, MatKhau, Quyen, MaNguoiDung) 
VALUES
('admin', '123456', N'Admin', 'AD01'),
('gv01', '123456', N'GiaoVien', 'GV01'),
('hs001', '123456', N'HocSinh', 'HS001');

-- ==========================================
-- 2. BẢNG THÔNG BÁO
-- ==========================================
DELETE FROM ThongBao;
INSERT INTO ThongBao (TieuDe, NoiDung, NguoiGui) 
VALUES
(N'Lịch nghỉ Lễ 30/04 và 01/05', N'Toàn trường được nghỉ học từ ngày 30/04 đến hết 03/05. Ngày 04/05 đi học lại bình thường.', 'AD01'),
(N'Nhắc nhở nộp học phí Học kỳ 1', N'Yêu cầu các em học sinh hoàn thành học phí HK1 trước ngày 15/10/2025.', 'AD01');

-- ==========================================
-- 3. BẢNG LỊCH THI
-- ==========================================
DELETE FROM LichThi;
INSERT INTO LichThi (TenKyThi, MaMH, NgayThi, GioBatDau, GioKetThuc, MaPhong) 
VALUES
(N'Thi Giữa Kỳ 1', 'MH01', '2026-10-15', '07:30', '09:00', 'P101'), -- Toán
(N'Thi Giữa Kỳ 1', 'MH02', '2026-10-16', '07:30', '09:00', 'P102'); -- Văn

-- ==========================================
-- 4. BẢNG PHÚC KHẢO
-- ==========================================
DELETE FROM PhucKhao;
INSERT INTO PhucKhao (MaHS, MaMH, LyDo, TrangThai) 
VALUES
('HS001', 'MH01', N'Em tính lại điểm tự luận thì thấy cao hơn điểm công bố trên phần mềm.', N'Đang chờ xử lý'),
('HS005', 'MH02', N'Điểm trên hệ thống bị nhập sai so với điểm trong bài kiểm tra giấy.', N'Đã giải quyết');

-- ==========================================
-- 5. BẢNG THỜI KHÓA BIỂU
-- ==========================================
DELETE FROM ThoiKhoaBieu;
INSERT INTO ThoiKhoaBieu (MaLop, MaMH, MaGV, MaPhong, Thu, TietBatDau, TietKetThuc) 
VALUES
('10A1', 'MH01', 'GV01', 'P101', 2, 1, 2), -- Lớp 10A1 học Toán (GV01) ở P101, Thứ 2, tiết 1 đến 2
('10A1', 'MH02', 'GV02', 'P102', 2, 3, 4), -- Lớp 10A1 học Văn (GV02) ở P102, Thứ 2, tiết 3 đến 4
('10A2', 'MH01', 'GV01', 'P101', 3, 1, 2), -- Lớp 10A2 học Toán (GV01) ở P101, Thứ 3, tiết 1 đến 2
('11A1', 'MH02', 'GV02', 'P201', 4, 1, 2); -- Lớp 11A1 học Văn (GV02) ở P201, Thứ 4, tiết 1 đến 2
GO