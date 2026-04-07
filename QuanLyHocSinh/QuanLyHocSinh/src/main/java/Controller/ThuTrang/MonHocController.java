/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ThuTrang;

import Dao.MonHocDAO;
import Model.MonHoc;
import View.ThuTrang.FrmMonHoc;
import java.awt.event.*;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MonHocController {
  private FrmMonHoc view;
    private MonHocDAO dao;

    public MonHocController(FrmMonHoc view) {
        this.view = view;
        this.dao = new MonHocDAO();
        initEvents();
        loadData();
    }

    private void initEvents() {

        
        view.addBtnXemListener(e -> loadData());

       
        view.addBtnTimKiemListener(e -> {
            String key = view.getTuKhoa();
            if (key.isEmpty()) {
                view.showMessage("Vui lòng nhập mã hoặc tên môn");
                return;
            }

            List<MonHoc> list = dao.search(key);
            view.setTableData(list);

            if (list.isEmpty()) {
                view.showMessage("Không tìm thấy môn học");
            }
        });

       
        view.addBtnLuuListener(e -> {
            MonHoc m = view.getMonHocInput();
            if (m.getMaMH().isEmpty()) {
                view.showMessage("Mã môn không được rỗng");
                return;
            }

            if (dao.exists(m.getMaMH())) {
                dao.update(m);
            } else {
                dao.insert(m);
            }

            view.showMessage("Lưu thành công");
            loadData();
            view.clearForm();
        });

    
        view.addBtnXoaListener(e -> {
            int row = view.getTable().getSelectedRow();
            if (row == -1) return;

            String ma = view.getTable().getValueAt(row, 0).toString();
            dao.delete(ma);
            view.showMessage("Đã xoá");
            loadData();
            view.clearForm();
        });


        view.addBtnMoiListener(e -> view.clearForm());


        view.addTableMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.fillForm(view.getTable().getSelectedRow());
            }
        });
    }

    private void loadData() {
        view.setTableData(dao.getAll());
    }
}