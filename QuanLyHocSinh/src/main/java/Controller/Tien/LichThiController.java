package Controller.Tien;

import Dao.LichThiDAO;
import Model.LichThi;
import View.Tien.LichThiPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import TienIch.XuatExcel;
import Model.Auth;

public class LichThiController {
    
    private LichThiPanel view;
    private LichThiDAO dao;
    
    public LichThiController(LichThiPanel view) {
        this.view = view;
        this.dao = new LichThiDAO();
        
        // Gán sự kiện cho các nút bấm
        initEvents();
        
        // Mở form lên là load dữ liệu ngay
        loadAll();
    }

    private void initEvents() {
        boolean[] editMode = {false};

        // Search button
        view.addBtnTimKiemListener(e -> {
            String kw = view.getKeyword();
            
            // If keyword empty → load all data
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
            
            // Validate: MaMH and NgayThi are required
            if (lt.getMaMH().isEmpty() || lt.getNgayThi().isEmpty()) {
                view.showMessage("Vui lòng nhập Mã môn và Ngày thi!");
                return;
            }
            
            // Save or update based on edit mode
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
            
            // Check if row is selected (MaLT = 0 means not selected)
            if(lt.getMaLT() == 0) {
                 view.showMessage("Vui lòng chọn dòng cần xóa!"); 
                 return;
            }
            
            // Confirm deletion
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

        // Table click - select row and fill form
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.fillForm(row);
                }
            }
        });
        
        // Excel export button
        view.addBtnXuatExcelListener(e -> {
            XuatExcel.xuatFileExcel(view.getTable(), view);
        });
    }

    // Helper: Load toàn bộ danh sách từ DB lên View
    private void loadAll() {
        view.setTableData(dao.getAllLichThi());
    }
}