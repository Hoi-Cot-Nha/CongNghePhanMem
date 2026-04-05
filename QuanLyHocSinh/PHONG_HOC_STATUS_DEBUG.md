# 🔍 DEBUG: Quản Lý Phòng Học - Tình trạng không đồng bộ

## 📊 Phát hiện vấn đề

### **Bước 1: Column Values (Table)**
**File:** `View/ThuTrang/FrmPhongHoc.java` (dòng 102-104)
```java
model = new DefaultTableModel(
    new String[]{"Mã phòng", "Tên phòng", "Sức chứa", "Loại", "Tình trạng"}, 0
);
```
- Cột "Tình trạng" = **column index 4** ✓
- Giá trị từ DB (phòng_học_tình_trạng): `rs.getString("TinhTrangThucTe")` → 
  - **"Đang học"** (từ ThoiKhoaBieu)
  - **"Trống"** (mặc định)
  - **"Bảo trì"** (thủ công)

### **Bước 2: ComboBox Values**
**File:** `View/ThuTrang/FrmPhongHoc.java` (dòng 129-131)
```java
cboTinhTrang = new JComboBox<>(new String[]{
    "Trống", "Đang học", "Bảo trì"
});
```
- **Item 0:** "Trống"
- **Item 1:** "Đang học"
- **Item 2:** "Bảo trì"

### **Bước 3: Sync Logic (fillForm)**
**File:** `View/ThuTrang/FrmPhongHoc.java` (dòng 218-226)
```java
public void fillForm(int row) {
    if (row < 0) return;
    
    txtMaPhong.setText(model.getValueAt(row, 0).toString());
    txtTenPhong.setText(model.getValueAt(row, 1).toString());
    txtSucChua.setText(model.getValueAt(row, 2).toString());
    cboLoaiPhong.setSelectedItem(model.getValueAt(row, 3).toString());
    cboTinhTrang.setSelectedItem(model.getValueAt(row, 4).toString());  // ⚠️ VẤNĐỀ TẠI ĐÂY
}
```

### **Bước 4: DAO Value**
**File:** `Dao/PhongHocDAO.java` (dòng 184-215)
```java
public List<PhongHoc> getAllWithTinhTrang() {
    String sql = """
        CASE
            WHEN p.MaPhong IN (SELECT DISTINCT MaPhong FROM ThoiKhoaBieu)
            THEN 'Đang học'
            ELSE 'Trống'
        END AS TinhTrangThucTe
    FROM PhongHoc p
    """;
    
    list.add(new PhongHoc(
        rs.getString("MaPhong"),
        rs.getString("TenPhong"),
        rs.getInt("SucChua"),
        rs.getString("LoaiPhong"),
        rs.getString("TinhTrangThucTe")  // ← Lấy giá trị từ column này
    ));
}
```

---

## ✅ **GỐC NGUYÊN NHÂN VẤNĐỀ**

### **Vấnđề 1: Dữ liệu DB không khớp combo**
- DB có **2 giá trị**: "Đang học", "Trống"
- Combo có **3 giá trị**: "Trống", "Đang học", "Bảo trì"
- → Khi DB trả về "Bảo trì", combo không có giá trị này → `setSelectedItem()` không tìm được match
- → ComboBox vẫn chọn item cũ hoặc không chọn

### **Vấnđề 2: Logic getAllWithTinhTrang() không hoàn chỉnh**
- Query chỉ có 2 giá trị: "Đang học" hoặc "Trống"
- Không có logic để lấy giá trị "Bảo trì" từ cột `TinhTrang` của bảng PhongHoc
- → Khi phòng bảo trì (do admin cập nhật), combo không hiển thị đúng

### **Vấnđề 3: Không lấy từ DB TinhTrang**
```java
// ❌ Sai: Chỉ lấy "Đang học" hoặc "Trống" từ logic CASE
rs.getString("TinhTrangThucTe")

// ✅ Nên lấy từ DB + tính toán hợp nhất
COALESCE(p.TinhTrang, CASE WHEN ... THEN 'Đang học' ELSE 'Trống' END)
```

---

## 🛠️ **KHUYẾN NGHỊ FIX**

### **Fix 1: Cập nhật DAO để lấy TinhTrang từ 2 nguồn**
```java
// getAllWithTinhTrang() - cũ
String sql = """
    CASE
        WHEN p.MaPhong IN (SELECT DISTINCT MaPhong FROM ThoiKhoaBieu)
        THEN 'Đang học'
        ELSE 'Trống'
    END AS TinhTrangThucTe
FROM PhongHoc p
""";

// ✅ Sửa: Lấy TinhTrang + tính toán "Đang học" nếu có trong ThoiKhoaBieu
String sql = """
    CASE
        WHEN p.MaPhong IN (SELECT DISTINCT MaPhong FROM ThoiKhoaBieu) THEN 'Đang học'
        WHEN p.TinhTrang IS NOT NULL AND p.TinhTrang != '' THEN p.TinhTrang
        ELSE 'Trống'
    END AS TinhTrangThucTe
FROM PhongHoc p
""";
```

**Logic:**
1. Nếu phòng có lịch (ThoiKhoaBieu) → "Đang học" (cao nhất)
2. Nếu không có lịch nhưng admin set "Bảo trì" → "Bảo trì"
3. Nếu không → "Trống"

### **Fix 2: Xử lý setSelectedItem() khi không tìm được match**
**File:** `View/ThuTrang/FrmPhongHoc.java` (dòng 224-225)

```java
// ❌ Cũ:
cboLoaiPhong.setSelectedItem(model.getValueAt(row, 3).toString());
cboTinhTrang.setSelectedItem(model.getValueAt(row, 4).toString());

// ✅ Sửa: Với fallback
private void setComboBoxValue(JComboBox<String> combo, String value) {
    if (value != null && !value.isEmpty()) {
        boolean found = false;
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(value)) {
                combo.setSelectedIndex(i);
                found = true;
                break;
            }
        }
        if (!found) {
            combo.setSelectedIndex(0); // Default nếu không tìm được
        }
    } else {
        combo.setSelectedIndex(0);
    }
}

// Áp dụng:
setComboBoxValue(cboLoaiPhong, model.getValueAt(row, 3).toString());
setComboBoxValue(cboTinhTrang, model.getValueAt(row, 4).toString());
```

### **Fix 3: Ensure DB TinhTrang không null**
Kiểm tra trong bảng PhongHoc: giá trị TinhTrang có null không?
```sql
SELECT MaPhong, TinhTrang FROM PhongHoc WHERE TinhTrang IS NULL;
```
Nếu có, cập nhật:
```sql
UPDATE PhongHoc SET TinhTrang = 'Trống' WHERE TinhTrang IS NULL;
```

---

## 📋 **Tóm tắt**

| Lỗi | Nguyên nhân | Fix |
|-----|-----------|-----|
| Combo không chọn đúng | DB value không khớp combo items | Cập nhật DAO lấy TinhTrang từ DB + logic |
| setSelectedItem() thất bại | Giá trị không tồn tại trong combo | Thêm fallback setSelectedIndex(0) |
| "Bảo trì" không hiển thị | DAO chỉ return "Đang học" hoặc "Trống" | Thêm logic CASE lấy p.TinhTrang |

---

## ✨ **Kết luận**

**Chính xác**: Vấnđề là `getAllWithTinhTrang()` chỉ return 2 giá trị ("Đang học" hoặc "Trống"), không lấy giá trị "Bảo trì" từ DB. Khi admin update phòng thành "Bảo trì" → DB có giá trị nhưng query không return → combo không tìm được → không select.

**Fix chính**: Sửa query SQL trong `getAllWithTinhTrang()` để:
1. Ưu tiên "Đang học" nếu có lịch
2. Lấy TinhTrang từ DB nếu admin set
3. Default "Trống" nếu không có cả hai
