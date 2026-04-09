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

    public Thongbaocontroller(QuanlyThongbaoPanel view) {
        this.view = view;
        this.dao = new ThongbaoDAO();
        initEvents();
        loadData();
    }

    private void initEvents() {
        boolean[] editMode = {false};

        // Filter/Search button
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


        // Add button
        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editMode[0] = false;
                view.refresh();
            }
        });

        // Table click - select row and fill form
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    editMode[0] = true;
                    view.fillForm(row);
                }
            }
        });

        // Save button (handles both add and edit)
        view.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editMode[0]) {
                    // Edit mode: update existing
                    int row = view.getTable().getSelectedRow();
                    if (row != -1 && validateForm()) {
                        Thongbao tb = currentList.get(row);
                        tb.setTieuDe(view.getTieuDe().trim());
                        tb.setNoiDung(view.getNoiDung().trim());
                        tb.setNguoiGui(view.getNguoiGui().trim());
                        if (dao.update(tb)) {
                            loadData();
                            JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
                            editMode[0] = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Chọn dòng để sửa!");
                    }
                } else {
                    // Add mode: insert new
                    if (validateForm()) {
                        Thongbao tb = new Thongbao();
                        tb.setTieuDe(view.getTieuDe().trim());
                        tb.setNoiDung(view.getNoiDung().trim());
                        tb.setNguoiGui(view.getNguoiGui().trim());
                        
                        if (dao.insert(tb)) {
                            JOptionPane.showMessageDialog(view, "Thêm thành công!");
                            loadData(); 
                            view.refresh();
                            editMode[0] = false;
                        }
                    }
                }
            }
        });

        // Edit button - enable edit mode
        view.getBtnSua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    editMode[0] = true;
                    view.fillForm(row);
                } else {
                    JOptionPane.showMessageDialog(view, "Chọn dòng cần sửa!");
                }
            }
        });

        // Delete button with confirmation
        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row != -1) {
                    int maTB = currentList.get(row).getMaTB(); 
                    int confirm = JOptionPane.showConfirmDialog(
                        view, "Bạn có chắc chắn muốn xóa?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (dao.delete(maTB)) {
                            loadData(); 
                            view.refresh();
                            JOptionPane.showMessageDialog(view, "Đã xóa!");
                            editMode[0] = false;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng cần xóa!");
                }
            }
        });

        // Reset/Cancel button
        view.getBtnHuy().addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) { 
                view.refresh(); 
                loadData();
                editMode[0] = false;
            }
        });
    }

    private void loadData() {
        currentList = dao.getAll(); 
        view.loadTable(currentList);
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
