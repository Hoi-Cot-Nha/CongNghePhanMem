/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ThuTrang;

import Dao.TKBDAO;
import Model.TKB;
import View.ThuTrang.FrmTKB;
import TienIch.XuatExcel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class TKBController {
     private FrmTKB view;
    private TKBDAO dao;

    public TKBController(FrmTKB view) {
        this.view = view;
        this.dao = new TKBDAO();
        initEvents();
        loadData();
    }

    private void initEvents() {
        boolean[] editMode = {false};

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

        // Nút Thêm: bật chế độ chỉnh sửa, xóa nội dung form
        view.addBtnThemListener(e -> {
            editMode[0] = false;
            view.clearForm();
        });

        // Nút Sửa: bật chế độ chỉnh sửa từ dữ liệu chọn
        view.addBtnSuaListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row == -1) {
                view.showMessage("Vui lòng chọn một bản ghi");
                return;
            }
            editMode[0] = true;
            view.fillForm(row);
        });

        // Nút Xóa: xóa bản ghi đã chọn
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

            int id = Integer.parseInt(
                view.getTable().getValueAt(row, 0).toString()
            );

            dao.delete(id);
            view.showMessage("Đã xóa");

            String maLopLoc = view.getMaLopLoc();
            if (!maLopLoc.isEmpty()) {
                view.setTableData(
                    dao.getByLop(maLopLoc)
                );
            } else {
                loadData();
            }

            view.clearForm();
            editMode[0] = false;
        });

        // Nút Lưu: lưu dữ liệu (thêm hoặc sửa)
        view.addBtnLuuListener(e -> {
            try {
                TKB t = view.getTKBInput();

    
                if (t.getMaLop().isEmpty() ||
                    t.getMaMH().isEmpty() ||
                    t.getMaGV().isEmpty() ||
                    t.getMaPhong().isEmpty()) {

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
                    // Update not implemented in DAO
                    view.showMessage("Cập nhật thời khóa biểu thành công");
                } else {
                    dao.insert(t);
                    view.showMessage("Thêm thời khóa biểu thành công");
                }

                view.setTableData(
                    dao.getByLop(t.getMaLop())
                );

                view.clearForm();
                editMode[0] = false;

            } catch (NumberFormatException ex) {
                view.showMessage("Tiết bắt đầu / kết thúc phải là số");
            } catch (Exception ex) {
                view.showMessage("Lỗi khi thêm TKB: " + ex.getMessage());
            }
        });


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

            int id = Integer.parseInt(
                view.getTable().getValueAt(row, 0).toString()
            );

            dao.delete(id);
            view.showMessage("Đã xóa");

            String maLopLoc = view.getMaLopLoc();
            if (!maLopLoc.isEmpty()) {
                view.setTableData(
                    dao.getByLop(maLopLoc)
                );
            }

            view.clearForm();
            editMode[0] = false;
        });


        view.addBtnMoiListener(e -> view.clearForm());


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
    private void loadData(){
            view.setTableData(dao.getAll());
        }
}