/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.HaTrang;

import Controller.HaTrang.Hocphicontroller;
import Model.Hocphi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.TitledBorder;
import TienIch.ButtonStyleHelper;
import Dao.LopDAO;

public class QuanLyHocPhiPanel extends JPanel {
    private JComboBox<String> cboMaLop, cboNamHoc;
    private JComboBox<String> cboHocKy;
    private JButton btnLoc, btnThem, btnLuu, btnXoa, btnLamMoi;
    private JTable tableHocPhi;
    private DefaultTableModel tableModel;
    private JTextField txtMaHS, txtTongTien, txtMienGiam, txtPhaiDong, txtTrangThai;

    public QuanLyHocPhiPanel() {
        initComponents();
        
        // Khởi tạo Controller (Controller sẽ gán sự kiện cho các nút)
        // Controller sẽ tự động tải dữ liệu sau khi khởi tạo xong
        Hocphicontroller controller = new Hocphicontroller(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- PANEL NORTH (TITLE & FILTER) ---
        JPanel pnlNorth = new JPanel(new BorderLayout(10, 10));
        pnlNorth.setOpaque(false);

        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ HỌC PHÍ", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(new Color(41, 128, 185));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);

        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

        pnlFilter.add(new JLabel("Mã Lớp:"));
        cboMaLop = new JComboBox<>();
        try {
            loadLopComboBox();
        } catch (Exception e) {
            System.out.println("⚠️ Lỗi tải lớp: " + e.getMessage());
            e.printStackTrace();
        }
        pnlFilter.add(cboMaLop);

        pnlFilter.add(new JLabel("Học Kỳ:"));
        cboHocKy = new JComboBox<>(new String[]{"1", "2"});
        cboHocKy.setSelectedIndex(0);
        pnlFilter.add(cboHocKy);

        pnlFilter.add(new JLabel("Năm Học:"));
        cboNamHoc = new JComboBox<>();
        try {
            loadNamHocComboBox();
        } catch (Exception e) {
            System.out.println("⚠️ Lỗi tải năm học: " + e.getMessage());
            e.printStackTrace();
        }
        pnlFilter.add(cboNamHoc);

        btnLoc = new JButton("Xem Danh Sách");
        ButtonStyleHelper.styleButtonView(btnLoc);
        btnLoc.setBackground(new Color(52, 152, 219));
        // btnLoc.setForeground(Color.WHITE); // Bạn có thể thêm màu chữ nếu muốn
        btnLoc.setFocusPainted(false);
        pnlFilter.add(btnLoc);

        pnlNorth.add(pnlFilter, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);

        // --- TABLE ---
        String[] cols = {"ID", "Mã HS", "Kỳ", "Năm học", "Tổng tiền", "Miễn giảm", "Phải đóng", "Trạng thái"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableHocPhi = new JTable(tableModel);
        tableHocPhi.setRowHeight(30);
        tableHocPhi.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        javax.swing.table.DefaultTableCellRenderer headerRenderer = (javax.swing.table.DefaultTableCellRenderer) tableHocPhi.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(new Color(100, 150, 200));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setOpaque(true);
        tableHocPhi.getTableHeader().setDefaultRenderer(headerRenderer);
        
        // Căn giữa dữ liệu bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<tableHocPhi.getColumnCount(); i++) {
            tableHocPhi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tableHocPhi);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách học phí"));
        add(scrollPane, BorderLayout.CENTER);

        // --- PANEL SOUTH (INPUT & BUTTONS) ---
        JPanel pnlSouth = new JPanel(new BorderLayout(10, 10));
        pnlSouth.setOpaque(false);

        JPanel pnlInput = new JPanel(new GridLayout(3, 4, 15, 10));
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(BorderFactory.createCompoundBorder(
            new TitledBorder("Thông tin chi tiết"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        pnlInput.add(new JLabel("Mã Học Sinh:"));
        txtMaHS = new JTextField();
        pnlInput.add(txtMaHS);

        pnlInput.add(new JLabel("Tổng Tiền:"));
        txtTongTien = new JTextField();
        pnlInput.add(txtTongTien);

        pnlInput.add(new JLabel("Miễn Giảm:"));
        txtMienGiam = new JTextField();
        pnlInput.add(txtMienGiam);

        pnlInput.add(new JLabel("Phải Đóng:"));
        txtPhaiDong = new JTextField();
        txtPhaiDong.setEditable(false);
        txtPhaiDong.setBackground(new Color(245, 245, 245));
        pnlInput.add(txtPhaiDong);

        pnlInput.add(new JLabel("Trạng Thái:"));
        txtTrangThai = new JTextField();
        txtTrangThai.setEditable(false);
        pnlInput.add(txtTrangThai);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);

        // Buttons
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlButtons.setOpaque(false);

        btnThem = createStyledButton("Thêm Mới", new Color(224, 255, 255));
        ButtonStyleHelper.styleButtonAdd(btnThem);
        btnLuu = createStyledButton("Cập Nhật", new Color(255, 250, 205));
        ButtonStyleHelper.styleButtonEdit(btnLuu);
        btnXoa = createStyledButton("Xóa Bỏ", new Color(255, 228, 225));
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnLamMoi = createStyledButton("Làm Mới", new Color(220, 220, 220));
        ButtonStyleHelper.styleButtonView(btnLamMoi);

        pnlButtons.add(btnThem);
        pnlButtons.add(btnLuu);
        pnlButtons.add(btnXoa);
        pnlButtons.add(btnLamMoi);

        pnlSouth.add(pnlButtons, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);

        // Event click bảng
        tableHocPhi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int r = tableHocPhi.getSelectedRow();
                if (r >= 0) {
                    txtMaHS.setText(tableModel.getValueAt(r, 1).toString());
                    txtMaHS.setEditable(false);
                    txtTongTien.setText(tableModel.getValueAt(r, 4).toString());
                    txtMienGiam.setText(tableModel.getValueAt(r, 5).toString());
                    txtPhaiDong.setText(tableModel.getValueAt(r, 6).toString());
                    txtTrangThai.setText(tableModel.getValueAt(r, 7).toString());
                }
            }
        });
    }

    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setPreferredSize(new Dimension(120, 35));
        return btn;
    }

    private void loadLopComboBox() {
        System.out.println("\n=== Bắt đầu loadLopComboBox ===");
        try {
            LopDAO lopDAO = new LopDAO();
            System.out.println("✓ Tạo LopDAO thành công");
            
            var lopList = lopDAO.getAllLop();
            System.out.println("✓ Gọi getAllLop() thành công, kết quả: " + (lopList == null ? "null" : lopList.size() + " lớp"));
            
            cboMaLop.removeAllItems();
            System.out.println("✓ Clear combo box");
            
            if (lopList == null || lopList.isEmpty()) {
                System.out.println("⚠️ CẢNH BÁO: Không có lớp nào từ database!");
                cboMaLop.addItem("(Không có dữ liệu)");
                return;
            }
            
            int count = 0;
            for (var lop : lopList) {
                if (lop != null && lop.getMaLop() != null && !lop.getMaLop().isEmpty()) {
                    cboMaLop.addItem(lop.getMaLop());
                    count++;
                }
            }
            
            System.out.println("✓ Đã thêm " + count + " lớp vào combo box");
            
            if (cboMaLop.getItemCount() > 0) {
                cboMaLop.setSelectedIndex(0);
                System.out.println("✓ Chọn lớp mặc định: " + cboMaLop.getSelectedItem());
            }
            
            System.out.println("=== Kết thúc loadLopComboBox thành công ===\n");
            
        } catch (Exception e) {
            System.out.println("❌ EXCEPTION trong loadLopComboBox: " + e.getClass().getName());
            System.out.println("❌ Message: " + e.getMessage());
            e.printStackTrace();
            cboMaLop.addItem("(Lỗi tải dữ liệu)");
        }
    }

    private void loadNamHocComboBox() {
        System.out.println("\n=== Bắt đầu loadNamHocComboBox ===");
        try {
            LopDAO lopDAO = new LopDAO();
            System.out.println("✓ Tạo LopDAO thành công");
            
            var lopList = lopDAO.getAllLop();
            System.out.println("✓ Gọi getAllLop() thành công, kết quả: " + (lopList == null ? "null" : lopList.size() + " lớp"));
            
            cboNamHoc.removeAllItems();
            System.out.println("✓ Clear combo box");
            
            if (lopList == null || lopList.isEmpty()) {
                System.out.println("⚠️ CẢNH BÁO: Không có lớp nào từ database!");
                cboNamHoc.addItem("(Không có dữ liệu)");
                return;
            }
            
            java.util.Set<String> namHocSet = new java.util.HashSet<>();
            for (var lop : lopList) {
                if (lop != null && lop.getNienKhoa() != null && !lop.getNienKhoa().isEmpty()) {
                    namHocSet.add(lop.getNienKhoa());
                }
            }
            
            System.out.println("✓ Tìm được " + namHocSet.size() + " năm học khác nhau");
            
            var sortedNamHoc = namHocSet.stream().sorted().toArray(String[]::new);
            
            if (sortedNamHoc.length == 0) {
                System.out.println("⚠️ CẢNH BÁO: Không có năm học nào!");
                cboNamHoc.addItem("(Không có dữ liệu)");
                return;
            }
            
            for (String nh : sortedNamHoc) {
                cboNamHoc.addItem(nh);
            }
            
            System.out.println("✓ Đã thêm " + sortedNamHoc.length + " năm học vào combo box");
            
            cboNamHoc.setSelectedIndex(sortedNamHoc.length - 1);
            System.out.println("✓ Chọn năm học mặc định: " + cboNamHoc.getSelectedItem());
            
            System.out.println("=== Kết thúc loadNamHocComboBox thành công ===\n");
            
        } catch (Exception e) {
            System.out.println("❌ EXCEPTION trong loadNamHocComboBox: " + e.getClass().getName());
            System.out.println("❌ Message: " + e.getMessage());
            e.printStackTrace();
            cboNamHoc.addItem("(Lỗi tải dữ liệu)");
        }
    }

    // Hàm loadTable được Controller gọi để đổ dữ liệu vào
    public void loadTable(List<Hocphi> list) {
        tableModel.setRowCount(0);
        int stt = 1; 
        for (Hocphi hp : list) {
            tableModel.addRow(new Object[]{
                stt++, // ID hiển thị là STT
                hp.getMaHS(), 
                hp.getHocKy(), 
                hp.getNamHoc(),
                hp.getTongTien(), 
                hp.getMienGiam(), 
                hp.getPhaiDong(), 
                hp.getTrangThai()
            });
        }
    }

    public void refreshForm() {
        txtMaHS.setText("");
        txtMaHS.setEditable(true);
        txtTongTien.setText("");
        txtMienGiam.setText("");
        txtPhaiDong.setText("");
        txtTrangThai.setText("");
        tableHocPhi.clearSelection();
    }

    // ===== Getters cho Controller =====
    public JComboBox<String> getCboMaLop() { return cboMaLop; }
    public JComboBox<String> getCboHocKy() { return cboHocKy; }
    public JComboBox<String> getCboNamHoc() { return cboNamHoc; }
    public JButton getBtnLoc() { return btnLoc; }
    public JButton getBtnThem() { return btnThem; }
    public JButton getBtnLuu() { return btnLuu; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnLamMoi() { return btnLamMoi; }
    public JTable getTableHocPhi() { return tableHocPhi; }
    public JTextField getTxtMaHS() { return txtMaHS; }
    public JTextField getTxtTongTien() { return txtTongTien; }
    public JTextField getTxtMienGiam() { return txtMienGiam; }
}