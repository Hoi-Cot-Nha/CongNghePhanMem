/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.HaTrang;

import Dao.HocphiDAO;
import Model.Hocphi;
import View.HaTrang.QuanLyHocPhiPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.List;

public class Hocphicontroller {
    private QuanLyHocPhiPanel view;
    private HocphiDAO dao;

    public Hocphicontroller(QuanLyHocPhiPanel view) {
        this.view = view;
        this.dao = new HocphiDAO();

        
        view.getBtnLoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                locDuLieu();
            }
        });

       
        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyLuu(true); 
            }
        });

      
        view.getBtnLuu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyLuu(false); 
            }
        });

        
        view.getBtnXoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaHocPhi();
            }
        });

      
        view.getBtnLamMoi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.refreshForm();
            }
        });
    }

    private void locDuLieu() {
        try {
            String maLop = view.getTxtLocMaLop().getText().trim();
            int hocKy = Integer.parseInt(view.getCboHocKy().getSelectedItem().toString());
            String namHoc = view.getTxtNamHoc().getText().trim();

            List<Hocphi> list = dao.getHocPhiByLop(maLop, hocKy, namHoc);
            view.loadTable(list);

            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy dữ liệu cho lớp " + maLop);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi lọc: " + ex.getMessage());
        }
    }

    private void xuLyLuu(boolean isThemMoi) {
        try {
           
            String maHS = view.getTxtMaHS().getText().trim();
            String tongTienStr = view.getTxtTongTien().getText().trim();
            String mienGiamStr = view.getTxtMienGiam().getText().trim();

            
            if (maHS.isEmpty() || tongTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã học sinh và Tổng tiền không được để trống!");
                return;
            }

            Hocphi hp = new Hocphi();
            hp.setMaHS(maHS);
            hp.setHocKy(Integer.parseInt(view.getCboHocKy().getSelectedItem().toString()));
            hp.setNamHoc(view.getTxtNamHoc().getText().trim());
            
            long tongTien = Long.parseLong(tongTienStr);
            long mienGiam = mienGiamStr.isEmpty() ? 0 : Long.parseLong(mienGiamStr);
            long phaiDong = tongTien - mienGiam;

            hp.setTongTien(tongTien);
            hp.setMienGiam(mienGiam);
            hp.setPhaiDong(phaiDong);
            hp.setTrangThai(phaiDong <= 0 ? "Đã đóng" : "Chưa đóng");

            
            if (dao.saveHocPhi(hp)) {
                String thongBao = isThemMoi ? "Thêm mới học phí thành công!" : "Cập nhật học phí thành công!";
                JOptionPane.showMessageDialog(view, thongBao);
                locDuLieu();
                view.refreshForm(); 
            } else {
                JOptionPane.showMessageDialog(view, "Lưu thất bại! Kiểm tra lại mã HS hoặc kết nối DB.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Tiền phải nhập định dạng số!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void xoaHocPhi() {
        int selectedRow = view.getTableHocPhi().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn một dòng trên bảng để xóa!");
            return;
        }

        int xacNhan = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa học phí này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
        
            int maHP = (int) view.getTableHocPhi().getValueAt(selectedRow, 0);
            if (dao.deleteHocPhi(maHP)) {
                JOptionPane.showMessageDialog(view, "Xóa thành công!");
                locDuLieu();
                view.refreshForm();
            } else {
                JOptionPane.showMessageDialog(view, "Xóa thất bại!");
            }
        }
    }
}