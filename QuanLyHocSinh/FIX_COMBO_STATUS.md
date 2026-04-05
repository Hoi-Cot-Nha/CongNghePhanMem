# Fix Combo Box Status Binding Issue

## Vấn đề
Combo box "Tình trạng" không bind được với dữ liệu từ bảng khi click vào một dòng.

**Nguyên nhân:**
- Combo box được hardcode với items: ["Trống", "Đang học", "Bảo trì"]
- Code sử dụng `setSelectedItem(value)` nhưng không hoạt động vì đối tượng String không match giữa combo model và dữ liệu từ bảng
- Điều này khiến khi click row, combo không select đúng giá trị

## Giải pháp
Thay thế `setSelectedItem()` bằng `setSelectedIndex()` với logic mapping đúng.

## File Thay Đổi
**File:** `src/main/java/View/ThuTrang/FrmPhongHoc.java`

**Dòng 225:** Thay từ:
```java
cboTinhTrang.setSelectedItem(model.getValueAt(row, 4).toString());
```

Thành:
```java
String tinhTrang = model.getValueAt(row, 4).toString();
if (tinhTrang.equals("Trống")) {
    cboTinhTrang.setSelectedIndex(0);
} else if (tinhTrang.equals("Đang học")) {
    cboTinhTrang.setSelectedIndex(1);
} else if (tinhTrang.equals("Bảo trì")) {
    cboTinhTrang.setSelectedIndex(2);
}
```

## Cách Hoạt Động
1. Lấy giá trị từ column 4 (Tình trạng) của bảng
2. So sánh với các giá trị có trong combo: "Trống", "Đang học", "Bảo trì"
3. Dùng `setSelectedIndex()` để select item đúng trong combo

## Kết Quả
- Khi click vào một dòng trong bảng, combo box "Tình trạng" sẽ hiển thị giá trị đúng
- Fix vấn đề không thể bind dữ liệu từ bảng vào form input
