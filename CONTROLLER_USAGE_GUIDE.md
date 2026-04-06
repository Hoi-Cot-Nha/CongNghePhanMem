# 🎯 HƯỚNG DẪN SỬ DỤNG CÁC CONTROLLERS ĐÃ CẬP NHẬT

---

## 📌 CẤP ĐỘ Cơ BẢN (Basic Usage)

### Luồng Thêm Mới (Add New Record)
```
1. Nhấn nút "Thêm" (Add button)
   ├─ Form sẽ xóa trắng
   └─ editMode = false (chế độ Add)

2. Nhập dữ liệu vào form

3. Nhấn nút "Lưu" (Save button)
   ├─ Kiểm tra editMode[0] == false
   ├─ Gọi dao.insert(object)
   └─ Reload dữ liệu lên bảng

4. Hoặc nhấn "Hủy" để xóa form
```

### Luồng Chỉnh Sửa (Edit Mode)
```
1. Chọn 1 dòng trên bảng (Click)
   ├─ Form sẽ điền dữ liệu từ dòng
   └─ editMode = true (chế độ Edit)

2. Sửa dữ liệu trong form

3. Nhấn nút "Lưu" (Save button)
   ├─ Kiểm tra editMode[0] == true
   ├─ Gọi dao.update(object)
   └─ Reload dữ liệu lên bảng

4. Hoặc nhấn nút "Sửa" để xác nhận chế độ sửa
```

### Luồng Xóa (Delete Record)
```
1. Chọn 1 dòng trên bảng (Click)

2. Nhấn nút "Xóa" (Delete button)

3. Xác nhận trong hộp thoại
   ├─ YES → Xóa dữ liệu, reload bảng
   └─ NO → Hủy thao tác, giữ dữ liệu

4. Form sẽ xóa trắng, editMode = false
```

---

## 🔧 PHÁT TRIỂN MỚI (Developer Guide)

### Tạo Controller Mới Theo Pattern Chuẩn

```java
package Controller.Module;

import Dao.EntityDAO;
import Model.Entity;
import View.Module.EntityPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class EntityController {
    private EntityPanel view;
    private EntityDAO dao;

    public EntityController(EntityPanel view) {
        this.view = view;
        this.dao = new EntityDAO();
        initEvents();
        loadData();
    }

    private void initEvents() {
        boolean[] editMode = {false};  // ✅ Biến theo dõi chế độ

        // ✅ Nút Xem/Load
        view.addBtnXemListener(e -> loadData());

        // ✅ Nút Tìm kiếm
        view.addBtnTimKiemListener(e -> {
            String key = view.getSearchKey();
            if (key.isEmpty()) {
                view.showMessage("Nhập từ khóa tìm kiếm");
                return;
            }
            List<Entity> list = dao.search(key);
            view.setTableData(list);
        });

        // ✅ Nút Thêm: Add mode
        view.addBtnThemListener(e -> {
            editMode[0] = false;
            view.clearForm();
        });

        // ✅ Nút Sửa: Edit mode
        view.addBtnSuaListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row == -1) {
                view.showMessage("Vui lòng chọn một bản ghi");
                return;
            }
            editMode[0] = true;
            view.fillForm(row);
        });

        // ✅ Nút Xóa: With confirmation
        view.addBtnXoaListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row == -1) {
                view.showMessage("Vui lòng chọn bản ghi cần xóa");
                return;
            }

            int id = (int) view.getTable().getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(
                view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(id);
                view.showMessage("Đã xoá");
                loadData();
                view.clearForm();
                editMode[0] = false;
            }
        });

        // ✅ Nút Lưu: Check editMode to insert/update
        view.addBtnLuuListener(e -> {
            Entity obj = view.getInput();
            
            // Validate
            if (obj.getId().isEmpty()) {
                view.showMessage("ID không được để trống");
                return;
            }

            // Insert or Update
            if (editMode[0]) {
                dao.update(obj);
                view.showMessage("Cập nhật thành công");
            } else {
                dao.insert(obj);
                view.showMessage("Thêm thành công");
            }

            loadData();
            view.clearForm();
            editMode[0] = false;
        });

        // ✅ Nút Hủy: Cancel edit
        view.addBtnHuyListener(e -> {
            view.clearForm();
            editMode[0] = false;
        });

        // ✅ Table click listener
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.fillForm(row);
                }
            }
        });
    }

    private void loadData() {
        List<Entity> list = dao.getAll();
        view.setTableData(list);
    }
}
```

---

## 🧪 TEST CHECKLIST

### Test Add Functionality
- [ ] Nhấn Nút Thêm → Form xóa trắng ✅
- [ ] Nhập dữ liệu → Nhấn Lưu ✅
- [ ] Dữ liệu xuất hiện trên bảng ✅
- [ ] editMode = false sau khi lưu ✅

### Test Edit Functionality
- [ ] Click dòng trên bảng → Form điền dữ liệu ✅
- [ ] Nhấn Nút Sửa (nếu có) → editMode = true ✅
- [ ] Sửa dữ liệu → Nhấn Lưu ✅
- [ ] Dữ liệu được cập nhật trên bảng ✅

### Test Delete Functionality
- [ ] Chọn dòng → Nhấn Xóa ✅
- [ ] Hộp xác nhận xuất hiện ✅
- [ ] Nhấn YES → Dữ liệu bị xóa ✅
- [ ] Nhấn NO → Giữ dữ liệu ✅

### Test Cancel Functionality
- [ ] Nhấn Thêm → Nhấn Hủy → Form xóa ✅
- [ ] Click bảng → Nhấn Hủy → Form xóa, editMode = false ✅

---

## ⚠️ NHỮNG LỖI THƯỜNG GẶP

### ❌ Lỗi 1: EditMode không được reset
```java
// SAI ❌
view.addBtnLuuListener(e -> {
    // ... logic ...
    loadData();
    // Quên reset editMode!
});

// ĐÚNG ✅
view.addBtnLuuListener(e -> {
    // ... logic ...
    loadData();
    view.clearForm();
    editMode[0] = false;  // ← Quan trọng!
});
```

### ❌ Lỗi 2: Không kiểm tra editMode khi lưu
```java
// SAI ❌ (Luôn insert, không bao giờ update)
view.addBtnLuuListener(e -> {
    dao.insert(obj);  // ← Quên kiểm tra editMode
});

// ĐÚNG ✅
view.addBtnLuuListener(e -> {
    if (editMode[0]) {
        dao.update(obj);
    } else {
        dao.insert(obj);
    }
});
```

### ❌ Lỗi 3: Xóa không có xác nhận
```java
// SAI ❌ (Dễ xóa nhầm dữ liệu)
view.addBtnXoaListener(e -> {
    dao.delete(id);  // ← Xóa ngay lập tức
});

// ĐÚNG ✅
view.addBtnXoaListener(e -> {
    int confirm = JOptionPane.showConfirmDialog(
        view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
        JOptionPane.YES_NO_OPTION
    );
    if (confirm == JOptionPane.YES_OPTION) {
        dao.delete(id);
    }
});
```

### ❌ Lỗi 4: Không load lại dữ liệu sau khi lưu/xóa
```java
// SAI ❌ (Bảng không update)
view.addBtnLuuListener(e -> {
    dao.insert(obj);
    // Quên loadData()
});

// ĐÚNG ✅
view.addBtnLuuListener(e -> {
    dao.insert(obj);
    loadData();  // ← Load lại dữ liệu
    view.clearForm();
    editMode[0] = false;
});
```

---

## 📚 CÁC CONTROLLERS ĐÃ CẬP NHẬT

| Controller | Path | Status |
|-----------|------|--------|
| PhongHocController | Controller/ThuTrang | ✅ Ready |
| TKBController | Controller/ThuTrang | ✅ Ready |
| DiemController | Controller/Tien | ✅ Ready |
| HanhKiemController | Controller/Tien | ✅ Ready |
| LichThiController | Controller/Tien | ✅ Ready |
| Hocphicontroller | Controller/HaTrang | ✅ Ready |
| Thongbaocontroller | Controller/HaTrang | ✅ Ready |
| Phuckhaocontroller | Controller/HaTrang | ✅ Ready |

---

## 🎓 TIPS & TRICKS

### 💡 Tip 1: Sử dụng boolean[] thay vì boolean
```java
// Tại sao? Để có thể thay đổi giá trị trong lambda
boolean[] editMode = {false};  // ✅ Có thể thay đổi
boolean editMode = false;      // ❌ Không thể thay đổi trong lambda
```

### 💡 Tip 2: Validate trước khi lưu
```java
if (obj.getId().isEmpty()) {
    view.showMessage("ID không được để trống");
    return;  // ← Dừng ngay, đừng lưu
}
```

### 💡 Tip 3: Luôn reset editMode sau mỗi tác vụ
```java
loadData();
view.clearForm();
editMode[0] = false;  // ← Cực kỳ quan trọng!
```

### 💡 Tip 4: Sử dụng JOptionPane cho xác nhận quan trọng
```java
int confirm = JOptionPane.showConfirmDialog(
    view,
    "Bạn có chắc chắn muốn xóa?",  // Message
    "Xác nhận",                     // Title
    JOptionPane.YES_NO_OPTION       // Button type
);
if (confirm == JOptionPane.YES_OPTION) {
    // Thực hiện thao tác
}
```

---

## 📞 HỖ TRỢ

Nếu bạn gặp vấn đề:
1. Kiểm tra báo cáo `CONTROLLER_STANDARDIZATION_REPORT.md`
2. Xem ví dụ từ các Controllers đã cập nhật
3. Kiểm tra lại checklist ở trên

---

**Happy Coding! 🚀**
