package Controller.Tien;

import Dao.LichThiDAO;
import Model.LichThi;
import View.Tien.LichThiPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import TienIch.XuatExcel;

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

        // 1. Nút Tìm kiếm
        view.addBtnTimKiemListener(e -> {
            String kw = view.getKeyword();
            
            // Nếu keyword trống → load toàn bộ dữ liệu
            if(kw.isEmpty()) { 
                loadAll();
                return; 
            }
            
            List<LichThi> list = dao.searchLichThi(kw);
            view.setTableData(list);
            
            if(list.isEmpty()) view.showMessage("Không tìm thấy kết quả nào!");
        });

        // 2. Nút Xem Tất Cả (Reset bộ lọc)
        view.addBtnXemTatCaListener(e -> loadAll());

        // 3. Nút Thêm Mới
        view.addBtnThemListener(e -> {
            LichThi lt = view.getLichThiInput();
            
            // Validate sơ bộ: Mã môn và ngày thi là bắt buộc
            if (lt.getMaMH().isEmpty() || lt.getNgayThi().isEmpty()) {
                view.showMessage("Vui lòng nhập Mã môn và Ngày thi!");
                return;
            }
            
            // Gọi DAO thêm vào DB
            if(dao.addLichThi(lt)) {
                view.showMessage("Thêm lịch thi thành công!");
                loadAll();      // Load lại bảng
                view.clearForm(); // Xóa trắng form nhập
            } else {
                view.showMessage("Thêm thất bại! (Kiểm tra xem Mã Môn/Mã Phòng có tồn tại chưa)");
            }
        });

        // 4. Nút Cập Nhật (Sửa)
        view.addBtnSuaListener(e -> {
            LichThi lt = view.getLichThiInput();
            // Gọi DAO update dựa trên khóa chính MaLT (đã ẩn trong object)
            if(dao.updateLichThi(lt)) {
                view.showMessage("Cập nhật thành công!");
                loadAll();
            } else {
                view.showMessage("Cập nhật thất bại!");
            }
        });

        // 5. Nút Xóa
        view.addBtnXoaListener(e -> {
            LichThi lt = view.getLichThiInput();
            
            // Check xem đã chọn dòng nào chưa (MaLT = 0 nghĩa là chưa chọn)
            if(lt.getMaLT() == 0) {
                 view.showMessage("Vui lòng chọn dòng cần xóa!"); 
                 return;
            }
            
            // Hỏi chắc chắn trước khi xóa
            int cf = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa lịch thi này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if(cf == JOptionPane.YES_OPTION) {
                if(dao.deleteLichThi(lt.getMaLT())) {
                    view.showMessage("Xóa thành công!");
                    loadAll();
                    view.clearForm();
                } else view.showMessage("Xóa thất bại!");
            }
        });
        
        // 6. Nút Làm Mới (Reset form nhập)
        view.addBtnMoiListener(e -> view.clearForm());

        // 7. Sự kiện Click bảng -> Đổ dữ liệu lên form nhập
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                view.fillForm(row);
            }
        });
        
        // 8. Xuất Excel (Dùng tiện ích chung)
        view.addBtnXuatExcelListener(e -> {
            XuatExcel.xuatFileExcel(view.getTable(), view);
        });
    }

    // Helper: Load toàn bộ danh sách từ DB lên View
    private void loadAll() {
        view.setTableData(dao.getAllLichThi());
    }
}