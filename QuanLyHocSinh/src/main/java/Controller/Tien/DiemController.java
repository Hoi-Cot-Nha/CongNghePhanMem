package Controller.Tien; 

import Dao.DiemDAO;
import Dao.LopDAO;
import Dao.MonHocDAO;
import Model.Auth;
import Model.Diem;
import Model.LopGVCN;
import Model.MonHoc;
import View.Tien.QuanLyDiemPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import TienIch.XuatExcel;
import Model.Auth;

public class DiemController { 
    
    private QuanLyDiemPanel view;
    private DiemDAO dao;

    public DiemController(QuanLyDiemPanel view) {
        this.view = view;
        this.dao = new DiemDAO();
        
        loadComboBoxData();
        
        // Gán hành động cho các nút bấm ngay khi khởi tạo
        initEvents();
        
        // Load dữ liệu mặc định lên bảng luôn cho đỡ trống
        loadData(); 
    }

    private void loadComboBoxData() {
        LopDAO lopDAO = new LopDAO();
        MonHocDAO monHocDAO = new MonHocDAO();

        List<LopGVCN> lops = lopDAO.getAllLop();
        List<String> maLops = new ArrayList<>();
        for (LopGVCN l : lops) {
            maLops.add(l.getMaLop());
        }
        view.setMaLopData(maLops);

        List<MonHoc> mons = monHocDAO.getAll();
        view.setMonHocData(mons);

        List<Integer> hks = dao.getDistinctHocKy();
        if (hks.isEmpty()) {
            hks.add(1);
            hks.add(2);
        }
        view.setHocKyData(hks);
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

        // Update score button (Cập Nhật / Lưu)
        view.addBtnLuuListener(e -> {
            // Get score input from form
            Diem d = view.getDiemInput();
            
            // Validate: Input must be valid number
            if (d == null) {
                view.showMessage("Điểm số phải là số thực (Ví dụ: 8.5)!"); 
                return;
            }
            
            // Validate: Must select a student first
            if (d.getMaHS().isEmpty()) {
                view.showMessage("Vui lòng click chọn học sinh trên bảng trước!"); 
                return;
            }

            // Save to database
            if (dao.updateDiem(d)) {
                view.showMessage("Đã cập nhật điểm thành công!");
                loadData();
                editMode[0] = false;
            } else {
                view.showMessage("Cập nhật thất bại! Hãy kiểm tra kết nối CSDL.");
            }
        });
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                view.fillFormInput(row); // Gọi hàm bên View để điền text
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

    // Hàm lấy danh sách điểm dựa theo bộ lọc (Lớp, Môn, Kỳ)
//    private void loadData() {
//        String maLop = view.getMaLopFilter();
//
//        // Nếu chưa nhập lớp thì thôi không load (tránh lỗi query)
//        if (maLop.isEmpty()) {
//             // Có thể báo lỗi hoặc im lặng tùy bạn
//             return;
//        }
//
//        String maMon = view.getMaMonFilter();
//        int hocKy = view.getHocKyFilter();
//
//        // Nếu tất cả filter rỗng → load toàn bộ dữ liệu
//        if (maLop.isEmpty() && maMon.isEmpty()) {
//            List<Diem> list = dao.getAllDiem();
//            view.setTableData(list);
//        } else {
//            // Nếu có filter → gọi getDiemByFilter
//            List<Diem> list = dao.getDiemByFilter(maLop, maMon, hocKy);
//            view.setTableData(list);
//        }
//    }

    // Hàm tìm kiếm theo tên hoặc mã
    private void searchData() {
        String keyword = view.getTuKhoaTimKiem();
        

        if (keyword.isEmpty()) {
            view.showMessage("Nhập tên hoặc mã HS để tìm kiếm nhé!");
            return;
        }

        List<Diem> list = dao.searchDiem(keyword);
        view.setTableData(list);

        if (list.isEmpty()) {
            view.showMessage("Không tìm thấy học sinh nào với từ khóa: " + keyword);
        }
    }
    // Thêm cho phân quyền tài khoản
    // Import thêm Auth vào đầu file
    // import com.qlhs.main.Auth;

    private void loadData() {

        List<Diem> list;

        // ✅ Nếu là học sinh
        if (Auth.isHocSinh()) {

            // DEBUG xem có đúng mã không
            System.out.println("LOGIN MA HS = [" + Auth.maNguoiDung + "]");

            // ✅ ÉP CHUẨN CHỮ HOA (QUAN TRỌNG)
            list = dao.getDiemByMaHS(Auth.maNguoiDung.toUpperCase());

            // Ẩn nút cập nhật
            view.getBtnCapNhat().setVisible(false);

        } else {

            String maLop = view.getMaLopFilter();
            if (maLop.isEmpty()) return;

            String maMon = view.getMaMonFilter();
            int hocKy = view.getHocKyFilter();

            list = dao.getDiemByFilter(maLop, maMon, hocKy);
        }

        view.setTableData(list);
    }
}