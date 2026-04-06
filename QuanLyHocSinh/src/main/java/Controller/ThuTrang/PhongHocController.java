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

      
        view.addBtnXemListener(e -> loadAllAndUpdateStatus());

        
        view.addBtnTimListener(e -> {
            List<PhongHoc> list = dao.search(
                    view.getMaPhongTim(),
                    view.getLoaiPhongTim(),
                    view.getTinhTrangTim()
            );

            // ✅ Không cần cập nhật tình trạng nữa, đã có trong query
            view.setTableData(list);
        });

    
        view.addBtnLuuListener(e -> {
            try {
                PhongHoc p = view.getPhongHocInput();

                if (p.getMaPhong().isEmpty()) {
                    view.showMessage("Mã phòng không được để trống");
                    return;
                }

                if (dao.exists(p.getMaPhong())) {
                    dao.update(p);
                    view.showMessage("✔ Cập nhật phòng học thành công");
                } else {
                    dao.insert(p);
                    view.showMessage("✔ Thêm phòng học thành công");
                }

                loadAllAndUpdateStatus();
                view.clearForm();

            } catch (NumberFormatException ex) {
                view.showMessage("Sức chứa phải là số");
            }
        });

      
        view.addBtnXoaListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row == -1) {
                view.showMessage("Vui lòng chọn phòng cần xóa");
                return;
            }

            String maPhong = view.getTable().getValueAt(row, 0).toString();
            dao.delete(maPhong);
            view.showMessage("Đã xoá");
            loadAllAndUpdateStatus();
            view.clearForm();
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
    }

    private void loadAllAndUpdateStatus() {
        List<PhongHoc> list = dao.getAll();
        view.setTableData(list);
    }
}