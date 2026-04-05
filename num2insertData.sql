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
GO

insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS001', 'Paloma Ackwood', '2005-10-01', 'Nữ', '52 Summit Lane', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS002', 'Dionne Gobeau', '2008-02-20', 'Nữ', '12 Hayes Hill', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS003', 'Nero Stanworth', '2007-01-28', 'Nam', '77 Emmet Alley', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS004', 'Piotr Pinhorn', '2006-05-03', 'Nam', '82361 Birchwood Place', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS005', 'Wally Newbold', '2008-07-11', 'Nữ', '7993 Hazelcrest Drive', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS006', 'Dennie Wills', '2007-01-15', 'Nữ', '1708 Burning Wood Center', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS007', 'Emalia De Cruce', '2006-10-13', 'Nam', '5873 Debs Plaza', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS008', 'Fidelity Feitosa', '2007-05-24', 'Nam', '5453 Vidon Plaza', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS009', 'Laina Aldrick', '2006-09-27', 'Nam', '09106 American Point', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS010', 'Nealson Driuzzi', '2008-07-08', 'Nữ', '82510 Hintze Parkway', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS011', 'Aubry Arger', '2008-04-19', 'Nữ', '8056 Westridge Court', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS012', 'Stacy Golt', '2005-03-21', 'Nam', '78 Chinook Circle', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS013', 'Herb Domoney', '2005-05-26', 'Nam', '569 Lyons Center', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS014', 'Solly Albion', '2006-04-01', 'Nam', '009 Westport Hill', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS015', 'Doralynn Clarkin', '2008-05-22', 'Nam', '99545 Muir Junction', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS016', 'Gayleen Rawlingson', '2008-04-06', 'Nam', '11 Oak Point', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS017', 'Sindee Obeney', '2006-11-26', 'Nữ', '78 Butternut Place', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS018', 'Maggee Hamprecht', '2005-10-06', 'Nam', '2655 Reinke Trail', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS019', 'Carlin Dellenty', '2006-12-03', 'Nam', '44 Cherokee Plaza', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS020', 'Vilhelmina Bramhall', '2005-12-01', 'Nam', '07 Maryland Junction', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS021', 'Alis Paprotny', '2006-06-16', 'Nữ', '1301 Carberry Hill', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS022', 'Paco Harfoot', '2007-03-27', 'Nam', '50 Westerfield Pass', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS023', 'Doloritas Farthin', '2008-03-06', 'Nữ', '254 Columbus Junction', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS024', 'Skelly Bramwell', '2006-04-04', 'Nữ', '0714 Anniversary Park', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS025', 'Malvina Ventris', '2006-09-25', 'Nữ', '8358 Merrick Point', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS026', 'Modestia Gadsdon', '2008-03-20', 'Nữ', '7728 Roth Plaza', 'L10A2', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS027', 'Sherrie Shmyr', '2005-11-30', 'Nam', '49063 Graedel Trail', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS028', 'Fitz Kock', '2005-04-07', 'Nam', '4758 Maywood Pass', 'L10A1', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS029', 'Loutitia Thurborn', '2005-06-15', 'Nữ', '4986 Spaight Lane', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS030', 'Theodor Belz', '2006-03-21', 'Nam', '89694 Bartelt Court', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS031', 'Onfroi Francklyn', '2007-03-24', 'Nam', '9 Schiller Plaza', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS032', 'Benedikta Arnely', '2006-02-11', 'Nam', '8 Becker Point', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS033', 'Hyacinthie Lillee', '2005-07-11', 'Nam', '64 Calypso Court', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS034', 'Leyla Umney', '2006-07-21', 'Nữ', '3853 Debra Crossing', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS035', 'Morganne Prattin', '2006-12-17', 'Nam', '4 Oneill Trail', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS036', 'Tab Lubbock', '2005-12-05', 'Nam', '83742 Pleasure Alley', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS037', 'Silvio Mattis', '2007-05-17', 'Nam', '190 Bluestem Road', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS038', 'Sascha Goldbourn', '2008-08-23', 'Nam', '8 Anniversary Park', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS039', 'Susy Mingay', '2005-02-05', 'Nam', '98099 Ryan Place', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS040', 'Hermina Yerborn', '2007-11-23', 'Nam', '26 Jana Street', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS041', 'Coletta Fenelon', '2007-04-29', 'Nữ', '400 Union Plaza', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS042', 'Sharia Zoephel', '2006-06-16', 'Nam', '32172 Iowa Lane', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS043', 'Lindsy Willars', '2007-05-22', 'Nữ', '2042 Birchwood Alley', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS044', 'Loutitia Birchill', '2007-08-06', 'Nữ', '54 Pierstorff Terrace', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS045', 'Helenka Brownsell', '2006-11-21', 'Nam', '8844 Montana Plaza', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS046', 'Leelah Nunns', '2007-03-08', 'Nam', '985 Shoshone Junction', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS047', 'Yves Bastin', '2006-05-29', 'Nam', '65 5th Parkway', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS048', 'Ted Jurs', '2007-09-22', 'Nam', '7 Eggendart Circle', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS049', 'Tandie Macauley', '2005-12-11', 'Nam', '0 Lillian Drive', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS050', 'Artemus Ramplee', '2007-06-25', 'Nữ', '46077 Twin Pines Crossing', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS051', 'Laney Wixey', '2005-08-10', 'Nữ', '76189 Warrior Pass', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS052', 'Atlante Bankhurst', '2006-03-14', 'Nam', '8720 Hintze Crossing', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS053', 'Vidovic Stangoe', '2005-12-05', 'Nam', '23131 Raven Plaza', 'L11A1', 'DT01');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS054', 'Leonardo Aupol', '2006-06-14', 'Nữ', '5 Fulton Plaza', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS055', 'Lois Crellin', '2005-12-12', 'Nữ', '66975 Haas Parkway', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS056', 'Whit Andrey', '2005-08-10', 'Nam', '4784 Old Gate Lane', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS057', 'Hew Bohden', '2007-11-09', 'Nữ', '296 Nevada Court', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS058', 'Latrena Littlefair', '2005-02-22', 'Nữ', '8 Bay Junction', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS059', 'Joyan Pentony', '2008-02-15', 'Nam', '4 Farwell Trail', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS060', 'Inglis Arnaldy', '2007-10-13', 'Nữ', '768 Kropf Center', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS061', 'Odele Nudds', '2007-11-07', 'Nữ', '26627 Carioca Crossing', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS062', 'Annora Shadfourth', '2007-07-05', 'Nam', '60320 Bultman Street', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS063', 'Jenny Paridge', '2005-10-08', 'Nam', '6211 Westend Drive', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS064', 'Grady Tunny', '2007-05-23', 'Nam', '530 Sauthoff Court', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS065', 'Fleming Stringer', '2006-11-08', 'Nữ', '8 Kinsman Lane', 'L11A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS066', 'Hugibert Starcks', '2005-06-04', 'Nữ', '873 Towne Pass', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS067', 'Rafi Bradd', '2005-04-12', 'Nữ', '38 Veith Circle', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS068', 'Flori Merchant', '2005-12-17', 'Nam', '04251 Fordem Point', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS069', 'Barclay Han', '2006-07-14', 'Nam', '80442 Cardinal Court', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS070', 'Gaylene Corfield', '2007-10-27', 'Nam', '55798 Vidon Way', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS071', 'Kippie Cree', '2008-11-14', 'Nữ', '2 Roth Center', 'L10A2', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS072', 'Curcio Shotton', '2005-08-08', 'Nữ', '107 Laurel Center', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS073', 'Lucilia Wyer', '2007-09-16', 'Nữ', '70743 School Parkway', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS074', 'Winny Ambage', '2006-03-08', 'Nữ', '038 Birchwood Road', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS075', 'Kai Chapling', '2005-03-22', 'Nam', '1 Larry Terrace', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS076', 'Tom Keilty', '2005-06-01', 'Nam', '5209 Steensland Parkway', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS077', 'Berget Wearne', '2008-05-27', 'Nữ', '524 Schlimgen Way', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS078', 'Verina Cicchillo', '2006-04-24', 'Nam', '7 Loomis Court', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS079', 'Dottie Ewers', '2008-10-21', 'Nữ', '2756 Starling Terrace', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS080', 'Tabina Busfield', '2006-03-02', 'Nam', '191 Old Shore Pass', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS081', 'Anna Jessel', '2008-09-20', 'Nam', '5 Dapin Trail', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS082', 'Jessika Eby', '2007-05-29', 'Nữ', '431 Kennedy Court', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS083', 'Danette Leate', '2007-08-17', 'Nam', '38538 Reindahl Crossing', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS084', 'Erna Beaze', '2005-11-27', 'Nữ', '0045 Ridge Oak Trail', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS085', 'Candis Gellett', '2006-01-15', 'Nữ', '6776 Graceland Street', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS086', 'Kaitlin Hinners', '2005-04-01', 'Nam', '13 Fisk Center', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS087', 'Nedda Anselmi', '2006-04-08', 'Nữ', '0841 Miller Center', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS088', 'Coreen Heggie', '2007-04-23', 'Nam', '927 Mccormick Drive', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS089', 'Ardyce Harnott', '2008-10-01', 'Nữ', '7713 Spenser Trail', 'L10A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS090', 'Dode Daymond', '2008-04-06', 'Nam', '01 Sutteridge Park', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS091', 'Osborne Radborne', '2008-05-30', 'Nữ', '43 Mayer Plaza', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS092', 'Eleen Jeanequin', '2007-05-20', 'Nam', '757 Talisman Pass', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS093', 'Jennine Stebbings', '2006-08-16', 'Nữ', '15829 Sheridan Trail', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS094', 'Fons Beecroft', '2005-03-02', 'Nam', '077 Valley Edge Plaza', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS095', 'Jorrie Owtram', '2006-02-06', 'Nữ', '395 Aberg Hill', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS096', 'Zebulon Branchett', '2005-08-15', 'Nam', '0902 Dakota Park', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS097', 'Elicia Bambery', '2007-05-16', 'Nữ', '6 Hauk Terrace', 'L10A2', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS098', 'Sal Heavyside', '2008-05-07', 'Nữ', '4413 Basil Center', 'L11A1', null);
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS099', 'Marlo Orlton', '2005-06-14', 'Nam', '880 Cody Drive', 'L10A1', 'DT02');
insert into HocSinh (MaHS, HoTen, NgaySinh, GioiTinh, DiaChi, MaLop, MaDT) values ('HS100', 'Darn Avieson', '2007-10-05', 'Nữ', '653 Bashford Circle', 'L10A1', null);

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

-- Đã xóa khoảng trắng, tên biến giờ là: @MucHocPhiGoc
DECLARE @MucHocPhiGoc DECIMAL(18,0) = 2000000;

INSERT INTO HocPhi (MaHS, HocKy, NamHoc, TongTien, MienGiam, PhaiDong, TrangThai)
SELECT 
    hs.MaHS,
    1 AS HocKy,
    N'2025-2026' AS NamHoc,
    @MucHocPhiGoc AS TongTien,
    
    -- Tính tiền miễn giảm
    ISNULL(dt.TiLeGiamHocPhi, 0) * @MucHocPhiGoc AS MienGiam,
    
    -- Tính tiền phải đóng
    @MucHocPhiGoc - (ISNULL(dt.TiLeGiamHocPhi, 0) * @MucHocPhiGoc) AS PhaiDong,
    
    -- Trạng thái ngẫu nhiên
    CHOOSE(ABS(CHECKSUM(NEWID())) % 2 + 1, N'Đã hoàn thành', N'Chưa đóng') AS TrangThai
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