/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.HaTrang;
import Model.Phuckhao;
import Controller.HaTrang.Phuckhaocontroller;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import TienIch.ButtonStyleHelper;
/**
 *
 * @author ADMIN
 */
public class QuanLyPhucKhaoPanel extends JPanel{
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaHS, txtMaMH, txtTrangThai, txtLoc;
    private JTextArea txtLyDo;
    private JButton btnLoc, btnThem, btnSua, btnXoa, btnLamMoi;

    public QuanLyPhucKhaoPanel() {
        initComponents();
   
        Phuckhaocontroller controller = new Controller.HaTrang.Phuckhaocontroller(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel pnlNorth = new JPanel(new BorderLayout(10, 10));
        pnlNorth.setOpaque(false);
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ PHÚC KHẢO", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(41, 128, 185));
        
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.add(new JLabel("Tìm kiếm (Mã HS/MH):"));
        txtLoc = new JTextField(20);
        btnLoc = new JButton("Tìm Kiếm");
        ButtonStyleHelper.styleButtonSearch(btnLoc);
        pnlFilter.add(txtLoc); 
        pnlFilter.add(btnLoc);
        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlFilter, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);


        String[] cols = {"STT", "Mã HS", "Mã MH", "Ngày gửi", "Trạng thái", "Lý do"};
        model = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setDefaultRenderer(new TienIch.CustomTableHeaderRenderer());
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel pnlSouth = new JPanel(new BorderLayout(10, 10));
        pnlSouth.setOpaque(false);
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(new TitledBorder("Chi tiết yêu cầu"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; pnlInput.add(new JLabel("Mã Học Sinh:"), gbc);
        gbc.gridx = 1; txtMaHS = new JTextField(25); pnlInput.add(txtMaHS, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlInput.add(new JLabel("Mã Môn Học:"), gbc);
        gbc.gridx = 1; txtMaMH = new JTextField(25); pnlInput.add(txtMaMH, gbc);
        gbc.gridx = 0; gbc.gridy = 2; pnlInput.add(new JLabel("Trạng Thái:"), gbc);
        gbc.gridx = 1; txtTrangThai = new JTextField(25); pnlInput.add(txtTrangThai, gbc);
        gbc.gridx = 0; gbc.gridy = 3; pnlInput.add(new JLabel("Lý Do:"), gbc);
        gbc.gridx = 1; txtLyDo = new JTextArea(8, 25); 
        txtLyDo.setLineWrap(true);
        txtLyDo.setWrapStyleWord(true);
        JScrollPane scrollLyDo = new JScrollPane(txtLyDo);
        scrollLyDo.setPreferredSize(new Dimension(400, 150));
        pnlInput.add(scrollLyDo, gbc);

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnThem = createBtn("Thêm Mới", new Color(224, 255, 255));
        ButtonStyleHelper.styleButtonAdd(btnThem);
        btnSua = createBtn("Cập Nhật", new Color(255, 250, 205));
        ButtonStyleHelper.styleButtonEdit(btnSua);
        btnXoa = createBtn("Xóa Bỏ", new Color(255, 228, 225));
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnLamMoi = createBtn("Làm Mới", new Color(220, 220, 220));
        ButtonStyleHelper.styleButtonView(btnLamMoi);
        pnlBtns.add(btnThem); pnlBtns.add(btnSua); pnlBtns.add(btnXoa); pnlBtns.add(btnLamMoi);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);
        pnlSouth.add(pnlBtns, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    private JButton createBtn(String t, Color c) {
        JButton b = new JButton(t); b.setBackground(c); b.setPreferredSize(new Dimension(110, 35)); return b;
    }

    public void loadTable(List<Phuckhao> list) {
        model.setRowCount(0); 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (Phuckhao pk : list) {
            model.addRow(new Object[]{
                stt++, 
                pk.getMaHS(), 
                pk.getMaMH(), 
                pk.getNgayGui() != null ? sdf.format(pk.getNgayGui()) : "", 
                pk.getTrangThai(), 
                pk.getLyDo()
            });
        }
    }

    public void fillForm(int row) {
        txtMaHS.setText(table.getValueAt(row, 1).toString());
        txtMaMH.setText(table.getValueAt(row, 2).toString());
        txtTrangThai.setText(table.getValueAt(row, 4).toString());
        txtLyDo.setText(table.getValueAt(row, 5).toString());
    }

    public void refresh() {
        txtMaHS.setText(""); txtMaMH.setText(""); txtTrangThai.setText(""); txtLyDo.setText(""); txtLoc.setText("");
        table.clearSelection();
    }


    public JTable getTable() { 
        return table; 
    }
    public JButton getBtnThem() { 
        return btnThem; 
    }
    public JButton getBtnSua() { 
        return btnSua; 
    }
    public JButton getBtnXoa() { 
        return btnXoa; 
    }
    public JButton getBtnLoc() { 
        return btnLoc; 
    }
    public JButton getBtnLamMoi() { 
        return btnLamMoi; 
    }
    public String getMaHS() { 
        return txtMaHS.getText(); 
    }
    public String getMaMH() { 
        return txtMaMH.getText(); 
    }
    public String getTrangThai() { 
        return txtTrangThai.getText(); 
    }
    public String getLyDo() { 
        return txtLyDo.getText(); 
    }
    public String getLocKeyword() { 
        return txtLoc.getText(); 
    }
}
