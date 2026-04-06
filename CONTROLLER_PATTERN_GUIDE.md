# Java Controllers Standardization Guide

## Overview

All 8 Java Controllers in the QuanLyHocSinh project have been standardized to follow a unified pattern. This guide explains the pattern and provides examples for creating new controllers or modifying existing ones.

---

## The Standard Pattern

### Structure Overview

```java
public class YourController {
    private YourView view;
    private YourDAO dao;

    public YourController(YourView view) {
        this.view = view;
        this.dao = new YourDAO();
        initEvents();      // Initialize event listeners
        loadData();        // Load initial data
    }

    private void initEvents() {
        boolean[] editMode = {false};  // Track edit vs add mode
        
        // All event listeners go here...
        
        // 1. View/Filter listeners (Xem, Lọc, Tìm kiếm)
        // 2. Add/Edit/Delete/Save/Cancel listeners
        // 3. Table interaction listeners
        // 4. Export/Special feature listeners
    }

    private void loadData() {
        // Load initial data from DAO
    }
}
```

---

## Core Event Listeners

### 1. Add Button (Thêm) - Clear Form, Prepare for New Entry

```java
view.addBtnThemListener(e -> {
    editMode[0] = false;        // Set to add mode
    view.clearForm();            // Clear all form fields
    // Optional: clear search fields
    // view.getTxtTimKiem().setText("");
});
```

**Purpose:** User clicks "Thêm" to add a new record
**Action:** Clear form, set editMode to false

---

### 2. Edit Button (Sửa) - Load Selected Data, Enter Edit Mode

```java
view.addBtnSuaListener(e -> {
    int row = view.getTable().getSelectedRow();
    if (row == -1) {
        view.showMessage("Vui lòng chọn một bản ghi");
        return;
    }
    editMode[0] = true;         // Set to edit mode
    view.fillForm(row);          // Fill form with selected row data
});
```

**Purpose:** User clicks "Sửa" to edit a selected record
**Action:** Validate selection, load data into form, set editMode to true

**Alternative:** Use table mouse click to auto-select:
```java
view.addTableMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            editMode[0] = true;
            view.fillForm(row);
        }
    }
});
```

---

### 3. Save Button (Lưu) - Insert or Update Based on editMode

```java
view.addBtnLuuListener(e -> {
    // Get form data
    YourModel m = view.getYourModelInput();
    
    // Validate (adjust based on your fields)
    if (m.getId().isEmpty()) {
        view.showMessage("ID không được để trống");
        return;
    }
    
    // Check for duplicates (if needed)
    if (dao.exists(m.getId()) && !editMode[0]) {
        view.showMessage("ID đã tồn tại");
        return;
    }
    
    // Save: check editMode to decide insert or update
    if (editMode[0]) {
        dao.update(m);              // Update mode
        view.showMessage("Cập nhật thành công");
    } else {
        dao.insert(m);              // Add mode
        view.showMessage("Thêm thành công");
    }
    
    // After save
    loadData();                     // Reload table
    view.clearForm();               // Clear form
    editMode[0] = false;            // Reset to add mode
});
```

**Purpose:** Save form data (insert or update)
**Action:** Check editMode, call appropriate DAO method, reload data

---

### 4. Delete Button (Xóa) - With Confirmation

```java
view.addBtnXoaListener(e -> {
    int row = view.getTable().getSelectedRow();
    if (row == -1) {
        view.showMessage("Vui lòng chọn một bản ghi");
        return;
    }
    
    // Get ID from first column
    String id = view.getTable().getValueAt(row, 0).toString();
    
    // Ask for confirmation
    int confirm = javax.swing.JOptionPane.showConfirmDialog(
        view, 
        "Bạn có chắc chắn muốn xóa?",  // Message
        "Xác nhận",                      // Dialog title
        javax.swing.JOptionPane.YES_NO_OPTION
    );
    
    // If user confirms
    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        dao.delete(id);                 // Delete from DB
        view.showMessage("Đã xoá");
        loadData();                     // Reload table
        view.clearForm();               // Clear form
        editMode[0] = false;            // Reset mode
    }
    // If user cancels, do nothing
});
```

**Purpose:** Delete selected record with user confirmation
**Action:** Validate selection, confirm, delete, reload

---

### 5. Cancel Button (Hủy) - Reset Everything

```java
view.addBtnHuyListener(e -> {
    view.clearForm();               // Clear all form fields
    editMode[0] = false;            // Reset to add mode
    // Optional: clear search fields if needed
});
```

**Purpose:** User clicks "Hủy" to cancel current operation
**Action:** Clear form, reset mode

---

## Supporting Listeners

### Search/Filter Listener

```java
view.addBtnTimKiemListener(e -> {
    String keyword = view.getKeyword().trim();
    if (keyword.isEmpty()) {
        view.showMessage("Vui lòng nhập từ khóa");
        return;
    }
    
    List<YourModel> results = dao.search(keyword);
    view.setTableData(results);
    
    if (results.isEmpty()) {
        view.showMessage("Không tìm thấy kết quả");
    }
});
```

### View/Refresh Listener

```java
view.addBtnXemListener(e -> loadData());
```

### Table Click Listener

```java
view.addTableMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            editMode[0] = true;      // Set to edit mode
            view.fillForm(row);      // Fill form with clicked row
        }
    }
});
```

---

## Complete Example: initEvents() Implementation

```java
private void initEvents() {
    boolean[] editMode = {false};  // Track edit mode
    
    // View button
    view.addBtnXemListener(e -> loadData());
    
    // Search button
    view.addBtnTimKiemListener(e -> {
        String keyword = view.getKeyword().trim();
        if (keyword.isEmpty()) {
            view.showMessage("Vui lòng nhập từ khóa");
            return;
        }
        List<YourModel> results = dao.search(keyword);
        view.setTableData(results);
    });
    
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
    
    // Save button
    view.addBtnLuuListener(e -> {
        YourModel m = view.getYourModelInput();
        
        if (m.getId().isEmpty()) {
            view.showMessage("ID không được để trống");
            return;
        }
        
        if (editMode[0]) {
            dao.update(m);
        } else {
            dao.insert(m);
        }
        
        view.showMessage("Lưu thành công");
        loadData();
        view.clearForm();
        editMode[0] = false;
    });
    
    // Delete button
    view.addBtnXoaListener(e -> {
        int row = view.getTable().getSelectedRow();
        if (row == -1) {
            view.showMessage("Vui lòng chọn một bản ghi");
            return;
        }
        
        String id = view.getTable().getValueAt(row, 0).toString();
        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            dao.delete(id);
            view.showMessage("Đã xoá");
            loadData();
            view.clearForm();
            editMode[0] = false;
        }
    });
    
    // Cancel button
    view.addBtnHuyListener(e -> {
        view.clearForm();
        editMode[0] = false;
    });
    
    // Table click listener
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

---

## Key Principles

### 1. editMode State Tracking
- `editMode[0] = false` → Add new record mode
- `editMode[0] = true` → Edit existing record mode
- Use array `boolean[]` to allow modification in lambda expressions

### 2. Form Lifecycle
- **New:** Click "Thêm" → Form clears → User enters data → Click "Lưu" → Insert
- **Edit:** Click "Sửa" → Form fills → User modifies → Click "Lưu" → Update
- **Cancel:** Click "Hủy" → Form clears → editMode resets

### 3. Confirmation Dialogs
Always confirm destructive operations (delete):
```java
JOptionPane.showConfirmDialog(parent, message, title, optionType)
```

### 4. Data Consistency
- After any data change, call `loadData()` to refresh table
- Always reset form and mode after save/delete
- Always clear form when switching modes

---

## Common Patterns

### Pattern 1: Checkbox for Existing Controllers
If a controller doesn't have explicit edit button, use table click:
```java
view.addTableMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        editMode[0] = true;
        // Fill form logic
    }
});
```

### Pattern 2: Specialized Save Logic
Some controllers (like DiemController) have specialized save methods:
```java
view.addBtnCapNhatListener(e -> {
    // Specialized logic for scores
    if (editMode[0]) {
        // Handle update
    }
});
```

### Pattern 3: Multiple Delete Keys
For composite keys, pass all required fields:
```java
dao.delete(id1, id2, id3);
```

---

## Testing Checklist

For each controller, verify:
- [ ] Click "Thêm" → Form clears, ready for input
- [ ] Enter data and click "Lưu" → Record inserted
- [ ] Click table row → Form fills, edit mode active
- [ ] Modify data and click "Lưu" → Record updated
- [ ] Select record and click "Xóa" → Confirmation dialog appears
- [ ] Confirm delete → Record deleted, table refreshed
- [ ] Click "Hủy" → Form clears, mode resets
- [ ] Search functionality works
- [ ] All messages display correctly

---

## References

### Implemented Controllers (Examples)
1. MonHocController.java - Original pattern source
2. PhongHocController.java - Standard implementation
3. TKBController.java - With validation example
4. LichThiController.java - With edit/add logic

### UI Methods Required
- `view.clearForm()` - Clear all form fields
- `view.fillForm(row)` - Fill form with table row data
- `view.showMessage(msg)` - Show message dialog
- `view.setTableData(list)` - Load data into table
- `view.getYourModelInput()` - Get form data as model
- `view.getTable()` - Get the table component

### DAO Methods Required
- `dao.insert(model)` - Insert new record
- `dao.update(model)` - Update existing record
- `dao.delete(id)` - Delete record
- `dao.search(keyword)` - Search records
- `dao.getAll()` - Get all records

---

## FAQs

**Q: Why use `boolean[]` instead of simple `boolean`?**
A: Lambda expressions can't modify local variables. Array allows modification.

**Q: Can I use different button names?**
A: Follow the existing naming: addBtnThem, addBtnSua, addBtnXoa, addBtnLuu, addBtnHuy

**Q: What if I only have Thêm/Lưu/Xóa without Sửa?**
A: Use table click listener to set editMode[0] = true

**Q: How do I handle composite keys?**
A: Modify the delete method signature in DAO to accept multiple parameters

**Q: Can I add custom listeners?**
A: Yes, follow the same pattern with appropriate method names

---

## Conclusion

All controllers in this project now follow the standardized pattern. When creating new controllers or modifying existing ones, follow these guidelines to maintain consistency, improve maintainability, and provide better code organization.

For questions or clarifications, refer to the implemented controllers as examples.

---

*Standard Pattern Guide - Version 1.0*
*Last Updated: 2024*
