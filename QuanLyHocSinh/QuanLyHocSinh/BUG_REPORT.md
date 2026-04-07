## 🔴 BÁO CÁO DEBUG: LỖI BỘ LỌC QUẢN LÝ HỌC PHÍ

### ❌ VẤN ĐỀ
Bộ lọc Quản Lý Học Phí bị lỗi:
- ✗ Chọn **L10A1**, Học kì 1, Năm 2023-2026 → **Không có kết quả**
- ✓ Chọn **L11A1**, Học kì 1, Năm 2023-2026 → **Có kết quả**

---

### 🔍 NGUYÊN NHÂN ROOT CAUSE

**File:** `QuanLyHocPhiPanel.java` (dòng 277)

**Vấn đề chính:**
```java
// CỔ: Load toàn bộ năm học từ bảng Lop (không phân biệt lớp)
var lopList = lopDAO.getAllLop();
java.util.Set<String> namHocSet = new java.util.HashSet<>();
for (var lop : lopList) {
    namHocSet.add(lop.getNienKhoa());  // Lấy tất cả NienKhoa
}
cboNamHoc.setSelectedIndex(sortedNamHoc.length - 1);  // ← CHỌN CUỐI CÙNG
```

**Kết quả:**
1. **Combo box Năm Học** được populate từ `Lop.NienKhoa` (toàn bộ niên khóa trong hệ thống)
2. Nhưng **HocPhi.NamHoc** có thể khác hoặc không có cho lớp L10A1:
   - L10A1 → NienKhoa = "2023-2024" → HocPhi chỉ có năm "2023-2024"
   - L11A1 → NienKhoa = "2023-2026" → HocPhi có năm "2023-2026"
3. Khi chọn L10A1 + chọn Năm 2023-2026 (vì nó là mặc định cuối cùng) → **JOIN không match → 0 kết quả**

---

### ✅ GIẢI PHÁP

#### **Bước 1:** Thêm DAO method `getNamHocByMaLop()`
**File:** `HocphiDAO.java`
- Query SQL: Lấy `DISTINCT NamHoc` từ bảng `HocPhi` dựa trên `MaLop` được chọn
- Thay vì lấy toàn bộ từ `Lop.NienKhoa`, chỉ lấy năm học **có dữ liệu thực tế** cho lớp đó

```sql
SELECT DISTINCT hp.NamHoc FROM HocPhi hp 
JOIN HocSinh hs ON hp.MaHS = hs.MaHS 
WHERE hs.MaLop = ? 
ORDER BY hp.NamHoc DESC
```

#### **Bước 2:** Thêm ItemListener cho `cboMaLop`
**File:** `QuanLyHocPhiPanel.java` (dòng 62)
- Khi user chọn lớp → tự động reload combo box **Năm Học**
- Gọi `loadNamHocByMaLop()` thay vì `loadNamHocComboBox()`

```java
cboMaLop.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        Object selected = cboMaLop.getSelectedItem();
        if (selected != null && !selected.toString().equals("(Không có dữ liệu)")) {
            loadNamHocByMaLop(selected.toString());
        }
    }
});
```

#### **Bước 3:** Tạo method `loadNamHocByMaLop()`
**File:** `QuanLyHocPhiPanel.java`
- Load năm học từ DAO method `getNamHocByMaLop()`
- Chỉ hiển thị năm học **có dữ liệu** cho lớp được chọn

---

### 📊 BẢNG SO SÁNH

| Aspect | CỒ (Bug) | MỚI (Fix) |
|--------|---------|----------|
| **Nguồn dữ liệu Năm Học** | `Lop.NienKhoa` (toàn bộ) | `HocPhi.NamHoc` (dựa theo MaLop) |
| **Khi thay đổi Lớp** | Năm Học không thay đổi | Năm Học reload tự động |
| **Dữ liệu hiển thị** | Toàn bộ niên khóa (có thể không có HocPhi) | Chỉ năm có dữ liệu HocPhi |
| **Lỗi L10A1 + 2023-2026** | ✗ 0 kết quả | ✓ Hiển thị chính xác |

---

### 📝 FILES MODIFIED

1. **`HocphiDAO.java`**
   - Thêm method: `getNamHocByMaLop(String maLop)`
   - Query: Lấy DISTINCT NamHoc từ HocPhi JOIN HocSinh

2. **`QuanLyHocPhiPanel.java`**
   - Thêm ItemListener cho `cboMaLop`
   - Thêm method: `loadNamHocByMaLop(String maLop)`
   - Sửa: `loadNamHocComboBox()` chọn index 0 thay vì cuối cùng

---

### 🧪 CÁCH TEST

1. **Chạy ứng dụng** → Module Quản Lý Học Phí
2. **Chọn L10A1** → Combo "Năm Học" sẽ hiển thị chỉ năm có dữ liệu
3. **Chọn Năm tương ứng** → Click "Xem Danh Sách" → Có kết quả
4. **Chuyển sang L11A1** → Combo "Năm Học" reload với dữ liệu của L11A1
5. **Kiểm tra các lớp khác** → Đảm bảo combo box thay đổi động

---

### 📌 GHI CHÚ

- **Data consistency:** Dữ liệu giữa `Lop.NienKhoa` và `HocPhi.NamHoc` có thể khác nhau
- **SQL JOIN:** Đúng (`HocPhi JOIN HocSinh ON MaHS` → lấy MaLop từ HocSinh)
- **Performance:** Mỗi lần chọn lớp sẽ query 1 lần → có thể tối ưu cache nếu cần

