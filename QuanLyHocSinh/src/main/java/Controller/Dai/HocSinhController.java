package Controller.Dai;

import Dao.HocSinhDAO;
import Model.HocSinh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HocSinhController {

    private HocSinhDAO dao = new HocSinhDAO();

    
    public void loadTable(DefaultTableModel model) {
        model.setRowCount(0);
        List<HocSinh> list = dao.getAll();

        for (HocSinh hs : list) {
            model.addRow(new Object[]{
                hs.getMaHS(),
                hs.getHoTen(),
                hs.getNgaySinh(),
                hs.getGioiTinh(),
                hs.getDiaChi(),
                hs.getMaLop(),
                (hs.getMaDT() == null || hs.getMaDT().isEmpty()) ? "Không" : hs.getMaDT()
            });
        }
    }

    public boolean them(HocSinh hs) {
        return dao.insert(hs);
    }

 
    public boolean sua(HocSinh hs) {
        return dao.update(hs);
    }


    public boolean xoa(String maHS) {
        return dao.delete(maHS);
    }
    
    public void loadComboMaLop(JComboBox<String> cbo) {
        cbo.removeAllItems();
        cbo.addItem(""); // Mục rỗng mặc định
        for (String ma : dao.getAllMaLop()) {
            cbo.addItem(ma);
        }
        cbo.setSelectedIndex(0); // Mặc định chọn mục rỗng
    }

    public void loadComboMaDT(JComboBox<String> cbo) {
        cbo.removeAllItems();
        cbo.addItem(""); // Mục rỗng mặc định
        for (String ma : dao.getAllMaDT()) {
            cbo.addItem(ma);
        }
        cbo.setSelectedIndex(0); // Mặc định chọn mục rỗng
    }


 
    public void timKiem(String keyword, DefaultTableModel model) {
        model.setRowCount(0);
        List<HocSinh> list = dao.search(keyword);

        for (HocSinh hs : list) {
            model.addRow(new Object[]{
                hs.getMaHS(),
                hs.getHoTen(),
                hs.getNgaySinh(),
                hs.getGioiTinh(),
                hs.getDiaChi(),
                hs.getMaLop(),
                (hs.getMaDT() == null || hs.getMaDT().isEmpty()) ? "Không" : hs.getMaDT()
            });
        }
    }
}