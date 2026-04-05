# Báo Cáo Thay Đổi Mã Nguồn

## TASK 1: QuanLyHocPhiPanel - Thêm cột "Mã Lớp"

### ✅ Tệp 1: Model/Hocphi.java
**Thay đổi:**
- Thêm trường `private String maLop;` vào class
- Cập nhật constructor để bao gồm `maLop`
- Thêm getter/setter cho `maLop`

**Trước:**
```java
public class Hocphi {
    private int maHP;
    private String maHS;
    private int hocKy;
    ...
}
```

**Sau:**
```java
public class Hocphi {
    private int maHP;
    private String maHS;
    private String maLop;  // THÊM MỚI
    private int hocKy;
    ...
}

public String getMaLop() {
    return maLop;
}

public void setMaLop(String maLop) {
    this.maLop = maLop;
}
```

---

### ✅ Tệp 2: Dao/HocphiDAO.java
**Thay đổi:**
- Cập nhật SQL trong `getHocPhiByLop()` để join lấy `MaLop` từ bảng HocSinh
- Cập nhật SQL trong `getAllHocPhi()` để join lấy `MaLop`
- Thêm `hp.setMaLop(rs.getString("MaLop"));` khi parse dữ liệu

**Trước:**
```java
String sql = "SELECT hp.* FROM HocPhi hp " +
             "JOIN HocSinh hs ON hp.MaHS = hs.MaHS " +
             "WHERE hs.MaLop = ? AND hp.HocKy = ? AND hp.NamHoc = ?";

// Trong loop:
hp.setMaHS(rs.getString("MaHS"));
hp.setHocKy(rs.getInt("HocKy"));
```

**Sau:**
```java
String sql = "SELECT hp.*, hs.MaLop FROM HocPhi hp " +  // THÊM hs.MaLop
             "JOIN HocSinh hs ON hp.MaHS = hs.MaHS " +
             "WHERE hs.MaLop = ? AND hp.HocKy = ? AND hp.NamHoc = ?";

// Trong loop:
hp.setMaHS(rs.getString("MaHS"));
hp.setMaLop(rs.getString("MaLop"));  // THÊM MỚI
hp.setHocKy(rs.getInt("HocKy"));
```

---

### ✅ Tệp 3: View/HaTrang/QuanLyHocPhiPanel.java

#### 3.1 Dòng 90: Cập nhật columns array
**Trước:**
```java
String[] cols = {"ID", "Mã HS", "Kỳ", "Năm học", "Tổng tiền", "Miễn giảm", "Phải đóng", "Trạng thái"};
```

**Sau:**
```java
String[] cols = {"ID", "Mã HS", "Mã Lớp", "Kỳ", "Năm học", "Tổng tiền", "Miễn giảm", "Phải đóng", "Trạng thái"};
```

#### 3.2 Dòng 290-306: Cập nhật loadTable()
**Trước:**
```java
public void loadTable(List<Hocphi> list) {
    tableModel.setRowCount(0);
    int stt = 1; 
    for (Hocphi hp : list) {
        tableModel.addRow(new Object[]{
            stt++, // ID hiển thị là STT
            hp.getMaHS(), 
            hp.getHocKy(), 
            hp.getNamHoc(),
            hp.getTongTien(), 
            hp.getMienGiam(), 
            hp.getPhaiDong(), 
            hp.getTrangThai()
        });
    }
}
```

**Sau:**
```java
public void loadTable(List<Hocphi> list) {
    tableModel.setRowCount(0);
    int stt = 1; 
    for (Hocphi hp : list) {
        tableModel.addRow(new Object[]{
            stt++, // ID hiển thị là STT
            hp.getMaHS(), 
            hp.getMaLop(),      // THÊM MỚI - Cột 2
            hp.getHocKy(),      // Cột 3 (trước là cột 2)
            hp.getNamHoc(),     // Cột 4 (trước là cột 3)
            hp.getTongTien(),   // Cột 5 (trước là cột 4)
            hp.getMienGiam(),   // Cột 6 (trước là cột 5)
            hp.getPhaiDong(),   // Cột 7 (trước là cột 6)
            hp.getTrangThai()   // Cột 8 (trước là cột 7)
        });
    }
}
```

#### 3.3 Dòng 169-183: Cập nhật column indices trong mouseClicked event
**Trước:**
```java
tableHocPhi.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = tableHocPhi.getSelectedRow();
        if (r >= 0) {
            txtMaHS.setText(tableModel.getValueAt(r, 1).toString());
            txtMaHS.setEditable(false);
            txtTongTien.setText(tableModel.getValueAt(r, 4).toString());    // Cột 4
            txtMienGiam.setText(tableModel.getValueAt(r, 5).toString());    // Cột 5
            txtPhaiDong.setText(tableModel.getValueAt(r, 6).toString());    // Cột 6
            txtTrangThai.setText(tableModel.getValueAt(r, 7).toString());   // Cột 7
        }
    }
});
```

**Sau:**
```java
tableHocPhi.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int r = tableHocPhi.getSelectedRow();
        if (r >= 0) {
            txtMaHS.setText(tableModel.getValueAt(r, 1).toString());
            txtMaHS.setEditable(false);
            txtTongTien.setText(tableModel.getValueAt(r, 5).toString());    // Cột 5 (trước cột 4)
            txtMienGiam.setText(tableModel.getValueAt(r, 6).toString());    // Cột 6 (trước cột 5)
            txtPhaiDong.setText(tableModel.getValueAt(r, 7).toString());    // Cột 7 (trước cột 6)
            txtTrangThai.setText(tableModel.getValueAt(r, 8).toString());   // Cột 8 (trước cột 7)
        }
    }
});
```

---

## TASK 2: QuanLyDoiTuongUuTienPanel - Nhân TiLeGiam × 100

### ✅ Tệp 4: Controller/Dai/DoiTuongUuTienController.java

#### 4.1 Dòng 13-24: Cập nhật loadTable()
**Trước:**
```java
public void loadTable(DefaultTableModel model) {
    model.setRowCount(0);
    List<DoiTuongUuTien> list = dao.getAll();

    for (DoiTuongUuTien dt : list) {
        model.addRow(new Object[]{
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getTiLeGiam()
        });
    }
}
```

**Sau:**
```java
public void loadTable(DefaultTableModel model) {
    model.setRowCount(0);
    List<DoiTuongUuTien> list = dao.getAll();

    for (DoiTuongUuTien dt : list) {
        model.addRow(new Object[]{
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getTiLeGiam() * 100  // THÊM × 100
        });
    }
}
```

#### 4.2 Dòng 40-51: Cập nhật timKiem()
**Trước:**
```java
public void timKiem(String keyword, DefaultTableModel model) {
    model.setRowCount(0);
    List<DoiTuongUuTien> list = dao.search(keyword);

    for (DoiTuongUuTien dt : list) {
        model.addRow(new Object[]{
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getTiLeGiam()
        });
    }
}
```

**Sau:**
```java
public void timKiem(String keyword, DefaultTableModel model) {
    model.setRowCount(0);
    List<DoiTuongUuTien> list = dao.search(keyword);

    for (DoiTuongUuTien dt : list) {
        model.addRow(new Object[]{
                dt.getMaDT(),
                dt.getTenDT(),
                dt.getTiLeGiam() * 100  // THÊM × 100
        });
    }
}
```

---

### ✅ Tệp 5: View/Dai/QuanLyDoiTuongUuTienPanel.java

#### 5.1 Dòng 262-273: Cập nhật doDuLieuVaoForm()
**Trước:**
```java
private void doDuLieuVaoForm() {
    int row = tableDT.getSelectedRow();
    if (row >= 0) {
        txtMaDT.setText(tableModel.getValueAt(row, 0).toString());
        txtTenDT.setText(tableModel.getValueAt(row, 1).toString());
        txtTiLeGiam.setText(tableModel.getValueAt(row, 2).toString());
    }
}
```

**Sau:**
```java
private void doDuLieuVaoForm() {
    int row = tableDT.getSelectedRow();
    if (row >= 0) {
        txtMaDT.setText(tableModel.getValueAt(row, 0).toString());
        txtTenDT.setText(tableModel.getValueAt(row, 1).toString());
        Object tiLeValue = tableModel.getValueAt(row, 2);
        if (tiLeValue != null) {
            double tiLeDisplay = Double.parseDouble(tiLeValue.toString());
            txtTiLeGiam.setText(String.valueOf(tiLeDisplay / 100));  // CHIA ÷ 100
        }
    }
}
```

---

## Tóm Tắt Thay Đổi

| Task | File | Thay Đổi |
|------|------|----------|
| **Task 1** | Model/Hocphi.java | Thêm field `maLop`, getter/setter |
| **Task 1** | Dao/HocphiDAO.java | Cập nhật SQL join lấy `MaLop`, parse dữ liệu |
| **Task 1** | View/HaTrang/QuanLyHocPhiPanel.java | Thêm "Mã Lớp" cột, cập nhật loadTable(), điều chỉnh indices |
| **Task 2** | Controller/Dai/DoiTuongUuTienController.java | Nhân TiLeGiam × 100 trong loadTable() và timKiem() |
| **Task 2** | View/Dai/QuanLyDoiTuongUuTienPanel.java | Chia TiLeGiam ÷ 100 khi load form |

---

## Các File Đã Sửa Đổi
1. ✅ `src\main\java\Model\Hocphi.java`
2. ✅ `src\main\java\Dao\HocphiDAO.java`
3. ✅ `src\main\java\View\HaTrang\QuanLyHocPhiPanel.java`
4. ✅ `src\main\java\Controller\Dai\DoiTuongUuTienController.java`
5. ✅ `src\main\java\View\Dai\QuanLyDoiTuongUuTienPanel.java`

---

## Ví Dụ Dữ Liệu

### Task 1 - Cột Mã Lớp
Bảng HỌC PHÍ sẽ hiển thị:
```
| ID | Mã HS | Mã Lớp | Kỳ | Năm học | Tổng tiền | ... |
|----|-------|--------|----|---------|-----------| ... |
| 1  | HS001 | 10A1   | 1  | 2024    | 5000000   | ... |
| 2  | HS002 | 10A1   | 1  | 2024    | 5000000   | ... |
```

### Task 2 - Tỉ Lệ Giảm × 100
- Database lưu: 0.5 → Hiển thị: 50
- Database lưu: 1.0 → Hiển thị: 100
- Database lưu: 0.1 → Hiển thị: 10

Bảng ĐỐI TƯỢNG ƯU TIÊN sẽ hiển thị:
```
| Mã Đối Tượng | Tên Đối Tượng       | Tỉ Lệ Giảm (%) |
|--------------|-------------------|----------------|
| DT001        | Học sinh giỏi      | 50             |
| DT002        | Học sinh khuyết tật | 100            |
| DT003        | Học sinh vùng sâu  | 25             |
```
