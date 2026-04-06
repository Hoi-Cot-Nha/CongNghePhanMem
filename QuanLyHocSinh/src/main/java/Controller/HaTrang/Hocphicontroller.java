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

        System.out.println("DEBUG Controller: Khởi tạo controller...");
        
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
        
        System.out.println("DEBUG Controller: Controller khởi tạo xong!");

        loadTatCaDuLieu();
    }

    private void loadTatCaDuLieu() {
        try {
            List<Hocphi> listAll = dao.getAllHocPhi();
            view.loadTable(listAll);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void locDuLieu() {
        try {
            Object cboMaLopSelected = view.getCboMaLop().getSelectedItem();
            Object cboHocKySelected = view.getCboHocKy().getSelectedItem();
            Object cboNamHocSelected = view.getCboNamHoc().getSelectedItem();

            // Nếu có selection (không null) thì lấy giá trị, nếu trống thì pass empty string
            String maLop = (cboMaLopSelected != null) ? cboMaLopSelected.toString().trim() : "";
            String hocKyStr = (cboHocKySelected != null) ? cboHocKySelected.toString().trim() : "";
            String namHoc = (cboNamHocSelected != null) ? cboNamHocSelected.toString().trim() : "";

            // Nếu tất cả đều trống → load toàn bộ dữ liệu
            // Nếu có ít nhất một giá trị → filter theo giá trị đó
            int hocKy = hocKyStr.isEmpty() ? 0 : Integer.parseInt(hocKyStr);
            
            System.out.println("DEBUG: Lọc dữ liệu - Lớp: " + (maLop.isEmpty() ? "Tất cả" : maLop) + 
                             ", Kỳ: " + (hocKy == 0 ? "Tất cả" : hocKy) + 
                             ", Năm: " + (namHoc.isEmpty() ? "Tất cả" : namHoc));

            List<Hocphi> list = dao.getHocPhiByLop(maLop, hocKy, namHoc);
            
            System.out.println("DEBUG: Số dòng tìm được: " + list.size());

            view.loadTable(list);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi định dạng: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi khi lọc: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void xuLyLuu(boolean isThemMoi) {
        try {
           
            String maHS = view.getTxtMaHS().getText().trim();
            String tongTienStr = view.getTxtTongTien().getText().trim();
            String mienGiamStr = view.getTxtMienGiam().getText().trim();
            String hocKyStr = view.getCboHocKy().getSelectedItem().toString().trim();
            String trangThaiStr = view.getCboTrangThai().getSelectedItem().toString().trim();

            
            if (maHS.isEmpty() || tongTienStr.isEmpty() || hocKyStr.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Mã học sinh, Tổng tiền và Học kỳ không được để trống!");
                return;
            }

            Hocphi hp = new Hocphi();
            hp.setMaHS(maHS);
            hp.setHocKy(Integer.parseInt(hocKyStr));
            hp.setNamHoc(view.getCboNamHoc().getSelectedItem().toString().trim());
            
            long tongTien = Long.parseLong(tongTienStr);
            long mienGiam = mienGiamStr.isEmpty() ? 0 : Long.parseLong(mienGiamStr);
            long phaiDong = tongTien - mienGiam;

            hp.setTongTien(tongTien);
            hp.setMienGiam(mienGiam);
            hp.setPhaiDong(phaiDong);
            hp.setTrangThai(trangThaiStr.isEmpty() ? "Chưa đóng" : trangThaiStr);

            
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