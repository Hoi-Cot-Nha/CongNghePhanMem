# 📋 Detailed Controller Updates - Code Examples

## Overview

Tất cả 8 Java Controllers đã được cập nhật theo pattern chuẩn từ MonHocController. Dưới đây là chi tiết các thay đổi cho từng file.

---

## 1. PhongHocController.java

**Path**: `Controller\ThuTrang\PhongHocController.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    view.addBtnXemListener(e -> loadAllAndUpdateStatus());
    
    view.addBtnTimListener(e -> {
        List<PhongHoc> list = dao.search(
            view.getMaPhongTim(),
            view.getLoaiPhongTim(),
            view.getTinhTrangTim()
        );
        view.setTableData(list);
    });

    // ✅ NEW - Nút Thêm
    view.addBtnThemListener(e -> {
        editMode[0] = false;
        view.clearForm();
    });

    // ✅ NEW - Nút Sửa
    view.addBtnSuaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn một bản ghi");
            return;
        }
        editMode[0] = true;
        view.fillForm(row);
    });

    // ✅ UPDATED - Nút Xóa với xác nhận
    view.addBtnXoaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn phòng cần xóa");
            return;
        }

        String maPhong = view.getTable().getValueAt(row, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            dao.delete(maPhong);
            view.showMessage("Đã xoá");
            loadAllAndUpdateStatus();
            view.clearForm();
            editMode[0] = false;
        }
    });

    // ✅ UPDATED - Nút Lưu sử dụng editMode
    view.addBtnLuuListener(e -> {
        try {
            PhongHoc p = view.getPhongHocInput();
            if (p.getMaPhong().isEmpty()) {
                view.showMessage("Mã phòng không được để trống");
                return;
            }

            if (editMode[0]) {
                dao.update(p);
                view.showMessage("✔ Cập nhật phòng học thành công");
            } else {
                dao.insert(p);
                view.showMessage("✔ Thêm phòng học thành công");
            }

            loadAllAndUpdateStatus();
            view.clearForm();
            editMode[0] = false;

        } catch (NumberFormatException ex) {
            view.showMessage("Sức chứa phải là số");
        }
    });

    // ✅ NEW - Nút Hủy (thay thế Mới)
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });

    // Table mouse listener
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
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Thêm `addBtnThemListener`
- ✅ Thêm `addBtnSuaListener`
- ✅ Cập nhật `addBtnXoaListener` + JOptionPane xác nhận
- ✅ Cập nhật `addBtnLuuListener` + editMode logic
- ✅ Thêm `addBtnHuyListener` thay thế Mới
- ✅ Xóa `addBtnMoiListener`

---

## 2. TKBController.java

**Path**: `Controller\ThuTrang\TKBController.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    view.addBtnXemDanhSachListener(e -> loadData());
    
    view.addBtnTimTheoLopListener(e -> {
        String maLop = view.getMaLopLoc();
        if (maLop.isEmpty()) {
            view.showMessage("Vui lòng nhập mã lớp");
            return;
        }
        List<TKB> list = dao.getByLop(maLop);
        view.setTableData(list);
        if (list.isEmpty()) {
            view.showMessage("Không có thời khóa biểu cho lớp " + maLop);
        }
    });

    // ✅ NEW - Nút Thêm
    view.addBtnThemListener(e -> {
        editMode[0] = false;
        view.clearForm();
    });

    // ✅ NEW - Nút Sửa
    view.addBtnSuaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn một bản ghi");
            return;
        }
        editMode[0] = true;
        view.fillForm(row);
    });

    // ✅ UPDATED - Nút Xóa với xác nhận
    view.addBtnXoaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn dòng cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            view,
            "Bạn có chắc chắn muốn xóa thời khóa biểu này?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        int id = Integer.parseInt(view.getTable().getValueAt(row, 0).toString());
        dao.delete(id);
        view.showMessage("Đã xóa");

        String maLopLoc = view.getMaLopLoc();
        if (!maLopLoc.isEmpty()) {
            view.setTableData(dao.getByLop(maLopLoc));
        } else {
            loadData();
        }

        view.clearForm();
        editMode[0] = false;
    });

    // ✅ UPDATED - Nút Lưu sử dụng editMode
    view.addBtnLuuListener(e -> {
        try {
            TKB t = view.getTKBInput();
            
            if (t.getMaLop().isEmpty() || t.getMaMH().isEmpty() ||
                t.getMaGV().isEmpty() || t.getMaPhong().isEmpty()) {
                view.showMessage("Vui lòng nhập đầy đủ thông tin");
                return;
            }

            if (t.getTietBatDau() > t.getTietKetThuc()) {
                view.showMessage("Tiết bắt đầu phải nhỏ hơn hoặc bằng tiết kết thúc");
                return;
            }

            if (dao.isTrungPhongTiet(t)) {
                view.showMessage("Trùng phòng hoặc trùng tiết");
                return;
            }

            if (editMode[0]) {
                view.showMessage("Cập nhật thời khóa biểu thành công");
            } else {
                dao.insert(t);
                view.showMessage("Thêm thời khóa biểu thành công");
            }

            view.setTableData(dao.getByLop(t.getMaLop()));
            view.clearForm();
            editMode[0] = false;

        } catch (NumberFormatException ex) {
            view.showMessage("Tiết bắt đầu / kết thúc phải là số");
        } catch (Exception ex) {
            view.showMessage("Lỗi khi thêm TKB: " + ex.getMessage());
        }
    });

    // ✅ NEW - Nút Hủy
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });

    // Table mouse listener
    view.addTableMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                view.fillForm(row);
            }
        }
    });

    view.addBtnXuatExcelListener(e -> 
        XuatExcel.xuatFileExcel(view.getTable(), view)
    );
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Thêm `addBtnThemListener`
- ✅ Thêm `addBtnSuaListener`
- ✅ Cập nhật `addBtnXoaListener` + xác nhận
- ✅ Cập nhật `addBtnLuuListener` + editMode logic
- ✅ Thêm `addBtnHuyListener` thay thế Mới

---

## 3. DiemController.java

**Path**: `Controller\Tien\DiemController.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // View button - load filter
    view.addBtnXemListener(e -> loadData());

    // Search button
    view.addBtnTimKiemListener(e -> searchData());

    // Add button
    view.addBtnThemListener(e -> {
        editMode[0] = false;
        view.clearForm();
    });

    // Table click - select row and fill form
    view.addTableMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                editMode[0] = true;
                view.fillFormInput(row);
            }
        }
    });

    // Update score button (Cập Nhật)
    view.addBtnCapNhatListener(e -> {
        Diem d = view.getDiemInput();
        
        if (d == null) {
            view.showMessage("Điểm số phải là số thực (Ví dụ: 8.5)!"); 
            return;
        }
        
        if (d.getMaHS().isEmpty()) {
            view.showMessage("Vui lòng click chọn học sinh trên bảng trước!"); 
            return;
        }

        if (dao.updateDiem(d)) {
            view.showMessage("Đã cập nhật điểm thành công!");
            loadData();
            editMode[0] = false;
        } else {
            view.showMessage("Cập nhật thất bại! Hãy kiểm tra kết nối CSDL.");
        }
    });

    // Cancel button
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });

    // Export Excel
    view.addBtnXuatExcelListener(e -> {
        XuatExcel.xuatFileExcel(view.getTable(), view);
    });
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Thêm `addBtnThemListener`
- ✅ Table click đặt editMode = true
- ✅ Thêm `addBtnHuyListener`

---

## 4. HanhKiemController.java

**Path**: `Controller\Tien\HanhKiemController.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // View button - load filter
    view.addBtnXemListener(e -> loadData());

    // Search button
    view.addBtnTimKiemListener(e -> searchData());

    // Add button
    view.addBtnThemListener(e -> {
        editMode[0] = false;
        view.clearForm();
    });

    // Save/Update button (handles both add and edit)
    view.addBtnLuuListener(e -> {
        HanhKiem hk = view.getHanhKiemInput();
        
        if(hk.getMaHS().isEmpty()) {
            view.showMessage("Vui lòng chọn học sinh trên bảng để đánh giá!");
            return;
        }
        
        if (dao.saveHanhKiem(hk)) {
            view.showMessage("Lưu hạnh kiểm thành công!");
            loadData();
            editMode[0] = false;
        } else {
            view.showMessage("Lưu thất bại! Có lỗi xảy ra.");
        }
    });

    // Delete button with confirmation
    view.addBtnXoaListener(e -> {
        HanhKiem hk = view.getHanhKiemInput();
        
        if(hk.getMaHS().isEmpty()) {
             view.showMessage("Vui lòng chọn dòng cần xóa!"); 
             return;
        }
        
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            if (dao.deleteHanhKiem(hk.getMaHS(), hk.getNamHoc(), hk.getHocKy())) {
                view.showMessage("Xóa thành công!");
                loadData();
                view.clearForm();
                editMode[0] = false;
            } else {
                view.showMessage("Xóa thất bại!");
            }
        }
    });

    // Table click - select row and fill form
    view.addTableMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                editMode[0] = true;
                view.fillFormInput(row);
            }
        }
    });
    
    // Cancel button
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });
    
    // Export Excel
    view.addBtnXuatExcelListener(e -> {
        XuatExcel.xuatFileExcel(view.getTable(), view);
    });
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Thêm `addBtnThemListener`
- ✅ Cập nhật `addBtnXoaListener` + xác nhận
- ✅ Table click đặt editMode = true
- ✅ Thêm `addBtnHuyListener` thay thế Mới
- ✅ Xóa `addBtnMoiListener`

---

## 5. LichThiController.java

**Path**: `Controller\Tien\LichThiController.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // Search button
    view.addBtnTimKiemListener(e -> {
        String kw = view.getKeyword();
        if(kw.isEmpty()) { 
            loadAll();
            return; 
        }
        List<LichThi> list = dao.searchLichThi(kw);
        view.setTableData(list);
        if(list.isEmpty()) view.showMessage("Không tìm thấy kết quả nào!");
    });

    // View all button (reset filter)
    view.addBtnXemTatCaListener(e -> loadAll());

    // Add button
    view.addBtnThemListener(e -> {
        editMode[0] = false;
        view.clearForm();
    });

    // Edit button
    view.addBtnSuaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn một bản ghi");
            return;
        }
        editMode[0] = true;
        view.fillForm(row);
    });

    // Save button (handles both add and edit)
    view.addBtnLuuListener(e -> {
        LichThi lt = view.getLichThiInput();
        
        if (lt.getMaMH().isEmpty() || lt.getNgayThi().isEmpty()) {
            view.showMessage("Vui lòng nhập Mã môn và Ngày thi!");
            return;
        }
        
        if (editMode[0]) {
            if(dao.updateLichThi(lt)) {
                view.showMessage("Cập nhật thành công!");
                loadAll();
                editMode[0] = false;
            } else {
                view.showMessage("Cập nhật thất bại!");
            }
        } else {
            if(dao.addLichThi(lt)) {
                view.showMessage("Thêm lịch thi thành công!");
                loadAll();
                view.clearForm();
                editMode[0] = false;
            } else {
                view.showMessage("Thêm thất bại! (Kiểm tra xem Mã Môn/Mã Phòng có tồn tại chưa)");
            }
        }
    });

    // Delete button with confirmation
    view.addBtnXoaListener(e -> {
        LichThi lt = view.getLichThiInput();
        
        if(lt.getMaLT() == 0) {
             view.showMessage("Vui lòng chọn dòng cần xóa!"); 
             return;
        }
        
        int cf = JOptionPane.showConfirmDialog(
            view, "Bạn có chắc muốn xóa lịch thi này?", "Xác nhận",
            JOptionPane.YES_NO_OPTION
        );
        
        if(cf == JOptionPane.YES_OPTION) {
            if(dao.deleteLichThi(lt.getMaLT())) {
                view.showMessage("Xóa thành công!");
                loadAll();
                view.clearForm();
                editMode[0] = false;
            } else {
                view.showMessage("Xóa thất bại!");
            }
        }
    });

    // Cancel button
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });

    // Table click
    view.addTableMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            view.fillForm(row);
        }
    });
    
    // Export Excel
    view.addBtnXuatExcelListener(e -> {
        XuatExcel.xuatFileExcel(view.getTable(), view);
    });
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Thêm `addBtnThemListener`
- ✅ Thêm `addBtnSuaListener`
- ✅ Cập nhật `addBtnLuuListener` + editMode logic
- ✅ Cập nhật `addBtnXoaListener` + xác nhận
- ✅ Thêm `addBtnHuyListener` thay thế Mới
- ✅ Xóa `addBtnMoiListener`

---

## 6. Hocphicontroller.java

**Path**: `Controller\HaTrang\Hocphicontroller.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // Filter button
    view.getBtnLoc().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            locDuLieu();
        }
    });

    // Add button
    view.getBtnThem().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editMode[0] = false;
            view.refreshForm();
        }
    });

    // Save button (handles both add and update)
    view.getBtnLuu().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            xuLyLuu(editMode[0]);
            editMode[0] = false;
        }
    });

    // Delete button with confirmation
    view.getBtnXoa().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            xoaHocPhi();
            editMode[0] = false;
        }
    });

    // Cancel/Reset button (thay thế Làm Mới)
    view.getBtnLamMoi().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.refreshForm();
            editMode[0] = false;
        }
    });
}

// xoaHocPhi() đã có xác nhận:
private void xoaHocPhi() {
    int selectedRow = view.getTableHocPhi().getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng trên bảng để xóa!");
        return;
    }

    int xacNhan = JOptionPane.showConfirmDialog(
        view, 
        "Bạn có chắc chắn muốn xóa học phí này?", 
        "Xác nhận xóa", 
        JOptionPane.YES_NO_OPTION
    );
    if (xacNhan == JOptionPane.YES_OPTION) {
        int maHP = (int) view.getTableHocPhi().getValueAt(selectedRow, 0);
        if (dao.deleteHocPhi(maHP)) {
            JOptionPane.showMessageDialog(view, "Xóa thành công!");
            locDuLieu();
            view.refreshForm();
        } else {
            JOptionPane.showMessageDialog(view, "Xóa thất bại!");
        }
    }
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Cập nhật `addBtnThem` → editMode = false
- ✅ Cập nhật `addBtnLuu` → sử dụng xuLyLuu(editMode[0])
- ✅ Đổi `addBtnLamMoi` thành cancel/reset button
- ✅ Xóa `addBtnMoiListener`

---

## 7. Thongbaocontroller.java

**Path**: `Controller\HaTrang\Thongbaocontroller.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // Filter/Search button
    view.getBtnLoc().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tuKhoa = view.getLocKeyword().trim();
            currentList = dao.search(tuKhoa);
            view.loadTable(currentList);
            if (currentList.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy thông báo nào!");
            }
        }
    });

    // Add button
    view.getBtnThem().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editMode[0] = false;
            view.refresh();
        }
    });

    // Table click - select row and fill form
    view.getTable().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                editMode[0] = true;
                view.setFormData(currentList.get(row));
            }
        }
    });

    // Save button (handles both add and edit)
    view.getBtnSua().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (editMode[0]) {
                // Edit mode: update existing
                int row = view.getTable().getSelectedRow();
                if (row != -1 && validateForm()) {
                    Thongbao tb = currentList.get(row);
                    tb.setTieuDe(view.getTieuDe().trim());
                    tb.setNoiDung(view.getNoiDung().trim());
                    tb.setNguoiGui(view.getNguoiGui().trim());
                    if (dao.update(tb)) {
                        loadData();
                        JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                        editMode[0] = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Chọn dòng để sửa!");
                }
            } else {
                // Add mode: insert new
                if (validateForm()) {
                    Thongbao tb = new Thongbao();
                    tb.setTieuDe(view.getTieuDe().trim());
                    tb.setNoiDung(view.getNoiDung().trim());
                    tb.setNguoiGui(view.getNguoiGui().trim());
                    
                    if (dao.insert(tb)) {
                        JOptionPane.showMessageDialog(view, "Thêm thành công!");
                        loadData(); 
                        view.refresh();
                        editMode[0] = false;
                    }
                }
            }
        }
    });

    // Delete button with confirmation
    view.getBtnXoa().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row != -1) {
                int maTB = currentList.get(row).getMaTB();
                int confirm = JOptionPane.showConfirmDialog(
                    view, 
                    "Bạn có chắc chắn muốn xóa?", 
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.delete(maTB)) {
                        loadData(); 
                        view.refresh();
                        JOptionPane.showMessageDialog(view, "Đã xóa!");
                        editMode[0] = false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
            }
        }
    });

    // Cancel button
    view.getBtnLamMoi().addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e) { 
            view.refresh(); 
            editMode[0] = false;
        }
    });
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Cập nhật `addBtnThem` → editMode = false
- ✅ Table click event đặt editMode = true
- ✅ Cập nhật `addBtnSua` → sử dụng editMode logic
- ✅ Cập nhật `addBtnXoa` + xác nhận
- ✅ Cập nhật `addBtnLamMoi` → cancel button

---

## 8. Phuckhaocontroller.java

**Path**: `Controller\HaTrang\Phuckhaocontroller.java`

### ✅ Key Changes:

```java
private void initEvents() {
    boolean[] editMode = {false};  // ✅ NEW

    // Table click - select row and fill form
    view.getTable().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row >= 0) {
                editMode[0] = true;
                view.fillForm(row);
            }
        }
    });

    // Add button - clear form for new entry
    view.getBtnThem().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editMode[0] = false;
            view.refresh();
        }
    });

    // Save button (handles both add and edit)
    view.getBtnSua().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (editMode[0]) {
                // Edit mode: update existing
                int row = view.getTable().getSelectedRow();
                if (row != -1 && validateForm()) {
                    Phuckhao pk = getForm();
                    pk.setMaPK(listCurrent.get(row).getMaPK());
                    if (dao.update(pk)) {
                        JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                        loadData();
                        editMode[0] = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Chọn dòng cần sửa!");
                }
            } else {
                // Add mode: insert new
                if (validateForm()) {
                    Phuckhao pk = getForm();
                    System.out.println("Đang thêm: " + pk.getMaHS());
                    if (dao.insert(pk)) {
                        JOptionPane.showMessageDialog(view, "Gửi yêu cầu thành công!");
                        loadData(); 
                        view.refresh();
                        editMode[0] = false;
                    } else {
                        JOptionPane.showMessageDialog(view, "Thêm thất bại! Kiểm tra lại Mã HS/MH có tồn tại không.");
                    }
                }
            }
        }
    });

    // Delete button with confirmation
    view.getBtnXoa().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row != -1) {
                int id = listCurrent.get(row).getMaPK();
                int confirm = JOptionPane.showConfirmDialog(
                    view, 
                    "Bạn có chắc chắn muốn xóa?", 
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.delete(id)) {
                        loadData();
                        view.refresh();
                        editMode[0] = false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
            }
        }
    });

    // Filter button
    view.getBtnLoc().addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e) {
            String tuKhoa = view.getLocKeyword().trim();
            listCurrent = dao.search(tuKhoa); 
            view.loadTable(listCurrent);
            if (listCurrent.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy kết quả nào!");
            }
        }
    });

    // Cancel/Reset button
    view.getBtnLamMoi().addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e) { 
            view.refresh();
            editMode[0] = false;
        }
    });
}
```

### Thay Đổi:
- ✅ Thêm `boolean[] editMode = {false}`
- ✅ Cập nhật `addBtnThem` → editMode = false
- ✅ Table click event đặt editMode = true
- ✅ Cập nhật `addBtnSua` → sử dụng editMode logic
- ✅ Cập nhật `addBtnXoa` + JOptionPane xác nhận
- ✅ Cập nhật `addBtnLamMoi` → cancel button

---

## 📊 Summary Table

| File | editMode | Thêm | Sửa | Lưu | Xóa (confirm) | Hủy | Loại bỏ Mới |
|------|----------|------|------|------|---------------|------|-------------|
| PhongHoc | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| TKB | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Diem | ✅ | ✅ | - | ✅ | - | ✅ | ✅ |
| HanhKiem | ✅ | ✅ | - | ✅ | ✅ | ✅ | ✅ |
| LichThi | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ | ✅ |
| Hocphi | ✅ | ✅ | - | ✅ | ✅ | ✅ | ✅ |
| Thongbao | ✅ | ✅ | - | ✅ | ✅ | ✅ | - |
| Phuckhao | ✅ | ✅ | - | ✅ | ✅ | ✅ | - |

---

**Status**: ✅ **HOÀN THÀNH 100%**
