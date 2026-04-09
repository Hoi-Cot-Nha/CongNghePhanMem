/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ThuTrang;

import Dao.PhongHocDAO;
import Model.PhongHoc;
import View.ThuTrang.FrmPhongHoc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhongHocController {
   private FrmPhongHoc view;
    private PhongHocDAO dao;

    public PhongHocController(FrmPhongHoc view) {
        this.view = view;
        this.dao = new PhongHocDAO();
        initEvents();
        loadAllAndUpdateStatus();
    }

   
    private void initEvents() {
        boolean[] editMode = {false};

        view.addBtnXemListener(e -> loadAllAndUpdateStatus());

        view.addBtnTimListener(e -> {
            List<PhongHoc> list = dao.search(
                    view.getMaPhongTim(),
                    view.getLoaiPhongTim(),
                    view.getTinhTrangTim()
            );

            view.setTableData(list);
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

        // Nút Lưu: lưu dữ liệu (thêm hoặc sửa)
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

        // Nút Hủy: hủy chỉnh sửa, xóa form
        view.addBtnHuyListener(e -> {
            view.clearForm();
            editMode[0] = false;
        });

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

    private void loadAllAndUpdateStatus() {
        List<PhongHoc> list = dao.getAll();
        view.setTableData(list);
    }
}