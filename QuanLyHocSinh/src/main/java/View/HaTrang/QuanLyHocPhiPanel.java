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

public class QuanLyHocPhiPanel extends JPanel {
    private JTextField txtLocMaLop, txtNamHoc;
    private JComboBox<String> cboHocKy;
    private JButton btnLoc, btnThem, btnLuu, btnXoa, btnLamMoi;
    private JTable tableHocPhi;
    private DefaultTableModel tableModel;
    private JTextField txtMaHS, txtTongTien, txtMienGiam, txtPhaiDong, txtTrangThai;

    public QuanLyHocPhiPanel() {
        initComponents();
        
        // Khởi tạo Controller (Controller sẽ gán sự kiện cho các nút)
        Hocphicontroller controller = new Hocphicontroller(this);
        
        // --- SỬA Ở ĐÂY ---
        // Tự động kích hoạt nút "Xem Danh Sách" ngay khi mở form
        // Việc này sẽ gọi logic lọc dữ liệu trong Controller với các giá trị mặc định (10A1, 2024-2025)
        btnLoc.doClick(); 
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
        // Giá trị mặc định ban đầu
        txtLocMaLop = new JTextField("10A1", 8); 
        pnlFilter.add(txtLocMaLop);

        pnlFilter.add(new JLabel("Học Kỳ:"));
        cboHocKy = new JComboBox<>(new String[]{"1", "2"});
        pnlFilter.add(cboHocKy);

        pnlFilter.add(new JLabel("Năm Học:"));
        // Giá trị mặc định ban đầu
        txtNamHoc = new JTextField("2024-2025", 8);
        pnlFilter.add(txtNamHoc);

        btnLoc = new JButton("Xem Danh Sách");
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
        tableHocPhi.getTableHeader().setBackground(new Color(236, 240, 241));
        
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
        btnLuu = createStyledButton("Cập Nhật", new Color(255, 250, 205));
        btnXoa = createStyledButton("Xóa Bỏ", new Color(255, 228, 225));
        btnLamMoi = createStyledButton("Làm Mới", new Color(220, 220, 220));

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
    public JTextField getTxtLocMaLop() { return txtLocMaLop; }
    public JComboBox<String> getCboHocKy() { return cboHocKy; }
    public JTextField getTxtNamHoc() { return txtNamHoc; }
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