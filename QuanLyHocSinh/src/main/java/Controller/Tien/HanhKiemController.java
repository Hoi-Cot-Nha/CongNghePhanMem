package Controller.Tien;

import Dao.HanhKiemDAO;
import Dao.LopDAO;
import Model.HanhKiem;
import Model.LopGVCN;
import TienIch.XuatExcel;
import View.Tien.HanhKiemPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class HanhKiemController {
    
    private HanhKiemPanel view;
    private HanhKiemDAO dao;

    public HanhKiemController(HanhKiemPanel view) {
        this.view = view;
        this.dao = new HanhKiemDAO();
        
        loadComboBoxData();
        
        // Gán action cho các nút bấm ngay khi khởi tạo
        initEvents();
        
        // Load dữ liệu mặc định lên bảng ngay khi vào form
        loadData(); 
    }

    private void loadComboBoxData() {
        LopDAO lopDAO = new LopDAO();
        List<LopGVCN> lops = lopDAO.getAllLop();
        List<String> maLops = new ArrayList<>();
        for (LopGVCN l : lops) {
            maLops.add(l.getMaLop());
        }
        view.setMaLopData(maLops);

        List<String> namHocs = dao.getDistinctNamHoc();
        if (namHocs.isEmpty()) {
            namHocs.add("2024-2025");
        }
        view.setNamHocData(namHocs);
    }

    private void initEvents() {
        boolean[] editMode = {false};

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
            
            // Validate: Must select a student first
            if(hk.getMaHS().isEmpty()) {
                view.showMessage("Vui lòng chọn học sinh trên bảng để đánh giá!");
                return;
            }
            
            // Save to database
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
            
            // Check if row is selected
            if(hk.getMaHS().isEmpty()) {
                 view.showMessage("Vui lòng chọn dòng cần xóa!"); 
                 return;
            }
            
            // Confirm deletion
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
        
        // Excel export button
        view.addBtnXuatExcelListener(e -> {
            XuatExcel.xuatFileExcel(view.getTable(), view);
        });
    }

    // Hàm lấy dữ liệu dựa theo bộ lọc (Lớp, Năm, Kỳ)
    private void loadData() {
        try {
            String maLop = view.getMaLopFilter();
            String namHoc = view.getNamHocFilter();
            int hocKy = view.getHocKyFilter();

            // Nếu filter rỗng → gọi DAO với empty string, DAO sẽ xử lý để lấy tất cả
            // Không return, cứ load dữ liệu
            List<HanhKiem> list = dao.getHanhKiemByFilter(maLop, namHoc, hocKy);
            view.setTableData(list);
            
        } catch (Exception ex) {
            view.showMessage("Lỗi tải dữ liệu: " + ex.getMessage());
        }
    }

    // Hàm tìm kiếm nhanh theo từ khóa
    private void searchData() {
        String keyword = view.getTuKhoaTimKiem();
        
        if(keyword.isEmpty()) {
            view.showMessage("Vui lòng nhập từ khóa (Tên hoặc Mã HS)!");
            return;
        }

        List<HanhKiem> list = dao.searchHanhKiem(keyword);
        view.setTableData(list);

        if(list.isEmpty()) {
            view.showMessage("Không tìm thấy kết quả nào cho: " + keyword);
        }
    }
}