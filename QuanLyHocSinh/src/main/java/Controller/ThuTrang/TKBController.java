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

                dao.insert(t);
                view.showMessage("Thêm thời khóa biểu thành công");

                view.setTableData(
                    dao.getByLop(t.getMaLop())
                );

                view.clearForm();

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