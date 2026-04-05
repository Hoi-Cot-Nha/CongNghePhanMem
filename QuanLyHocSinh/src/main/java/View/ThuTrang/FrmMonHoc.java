/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.ThuTrang;

import Model.MonHoc;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import TienIch.ButtonStyleHelper;

/**
 *
 * @author Admin
 */
public class FrmMonHoc extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private JTextField txtMaMH, txtTenMH, txtTimKiem;
    private JButton btnXem, btnTimKiem, btnLuu, btnXoa, btnMoi;

    public FrmMonHoc() {
        initComponents();
    }

   private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== 1. PANEL NORTH: CHỨA TẤT CẢ PHẦN ĐẦU =====
        // Sử dụng BorderLayout để xếp 3 món (Tiêu đề - Xem - Tìm) dọc xuống
        JPanel pnlNorth = new JPanel(new BorderLayout(0, 5)); // 5 là khoảng cách giữa các phần

        // -- Món 1: Tiêu đề (Đặt ở NORTH) --
        JLabel title = new JLabel("QUẢN LÝ MÔN HỌC", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(0, 102, 204));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Tạo khoảng hở dưới chữ
        pnlNorth.add(title, BorderLayout.NORTH);

        // -- Món 2: Khung Xem danh sách (Đặt ở CENTER) --
        JPanel pnlView = new JPanel();
        pnlView.setBorder(new TitledBorder("Xem danh sách"));
        btnXem = new JButton("Xem danh sách");
        ButtonStyleHelper.styleButtonView(btnXem);
        pnlView.add(btnXem);
        pnlNorth.add(pnlView, BorderLayout.CENTER);

        // -- Món 3: Khung Tìm kiếm (Đặt ở SOUTH) --
        JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder("Tìm kiếm"));
        pnlSearch.add(new JLabel("Mã / Tên môn:"));
        txtTimKiem = new JTextField(20);
        pnlSearch.add(txtTimKiem);
        btnTimKiem = new JButton("Tìm");
        ButtonStyleHelper.styleButtonSearch(btnTimKiem);
        pnlSearch.add(btnTimKiem);
        pnlNorth.add(pnlSearch, BorderLayout.SOUTH);

        // Đưa pnlNorth (đã chứa đủ 3 món) ra giao diện chính
        add(pnlNorth, BorderLayout.NORTH);


        // ===== 2. TABLE (CENTER) =====
        model = new DefaultTableModel(new String[]{"Mã MH", "Tên môn"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        javax.swing.table.DefaultTableCellRenderer headerRenderer = (javax.swing.table.DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(new Color(100, 150, 200));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setOpaque(true);
        table.getTableHeader().setDefaultRenderer(headerRenderer);
        add(new JScrollPane(table), BorderLayout.CENTER);


        // ===== 3. FORM INPUT (SOUTH) =====
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBorder(new TitledBorder("Cập nhật môn học"));

        JPanel pnlInput = new JPanel(new GridLayout(2, 2, 10, 5));
        pnlInput.add(new JLabel("Mã môn:"));
        txtMaMH = new JTextField();
        pnlInput.add(txtMaMH);

        pnlInput.add(new JLabel("Tên môn:"));
        txtTenMH = new JTextField();
        pnlInput.add(txtTenMH);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);

        JPanel pnlBtn = new JPanel();
        btnLuu = new JButton("Lưu");
        ButtonStyleHelper.styleButtonSave(btnLuu);
        btnXoa = new JButton("Xóa");
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnMoi = new JButton("Mới");
        ButtonStyleHelper.styleButtonView(btnMoi);
        pnlBtn.add(btnLuu);
        pnlBtn.add(btnXoa);
        pnlBtn.add(btnMoi);

        pnlSouth.add(pnlBtn, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // ===== DATA =====
    public MonHoc getMonHocInput() {
        return new MonHoc(
            txtMaMH.getText().trim(),
            txtTenMH.getText().trim()
        );
    }

    public String getTuKhoa() {
        return txtTimKiem.getText().trim();
    }

    public void setTableData(List<MonHoc> list) {
        model.setRowCount(0);
        for (MonHoc m : list) {
            model.addRow(new Object[]{m.getMaMH(), m.getTenMH()});
        }
    }

    public void fillForm(int row) {
        txtMaMH.setText(model.getValueAt(row, 0).toString());
        txtTenMH.setText(model.getValueAt(row, 1).toString());
        txtMaMH.setEditable(false);
    }

    public void clearForm() {
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtMaMH.setEditable(true);
    }

    public JTable getTable() {
        return table;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    // ===== EVENTS =====
    public void addBtnXemListener(ActionListener l) {
        btnXem.addActionListener(l);
    }

    public void addBtnTimKiemListener(ActionListener l) {
        btnTimKiem.addActionListener(l);
    }

    public void addBtnLuuListener(ActionListener l) {
        btnLuu.addActionListener(l);
    }

    public void addBtnXoaListener(ActionListener l) {
        btnXoa.addActionListener(l);
    }

    public void addBtnMoiListener(ActionListener l) {
        btnMoi.addActionListener(l);
    }

    public void addTableMouseListener(MouseAdapter l) {
        table.addMouseListener(l);
    }
}