USE QuanLyHocSinh;
GO

-- 1. Thêm Đối tượng ưu tiên
INSERT INTO DoiTuongUuTien (MaDT, TenDT, TiLeGiamHocPhi) 
VALUES 
('DT01', N'Hộ nghèo', 0.5), 
('DT02', N'Con thương binh', 1.0);

-- 2. Thêm Giáo viên (để làm GVCN)
INSERT INTO GiaoVien (MaGV, HoTen, NgaySinh, SDT) 
VALUES 
('GV01', N'Nguyễn Bá Đạt', '1985-05-20', '0901234567'), 
('GV02', N'Trần Thu Trang', '1990-11-15', '0912345678');

-- 3. Thêm Lớp
INSERT INTO Lop (MaLop, TenLop, NienKhoa, MaGVCN) 
VALUES 
('L10A1', N'10A1', '2023-2026', 'GV01'), 
('L10A2', N'10A2', '2023-2026', 'GV02'),
('L11A1', N'11A1', '2022-2025', 'GV01');
go

insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS001', N'Nguyễn Thị Mai', '2005-10-01', N'Nữ', N'Hà Nội', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS002', N'Trần Thị Lan', '2008-02-20', N'Nữ', N'Bắc Ninh', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS003', N'Lê Văn Tuấn', '2007-01-28', N'Nam', N'Thái Nguyên', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS004', N'Phạm Văn Minh', '2006-05-03', N'Nam', N'Hải Phòng', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS005', N'Hoàng Thị Thu', '2008-07-11', N'Nữ', N'Nam Định', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS006', N'Vũ Thị Hoa', '2007-01-15', N'Nữ', N'Thái Bình', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS007', N'Võ Văn Dũng', '2006-10-13', N'Nam', N'Hưng Yên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS008', N'Đặng Văn Thành', '2007-05-24', N'Nam', N'Hải Dương', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS009', N'Bùi Văn Hùng', '2006-09-27', N'Nam', N'Vĩnh Phúc', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS010', N'Đỗ Thị Hằng', '2008-07-08', N'Nữ', N'Hà Nam', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS011', N'Nguyễn Thị Phương', '2008-04-19', N'Nữ', N'Hà Nội', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS012', N'Trần Văn Đạt', '2005-03-21', N'Nam', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS013', N'Lê Văn Khoa', '2005-05-26', N'Nam', N'Thái Nguyên', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS014', N'Phạm Văn Hải', '2006-04-01', N'Nam', N'Hải Phòng', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS015', N'Hoàng Văn Nam', '2008-05-22', N'Nam', N'Nam Định', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS016', N'Vũ Văn Phong', '2008-04-06', N'Nam', N'Thái Bình', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS017', N'Võ Thị Ngọc', '2006-11-26', N'Nữ', N'Hưng Yên', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS018', N'Đặng Văn Sơn', '2005-10-06', N'Nam', N'Hải Dương', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS019', N'Bùi Văn Thắng', '2006-12-03', N'Nam', N'Vĩnh Phúc', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS020', N'Đỗ Văn Long', '2005-12-01', N'Nam', N'Hà Nam', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS021', N'Nguyễn Thị Trâm', '2006-06-16', N'Nữ', N'Hà Nội', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS022', N'Trần Văn Kiên', '2007-03-27', N'Nam', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS023', N'Lê Thị Hương', '2008-03-06', N'Nữ', N'Thái Nguyên', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS024', N'Phạm Thị Oanh', '2006-04-04', N'Nữ', N'Hải Phòng', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS025', N'Hoàng Thị Yến', '2006-09-25', N'Nữ', N'Nam Định', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS026', N'Vũ Thị Hà', '2008-03-20', N'Nữ', N'Thái Bình', 'L10A2', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS027', N'Võ Văn Lâm', '2005-11-30', N'Nam', N'Hưng Yên', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS028', N'Đặng Văn Tú', '2005-04-07', N'Nam', N'Hải Dương', 'L10A1', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS029', N'Bùi Thị Thảo', '2005-06-15', N'Nữ', N'Vĩnh Phúc', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS030', N'Đỗ Văn Tiến', '2006-03-21', N'Nam', N'Hà Nam', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS031', N'Nguyễn Văn Quân', '2007-03-24', N'Nam', N'Hà Nội', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS032', N'Trần Văn Bình', '2006-02-11', N'Nam', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS033', N'Lê Văn Tâm', '2005-07-11', N'Nam', N'Thái Nguyên', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS034', N'Phạm Thị Linh', '2006-07-21', N'Nữ', N'Hải Phòng', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS035', N'Hoàng Văn Tùng', '2006-12-17', N'Nam', N'Nam Định', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS036', N'Vũ Văn Toàn', '2005-12-05', N'Nam', N'Thái Bình', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS037', N'Võ Văn Quyết', '2007-05-17', N'Nam', N'Hưng Yên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS038', N'Đặng Văn Nghĩa', '2008-08-23', N'Nam', N'Hải Dương', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS039', N'Bùi Văn Trọng', '2005-02-05', N'Nam', N'Vĩnh Phúc', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS040', N'Đỗ Văn Cường', '2007-11-23', N'Nam', N'Hà Nam', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS041', N'Nguyễn Thị Quyên', '2007-04-29', N'Nữ', N'Hà Nội', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS042', N'Trần Văn Hoàng', '2006-06-16', N'Nam', N'Bắc Ninh', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS043', N'Lê Thị Thu', '2007-05-22', N'Nữ', N'Thái Nguyên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS044', N'Phạm Thị Tuyết', '2007-08-06', N'Nữ', N'Hải Phòng', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS045', N'Hoàng Văn Khang', '2006-11-21', N'Nam', N'Nam Định', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS046', N'Vũ Văn Hưng', '2007-03-08', N'Nam', N'Thái Bình', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS047', N'Võ Văn Huy', '2006-05-29', N'Nam', N'Hưng Yên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS048', N'Đặng Văn Hiếu', '2007-09-22', N'Nam', N'Hải Dương', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS049', N'Bùi Văn Hào', '2005-12-11', N'Nam', N'Vĩnh Phúc', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS050', N'Đỗ Thị Trang', '2007-06-25', N'Nữ', N'Hà Nam', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS051', N'Nguyễn Thị Nga', '2005-08-10', N'Nữ', N'Hà Nội', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS052', N'Trần Văn Cảnh', '2006-03-14', N'Nam', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS053', N'Lê Văn Lộc', '2005-12-05', N'Nam', N'Thái Nguyên', 'L11A1', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS054', N'Phạm Thị Nhi', '2006-06-14', N'Nữ', N'Hải Phòng', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS055', N'Hoàng Thị Giang', '2005-12-12', N'Nữ', N'Nam Định', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS056', N'Vũ Văn Thịnh', '2005-08-10', N'Nam', N'Thái Bình', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS057', N'Võ Thị My', '2007-11-09', N'Nữ', N'Hưng Yên', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS058', N'Đặng Thị Mai', '2005-02-22', N'Nữ', N'Hải Dương', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS059', N'Bùi Văn Phát', '2008-02-15', N'Nam', N'Vĩnh Phúc', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS060', N'Đỗ Thị Bích', '2007-10-13', N'Nữ', N'Hà Nam', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS061', N'Nguyễn Thị Hồng', '2007-11-07', N'Nữ', N'Hà Nội', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS062', N'Trần Văn Phúc', '2007-07-05', N'Nam', N'Bắc Ninh', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS063', N'Lê Văn Tài', '2005-10-08', N'Nam', N'Thái Nguyên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS064', N'Phạm Văn An', '2007-05-23', N'Nam', N'Hải Phòng', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS065', N'Hoàng Thị Điệp', '2006-11-08', N'Nữ', N'Nam Định', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS066', N'Vũ Thị Loan', '2005-06-04', N'Nữ', N'Thái Bình', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS067', N'Võ Thị Lệ', '2005-04-12', N'Nữ', N'Hưng Yên', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS068', N'Đặng Văn Đức', '2005-12-17', N'Nam', N'Hải Dương', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS069', N'Bùi Văn Duy', '2006-07-14', N'Nam', N'Vĩnh Phúc', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS070', N'Đỗ Văn Đạt', '2007-10-27', N'Nam', N'Hà Nam', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS071', N'Nguyễn Thị Thủy', '2008-11-14', N'Nữ', N'Hà Nội', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS072', N'Trần Thị Thanh', '2005-08-08', N'Nữ', N'Bắc Ninh', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS073', N'Lê Thị Tâm', '2007-09-16', N'Nữ', N'Thái Nguyên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS074', N'Phạm Thị Thúy', '2006-03-08', N'Nữ', N'Hải Phòng', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS075', N'Hoàng Văn Quang', '2005-03-22', N'Nam', N'Nam Định', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS076', N'Vũ Văn Quyền', '2005-06-01', N'Nam', N'Thái Bình', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS077', N'Võ Thị Duyên', '2008-05-27', N'Nữ', N'Hưng Yên', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS078', N'Đặng Văn Thọ', '2006-04-24', N'Nam', N'Hải Dương', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS079', N'Bùi Thị Tình', '2008-10-21', N'Nữ', N'Vĩnh Phúc', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS080', N'Đỗ Văn Bảo', '2006-03-02', N'Nam', N'Hà Nam', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS081', N'Nguyễn Văn Thái', '2008-09-20', N'Nam', N'Hà Nội', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS082', N'Trần Thị Huệ', '2007-05-29', N'Nữ', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS083', N'Lê Văn Nhật', '2007-08-17', N'Nam', N'Thái Nguyên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS084', N'Phạm Thị Kim', '2005-11-27', N'Nữ', N'Hải Phòng', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS085', N'Hoàng Thị Ngọc', '2006-01-15', N'Nữ', N'Nam Định', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS086', N'Vũ Văn Hải', '2005-04-01', N'Nam', N'Thái Bình', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS087', N'Võ Thị Hương', '2006-04-08', N'Nữ', N'Hưng Yên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS088', N'Đặng Văn Nam', '2007-04-23', N'Nam', N'Hải Dương', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS089', N'Bùi Thị Lan', '2008-10-01', N'Nữ', N'Vĩnh Phúc', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS090', N'Đỗ Văn Dũng', '2008-04-06', N'Nam', N'Hà Nam', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS091', N'Nguyễn Thị Cúc', '2008-05-30', N'Nữ', N'Hà Nội', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS092', N'Trần Văn Trí', '2007-05-20', N'Nam', N'Bắc Ninh', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS093', N'Lê Thị Sen', '2006-08-16', N'Nữ', N'Thái Nguyên', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS094', N'Phạm Văn Hậu', '2005-03-02', N'Nam', N'Hải Phòng', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS095', N'Hoàng Thị Mơ', '2006-02-06', N'Nữ', N'Nam Định', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS096', N'Vũ Văn Trường', '2005-08-15', N'Nam', N'Thái Bình', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS097', N'Võ Thị Hằng', '2007-05-16', N'Nữ', N'Hưng Yên', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS098', N'Đặng Thị Đào', '2008-05-07', N'Nữ', N'Hải Dương', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS099', N'Bùi Văn Tín', '2005-06-14', N'Nam', N'Vĩnh Phúc', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS100', N'Đỗ Thị Nụ', '2007-10-05', N'Nữ', N'Hà Nam', 'L10A1', null);
go

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
('KHXH', N'Khoa học Xã hội (Sử, Địa, GDCD)'),
('CB', N'Cơ bản');
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
(N'Thi Giữa Kỳ 1', 'MH01', '2025-10-15', '07:30', '09:00', 'P101'), -- Toán
(N'Thi Giữa Kỳ 1', 'MH02', '2025-10-16', '07:30', '09:00', 'P102'); -- Văn

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
('L10A1', 'MH01', 'GV01', 'P101', 2, 1, 2), -- Lớp 10A1 học Toán (GV01) ở P101, Thứ 2, tiết 1 đến 2
('L10A1', 'MH02', 'GV02', 'P102', 2, 3, 4), -- Lớp 10A1 học Văn (GV02) ở P102, Thứ 2, tiết 3 đến 4
('L10A2', 'MH01', 'GV01', 'P101', 3, 1, 2), -- Lớp 10A2 học Toán (GV01) ở P101, Thứ 3, tiết 1 đến 2
('L11A1', 'MH02', 'GV02', 'P201', 4, 1, 2); -- Lớp 11A1 học Văn (GV02) ở P201, Thứ 4, tiết 1 đến 2
GO