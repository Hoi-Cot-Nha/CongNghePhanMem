/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.HaTrang;
import Dao.ThongbaoDAO;
import Model.Thongbao;
import View.HaTrang.QuanlyThongbaoPanel;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author ADMIN
 */
public class Thongbaocontroller {
    private QuanlyThongbaoPanel view;
    private ThongbaoDAO dao;
    private List<Thongbao> currentList; 

    private void loadData() {
     
        currentList = dao.getAll(); 
        view.loadTable(currentList);
    }

    public Thongbaocontroller(QuanlyThongbaoPanel view) {
        this.view = view;
        this.dao = new ThongbaoDAO();
        loadData();


        view.getBtnLoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tuKhoa = view.getLocKeyword().trim();
                currentList = dao.search(tuKhoa);
                view.loadTable(currentList);
                if (currentList.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy thông báo nào!");
                }
            }
        });


        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    Thongbao tb = new Thongbao();
                    tb.setTieuDe(view.getTieuDe().trim());
                    tb.setNoiDung(view.getNoiDung().trim());
                    tb.setNguoiGui(view.getNguoiGui().trim());
                    
                    if (dao.insert(tb)) {
                        JOptionPane.showMessageDialog(view, "Thêm thành công!");
                        loadData(); 
                        view.refresh();
                    }
                }
            }
        });

   
        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    int maTB = currentList.get(row).getMaTB(); 
                    if (JOptionPane.showConfirmDialog(view, "Xóa thông báo này?") == JOptionPane.YES_OPTION) {
                        if (dao.delete(maTB)) {
                            loadData(); 
                            view.refresh();
                            JOptionPane.showMessageDialog(view, "Đã xóa!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
                }
            }
        });

        view.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    if (validateForm()) {
                        Thongbao tb = currentList.get(row);
                        tb.setTieuDe(view.getTieuDe().trim());
                        tb.setNoiDung(view.getNoiDung().trim());
                        tb.setNguoiGui(view.getNguoiGui().trim());
                        if (dao.update(tb)) {
                            loadData();
                            JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Chọn dòng để sửa!");
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

    private boolean validateForm() {
        if (view.getTieuDe().trim().isEmpty() || 
            view.getNguoiGui().trim().isEmpty() || 
            view.getNoiDung().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng không để trống thông tin!");
            return false;
        }
        return true;
    }
}
