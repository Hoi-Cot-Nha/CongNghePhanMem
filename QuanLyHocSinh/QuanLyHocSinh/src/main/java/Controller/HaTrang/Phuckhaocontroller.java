/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.HaTrang;
import Dao.PhuckhaoDAO;
import Model.Phuckhao;
import View.HaTrang.QuanLyPhucKhaoPanel;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Phuckhaocontroller {
    private QuanLyPhucKhaoPanel view;
    private PhuckhaoDAO dao;
    private List<Phuckhao> listCurrent;

    public Phuckhaocontroller(QuanLyPhucKhaoPanel view) {
        this.view = view;
        this.dao = new PhuckhaoDAO();
        loadData();


        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) view.fillForm(row);
            }
        });


        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    Phuckhao pk = getForm();
         
                    System.out.println("Đang thêm: " + pk.getMaHS()); 
                    if (dao.insert(pk)) {
                        JOptionPane.showMessageDialog(view, "Gửi yêu cầu thành công!");
                        loadData(); 
                        view.refresh();
                    } else {
                        JOptionPane.showMessageDialog(view, "Thêm thất bại! Kiểm tra lại Mã HS/MH có tồn tại không.");
                    }
                }
            }
        });

   
        view.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1 && validateForm()) {
                    Phuckhao pk = getForm();
                    pk.setMaPK(listCurrent.get(row).getMaPK());
                    if (dao.update(pk)) {
                        JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                        loadData();
                    }
                } else { JOptionPane.showMessageDialog(view, "Chọn dòng cần sửa!"); }
            }
        });


        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    int id = listCurrent.get(row).getMaPK();
                    if (JOptionPane.showConfirmDialog(view, "Xóa bản ghi này?") == 0) {
                        if (dao.delete(id)) { loadData(); view.refresh(); }
                    }
                }
            }
        });


        view.getBtnLoc().addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                String tuKhoa = view.getLocKeyword().trim();

                listCurrent = dao.search(tuKhoa); 
                view.loadTable(listCurrent);

                if (listCurrent.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy kết quả nào!");
                }
            }
        });
        view.getBtnLamMoi().addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) { 
                view.refresh(); 
                loadData();   
            }
        });
    }

    private void loadData() { 
        listCurrent = dao.getAll(); 
        view.loadTable(listCurrent); 
    }

    private boolean validateForm() {
        if (view.getMaHS().trim().isEmpty() || view.getMaMH().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Mã HS và Mã MH không được để trống!");
            return false;
        }
        return true;
    }

    private Phuckhao getForm() {
        Phuckhao pk = new Phuckhao();
        pk.setMaHS(view.getMaHS().trim());
        pk.setMaMH(view.getMaMH().trim());
        pk.setTrangThai(view.getTrangThai().trim());
        pk.setLyDo(view.getLyDo().trim());
        return pk;
    }
}
