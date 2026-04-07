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
import Model.Auth;

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
        // 1. Nút Xem/Lọc danh sách
        view.addBtnXemListener(e -> loadData());

        // 2. Nút Tìm kiếm
        view.addBtnTimKiemListener(e -> searchData());

        // 3. Nút Lưu (Xử lý cả Thêm mới và Cập nhật)
        view.addBtnLuuListener(e -> {
            // Lấy object Hạnh kiểm từ form nhập
            HanhKiem hk = view.getHanhKiemInput();
            
            // Validate: Phải chọn HS trên bảng trước mới biết đánh giá cho ai
            if(hk.getMaHS().isEmpty()) {
                view.showMessage("Vui lòng chọn học sinh trên bảng để đánh giá!");
                return;
            }
            
            // Gọi DAO lưu xuống DB
            if (dao.saveHanhKiem(hk)) {
                view.showMessage("Lưu hạnh kiểm thành công!");
                loadData(); // Load lại bảng để thấy kết quả
            } else {
                view.showMessage("Lưu thất bại! Có lỗi xảy ra.");
            }
        });
        
        // 4. Nút Xóa (Xóa xếp loại/nhận xét của HS trong kỳ đó)
        view.addBtnXoaListener(e -> {
            HanhKiem hk = view.getHanhKiemInput();
            
            // Check xem đã chọn dòng nào chưa
            if(hk.getMaHS().isEmpty()) {
                 view.showMessage("Vui lòng chọn dòng cần xóa!"); 
                 return;
            }
            
            // Gọi DAO xóa dựa trên Composite Key (MaHS + NamHoc + HocKy)
            if (dao.deleteHanhKiem(hk.getMaHS(), hk.getNamHoc(), hk.getHocKy())) {
                view.showMessage("Xóa thành công!");
                loadData();
                view.clearForm(); // Xóa xong thì reset form nhập cho sạch
            } else {
                view.showMessage("Xóa thất bại!");
            }
        });

        // 5. Sự kiện Click vào bảng -> Đổ dữ liệu ngược lên form nhập
        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                view.fillFormInput(row);
            }
        });
        
        // 6. Nút Làm mới (Reset trắng form nhập)
        view.addBtnMoiListener(e -> view.clearForm());
        
        // 7. Xuất Excel (Dùng class tiện ích chung)
        view.addBtnXuatExcelListener(e -> {
            XuatExcel.xuatFileExcel(view.getTable(), view);
        });
    }

    // Hàm lấy dữ liệu dựa theo bộ lọc (Lớp, Năm, Kỳ)
    private void loadData() {
        try {

            List<HanhKiem> list;

            if (Auth.isHocSinh()) {

                list = dao.getHanhKiemByMaHS(Auth.maNguoiDung);

                // Ẩn nút
                view.hideButtonForStudent();

            } else {

                String maLop = view.getMaLopFilter();
                String namHoc = view.getNamHocFilter();
                int hocKy = view.getHocKyFilter();

                if (maLop.isEmpty() || namHoc.isEmpty()) return;

                list = dao.getHanhKiemByFilter(maLop, namHoc, hocKy);
            }

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

        List<HanhKiem> list;

        if (Auth.isHocSinh()) {
            list = dao.searchHanhKiemByMaHS(Auth.maNguoiDung, keyword);
        } else {
            list = dao.searchHanhKiem(keyword);
        }

        if(list.isEmpty()) {
            view.showMessage("Không tìm thấy kết quả nào cho: " + keyword);
        }
    }
}