# 🎯 Hướng Dẫn Cập Nhật Controllers

## Mục Đích
Sau khi standardize các nút trên View Panels, Controllers cần được cập nhật 
để xử lý các nút mới (Thêm, Sửa, Lưu, Hủy) cùng với nút Xóa đã có.

---

## 📋 Các Controller Cần Cập Nhật

1. **QuanLyDiemController** (cho QuanLyDiemPanel)
2. **HanhKiemController** (cho HanhKiemPanel)
3. **LichThiController** (cho LichThiPanel)
4. **HocphiController** (cho QuanLyHocPhiPanel)
5. **ThongbaoController** (cho QuanlyThongbaoPanel)
6. **PhuckhaoController** (cho QuanLyPhucKhaoPanel)
7. **TKBController** (cho FrmTKB)

---

## 🔄 Pattern Chung - Cách Thực Hiện

### Bước 1: Khai báo trong Constructor

```java
public class XxxController {
    private XxxPanel panel;
    
    public XxxController(XxxPanel panel) {
        this.panel = panel;
        
        // Gán sự kiện cho các nút
        panel.addBtnThemListener(e -> handleThem());
        panel.addBtnSuaListener(e -> handleSua());
        panel.addBtnXoaListener(e -> handleXoa());
        panel.addBtnLuuListener(e -> handleLuu());
        panel.addBtnHuyListener(e -> handleHuy());
        
        // Các sự kiện khác (filter, search, v.v.)
        panel.addBtnXemListener(e -> loadData());
        panel.addBtnTimKiemListener(e -> searchData());
        panel.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = panel.getTable().getSelectedRow();
                if (row >= 0) {
                    panel.fillForm(row);
                }
            }
        });
    }
}
```

### Bước 2: Implement Handler Methods

```java
// ===== THÊM =====
private void handleThem() {
    panel.clearForm();           // Clear form để nhập dữ liệu mới
    panel.getTable().clearSelection();
    // Bất kỳ khởi tạo nào khác
}

// ===== SỬA =====
private void handleSua() {
    int row = panel.getTable().getSelectedRow();
    if (row < 0) {
        panel.showMessage("Vui lòng chọn một bản ghi để sửa!");
        return;
    }
    // Dữ liệu đã được load vào form bằng mouse listener
    // Cho phép người dùng chỉnh sửa
}

// ===== LƯUUU =====
private void handleLuu() {
    try {
        // Lấy dữ liệu từ form
        Xxx object = panel.getXxxInput(); // Hoặc tương tự
        
        if (object == null) {
            panel.showMessage("Vui lòng nhập dữ liệu hợp lệ!");
            return;
        }
        
        // Kiểm tra xem là insert hay update
        int selectedRow = panel.getTable().getSelectedRow();
        
        if (selectedRow < 0) {
            // INSERT - Thêm mới
            XxxDAO dao = new XxxDAO();
            boolean success = dao.add(object);
            if (success) {
                panel.showMessage("Thêm thành công!");
                loadData(); // Reload bảng
                panel.clearForm();
            } else {
                panel.showMessage("Thêm thất bại!");
            }
        } else {
            // UPDATE - Cập nhật
            // Set ID từ row được chọn
            object.setId(panel.getIdFromSelectedRow(selectedRow));
            
            XxxDAO dao = new XxxDAO();
            boolean success = dao.update(object);
            if (success) {
                panel.showMessage("Cập nhật thành công!");
                loadData(); // Reload bảng
                panel.clearForm();
            } else {
                panel.showMessage("Cập nhật thất bại!");
            }
        }
    } catch (Exception ex) {
        panel.showMessage("Lỗi: " + ex.getMessage());
        ex.printStackTrace();
    }
}

// ===== XÓA =====
private void handleXoa() {
    int row = panel.getTable().getSelectedRow();
    if (row < 0) {
        panel.showMessage("Vui lòng chọn một bản ghi để xóa!");
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(
        panel, 
        "Bạn có chắc muốn xóa bản ghi này?", 
        "Xác nhận xóa", 
        JOptionPane.YES_NO_OPTION
    );
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            String id = panel.getIdFromSelectedRow(row); // Hoặc tương tự
            XxxDAO dao = new XxxDAO();
            boolean success = dao.delete(id);
            
            if (success) {
                panel.showMessage("Xóa thành công!");
                loadData();
                panel.clearForm();
            } else {
                panel.showMessage("Xóa thất bại!");
            }
        } catch (Exception ex) {
            panel.showMessage("Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

// ===== HỦY =====
private void handleHuy() {
    panel.clearForm();
    panel.getTable().clearSelection();
    // Reset lại trạng thái ban đầu
}

// ===== LOAD DỮ LIỆU =====
private void loadData() {
    try {
        XxxDAO dao = new XxxDAO();
        List<Xxx> list = dao.getAll(); // Hoặc filter tùy logic
        panel.setTableData(list);
    } catch (Exception ex) {
        panel.showMessage("Lỗi tải dữ liệu: " + ex.getMessage());
        ex.printStackTrace();
    }
}

// ===== TÌM KIẾM =====
private void searchData() {
    String keyword = panel.getSearchKeyword();
    try {
        XxxDAO dao = new XxxDAO();
        List<Xxx> list = dao.search(keyword);
        panel.setTableData(list);
    } catch (Exception ex) {
        panel.showMessage("Lỗi tìm kiếm: " + ex.getMessage());
    }
}
```

---

## 📝 Ví Dụ Cụ Thể: QuanLyDiemController

```java
package Controller.Tien;

import View.Tien.QuanLyDiemPanel;
import Model.Diem;
import Dao.DiemDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

public class QuanLyDiemController {
    private QuanLyDiemPanel panel;
    private DiemDAO diemDAO;
    
    public QuanLyDiemController(QuanLyDiemPanel panel) {
        this.panel = panel;
        this.diemDAO = new DiemDAO();
        
        // Gán sự kiện cho các nút
        panel.addBtnXemListener(e -> loadAllData());
        panel.addBtnTimKiemListener(e -> searchData());
        
        panel.addBtnThemListener(e -> handleThem());
        panel.addBtnSuaListener(e -> handleSua());
        panel.addBtnXoaListener(e -> handleXoa());
        panel.addBtnLuuListener(e -> handleLuu());
        panel.addBtnHuyListener(e -> handleHuy());
        
        panel.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = panel.getTable().getSelectedRow();
                if (row >= 0) {
                    panel.fillFormInput(row);
                }
            }
        });
        
        // Tải dữ liệu ban đầu
        loadAllData();
    }
    
    private void loadAllData() {
        try {
            String maLop = panel.getMaLopFilter();
            String maMon = panel.getMaMonFilter();
            int hocKy = panel.getHocKyFilter();
            
            List<Diem> diems = diemDAO.getDiemByFilter(maLop, maMon, hocKy);
            panel.setTableData(diems);
        } catch (Exception ex) {
            panel.showMessage("Lỗi tải dữ liệu: " + ex.getMessage());
        }
    }
    
    private void searchData() {
        try {
            String keyword = panel.getTuKhoaTimKiem();
            List<Diem> diems = diemDAO.searchByKeyword(keyword);
            panel.setTableData(diems);
        } catch (Exception ex) {
            panel.showMessage("Lỗi tìm kiếm: " + ex.getMessage());
        }
    }
    
    private void handleThem() {
        panel.clearForm();
        panel.getTable().clearSelection();
        // Tập trung vào form để nhập dữ liệu mới
    }
    
    private void handleSua() {
        int row = panel.getTable().getSelectedRow();
        if (row < 0) {
            panel.showMessage("Vui lòng chọn một bản ghi để sửa!");
            return;
        }
        // Form đã được điền từ mouse listener
    }
    
    private void handleLuu() {
        try {
            int selectedRow = panel.getTable().getSelectedRow();
            Diem diem = panel.getDiemInput();
            
            if (diem == null) {
                panel.showMessage("Vui lòng nhập dữ liệu điểm hợp lệ!");
                return;
            }
            
            if (selectedRow < 0) {
                // Insert
                boolean success = diemDAO.add(diem);
                if (success) {
                    panel.showMessage("Thêm điểm thành công!");
                    loadAllData();
                    panel.clearForm();
                } else {
                    panel.showMessage("Thêm điểm thất bại!");
                }
            } else {
                // Update
                boolean success = diemDAO.update(diem);
                if (success) {
                    panel.showMessage("Cập nhật điểm thành công!");
                    loadAllData();
                    panel.clearForm();
                } else {
                    panel.showMessage("Cập nhật điểm thất bại!");
                }
            }
        } catch (Exception ex) {
            panel.showMessage("Lỗi: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    private void handleXoa() {
        int row = panel.getTable().getSelectedRow();
        if (row < 0) {
            panel.showMessage("Vui lòng chọn một bản ghi để xóa!");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            panel, 
            "Bạn có chắc muốn xóa bản ghi điểm này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String maHS = panel.getTable().getValueAt(row, 0).toString();
                boolean success = diemDAO.deleteByMaHS(maHS);
                
                if (success) {
                    panel.showMessage("Xóa điểm thành công!");
                    loadAllData();
                    panel.clearForm();
                } else {
                    panel.showMessage("Xóa điểm thất bại!");
                }
            } catch (Exception ex) {
                panel.showMessage("Lỗi: " + ex.getMessage());
            }
        }
    }
    
    private void handleHuy() {
        panel.clearForm();
        panel.getTable().clearSelection();
    }
}
```

---

## 📌 Quy Ước Hàm

### Getter Methods (Panel cung cấp)
- `getXxxInput()` - Lấy dữ liệu từ form input
- `getTable()` - Lấy JTable để xử lý selection
- `getTuKhoaTimKiem()` - Lấy từ khóa tìm kiếm
- `getXxxFilter()` - Lấy giá trị filter

### Setter Methods (Panel cung cấp)
- `setTableData(List<Xxx>)` - Điền dữ liệu vào bảng
- `fillForm(int row)` - Điền dữ liệu từ bảng vào form
- `clearForm()` - Clear form input
- `showMessage(String)` - Hiển thị dialog thông báo

### Listener Methods (Controller sẽ gọi)
- `addBtnXxxListener(ActionListener)` - Gán sự kiện cho nút

---

## ✨ Best Practices

1. **Luôn clear form sau khi Thêm/Sửa thành công**
   ```java
   loadData();
   panel.clearForm();
   ```

2. **Hỏi xác nhận trước khi Xóa**
   ```java
   int confirm = JOptionPane.showConfirmDialog(...);
   ```

3. **Validate dữ liệu trước khi Lưu**
   ```java
   Xxx obj = panel.getXxxInput();
   if (obj == null) {
       panel.showMessage("Dữ liệu không hợp lệ!");
       return;
   }
   ```

4. **Handle Exception một cách chi tiết**
   ```java
   try {
       // Code
   } catch (Exception ex) {
       panel.showMessage("Lỗi: " + ex.getMessage());
       ex.printStackTrace();
   }
   ```

5. **Phân biệt Insert vs Update**
   ```java
   int selectedRow = panel.getTable().getSelectedRow();
   if (selectedRow < 0) {
       // INSERT
   } else {
       // UPDATE
   }
   ```

---

## 🚀 Khi Nào Thực Hiện

1. ✅ Khi quay lại mainframe/panel, Controller được khởi tạo
2. ✅ Khi người dùng click vào các nút, ActionListener được gọi
3. ✅ Khi load dữ liệu thành công, `setTableData()` được gọi
4. ✅ Khi người dùng click vào bảng, form được điền tự động

---

## ⚠️ Lưu Ý Quan Trọng

- **Đừng quên gán listener trong constructor**
- **Luôn validate dữ liệu trước khi lưu**
- **Luôn xử lý Exception để tránh crash**
- **Luôn reload bảng sau khi thay đổi dữ liệu**
- **Luôn clear form và deselect bảng sau hoàn thành**

---

**Generated**: 2024
**Status**: Ready for Implementation
