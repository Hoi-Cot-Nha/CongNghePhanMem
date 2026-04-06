/*USE master;
GO

-- Ngắt các kết nối đang sử dụng database này (nếu có)
ALTER DATABASE QuanLyHocSinh SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

-- Xóa hoàn toàn database
DROP DATABASE QuanLyHocSinh;
GO
*/
-- 1. TẠO CƠ SỞ DỮ LIỆU
CREATE DATABASE QuanLyHocSinh;
GO
USE QuanLyHocSinh;
GO

-- =============================================
-- PHẦN 1: CÁC BẢNG DANH MỤC CỐT LÕI (Không chứa khóa ngoại - Tạo trước)
-- =============================================

-- 1. Bảng TỔ HỢP MÔN
CREATE TABLE ToHopMon (
    MaToHop VARCHAR(10) PRIMARY KEY,
    TenToHop NVARCHAR(100) NOT NULL
);

-- 2. Bảng PHÒNG HỌC
CREATE TABLE PhongHoc (
    MaPhong VARCHAR(10) PRIMARY KEY,
    TenPhong NVARCHAR(50),
    SucChua INT,
    LoaiPhong NVARCHAR(50), 
    TinhTrang NVARCHAR(50) DEFAULT N'Trống' 
);

-- 3. Bảng MÔN HỌC
CREATE TABLE MonHoc (
    MaMH VARCHAR(10) PRIMARY KEY,
    TenMH NVARCHAR(100) NOT NULL
);

-- 4. Bảng ĐỐI TƯỢNG ƯU TIÊN
CREATE TABLE DoiTuongUuTien (
    MaDT VARCHAR(10) PRIMARY KEY,
    TenDT NVARCHAR(100), 
    TiLeGiamHocPhi FLOAT 
);

-- 8. Bảng TÀI KHOẢN (Đưa lên trên vì không có khóa ngoại ràng buộc cứng)
CREATE TABLE TaiKhoan (
    TenDangNhap VARCHAR(50) PRIMARY KEY,
    MatKhau VARCHAR(100) NOT NULL,
    Quyen NVARCHAR(20), 
    MaNguoiDung VARCHAR(10) 
);

-- =============================================
-- PHẦN 2: CÁC BẢNG NHÂN SỰ & TỔ CHỨC (Có khóa ngoại)
-- =============================================

-- 5. Bảng GIÁO VIÊN
CREATE TABLE GiaoVien (
    MaGV VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE,
    SDT VARCHAR(15),
    MaToHop VARCHAR(10), 
    -- Xóa Tổ hợp -> Cập nhật MaToHop thành NULL ở bảng Giáo viên
    CONSTRAINT FK_GiaoVien_ToHop FOREIGN KEY (MaToHop) REFERENCES ToHopMon(MaToHop) ON DELETE SET NULL ON UPDATE CASCADE
);

-- 6. Bảng LỚP HỌC
CREATE TABLE Lop (
    MaLop VARCHAR(10) PRIMARY KEY,
    TenLop NVARCHAR(50) NOT NULL,
    NienKhoa NVARCHAR(20),
    MaGVCN VARCHAR(10), 
    -- Giáo viên nghỉ việc -> Lớp tạm thời trống GVCN (NULL)
    CONSTRAINT FK_Lop_GiaoVien FOREIGN KEY (MaGVCN) REFERENCES GiaoVien(MaGV) ON DELETE SET NULL ON UPDATE CASCADE
);

-- 7. Bảng HỌC SINH
CREATE TABLE HocSinh (
    MaHS VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(50) NOT NULL,
    NgaySinh DATE,
    GioiTinh NVARCHAR(10),
    DiaChi NVARCHAR(200),
    MaLop VARCHAR(10),
    MaDT VARCHAR(10),
    -- Xóa lớp -> Xóa học sinh (CASCADE)
    CONSTRAINT FK_HocSinh_Lop FOREIGN KEY (MaLop) REFERENCES Lop(MaLop) ON DELETE CASCADE ON UPDATE CASCADE,
    -- Xóa đối tượng ưu tiên -> Cập nhật MaDT của học sinh thành NULL
    CONSTRAINT FK_HocSinh_DTUT FOREIGN KEY (MaDT) REFERENCES DoiTuongUuTien(MaDT) ON DELETE SET NULL ON UPDATE CASCADE
);

-- =============================================
-- PHẦN 3: CÁC BẢNG NGHIỆP VỤ (Phụ thuộc nhiều vào danh mục và nhân sự)
-- =============================================

-- 9. Bảng THỜI KHÓA BIỂU
CREATE TABLE ThoiKhoaBieu (
    MaTKB INT IDENTITY(1,1) PRIMARY KEY,
    MaLop VARCHAR(10),
    MaMH VARCHAR(10),
    MaGV VARCHAR(10),
    MaPhong VARCHAR(10),
    Thu INT CHECK (Thu >= 2 AND Thu <= 8), 
    TietBatDau INT CHECK (TietBatDau >= 1 AND TietBatDau <= 15),
    TietKetThuc INT CHECK (TietKetThuc >= 1 AND TietKetThuc <= 15),
    CONSTRAINT FK_TKB_Lop FOREIGN KEY (MaLop) REFERENCES Lop(MaLop) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_TKB_MonHoc FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_TKB_GiaoVien FOREIGN KEY (MaGV) REFERENCES GiaoVien(MaGV),
    CONSTRAINT FK_TKB_PhongHoc FOREIGN KEY (MaPhong) REFERENCES PhongHoc(MaPhong),
    CONSTRAINT CK_TietHopLe CHECK (TietKetThuc >= TietBatDau)
);

-- 10. Bảng ĐIỂM SỐ
CREATE TABLE Diem (
    MaHS VARCHAR(10),
    MaMH VARCHAR(10),
    HocKy INT, 
    Diem15p FLOAT,
    Diem1Tiet FLOAT,
    DiemGiuaKy FLOAT, 
    DiemCuoiKy FLOAT,
    DiemTongKet FLOAT,
    PRIMARY KEY (MaHS, MaMH, HocKy),
    -- Xóa học sinh -> Xóa điểm (CASCADE)
    CONSTRAINT FK_Diem_HocSinh FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Diem_MonHoc FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 11. Bảng HẠNH KIỂM
CREATE TABLE HanhKiem (
    MaHS VARCHAR(10),
    HocKy INT,
    NamHoc NVARCHAR(20),
    XepLoai NVARCHAR(20), 
    NhanXet NVARCHAR(200),
    PRIMARY KEY (MaHS, HocKy, NamHoc),
    -- Xóa học sinh -> Xóa hạnh kiểm (CASCADE)
    CONSTRAINT FK_HanhKiem_HocSinh FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 12. Bảng LỊCH THI
CREATE TABLE LichThi (
    MaLT INT IDENTITY(1,1) PRIMARY KEY,
    TenKyThi NVARCHAR(50), 
    MaMH VARCHAR(10),
    NgayThi DATE,
    GioBatDau TIME, 
    GioKetThuc TIME, 
    MaPhong VARCHAR(10),
    CONSTRAINT FK_LichThi_MonHoc FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_LichThi_PhongHoc FOREIGN KEY (MaPhong) REFERENCES PhongHoc(MaPhong) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 13. Bảng HỌC PHÍ
CREATE TABLE HocPhi (
    MaHP INT IDENTITY(1,1) PRIMARY KEY,
    MaHS VARCHAR(10),
    HocKy INT,
    NamHoc NVARCHAR(20),
    TongTien DECIMAL(18,0), 
    MienGiam DECIMAL(18,0), 
    PhaiDong DECIMAL(18,0), 
    TrangThai NVARCHAR(20), 
    -- Xóa học sinh -> Xóa thông tin học phí (CASCADE)
    CONSTRAINT FK_HocPhi_HocSinh FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 14. Bảng THÔNG BÁO
CREATE TABLE ThongBao (
    MaTB INT IDENTITY(1,1) PRIMARY KEY,
    TieuDe NVARCHAR(200),
    NoiDung NTEXT,
    NgayTao DATE DEFAULT GETDATE(),
    NguoiGui VARCHAR(10) 
);

-- 15. Bảng PHÚC KHẢO
CREATE TABLE PhucKhao (
    MaPK INT IDENTITY(1,1) PRIMARY KEY,
    MaHS VARCHAR(10),
    MaMH VARCHAR(10),
    LyDo NVARCHAR(500),
    NgayGui DATE DEFAULT GETDATE(),
    TrangThai NVARCHAR(50), 
    CONSTRAINT FK_PhucKhao_HocSinh FOREIGN KEY (MaHS) REFERENCES HocSinh(MaHS) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_PhucKhao_MonHoc FOREIGN KEY (MaMH) REFERENCES MonHoc(MaMH) ON DELETE CASCADE ON UPDATE CASCADE
);
GO

