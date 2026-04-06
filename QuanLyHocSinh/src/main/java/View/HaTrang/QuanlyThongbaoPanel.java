/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.HaTrang;
import Controller.HaTrang.Thongbaocontroller;
import Model.Thongbao;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import TienIch.ButtonStyleHelper;
import TienIch.TableSortHelper;
/**
 *
 * @author ADMIN
 */
public class QuanlyThongbaoPanel extends JPanel{
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtTieuDe, txtNguoiGui, txtLocKeyword; 
    private JTextArea txtNoiDung;
    private JButton btnLoc, btnThem, btnSua, btnXoa, btnLamMoi;

    public QuanlyThongbaoPanel() {
        initComponents();
        Thongbaocontroller controller = new Thongbaocontroller(this);
    }

    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

 
        JPanel pnlNorth = new JPanel(new BorderLayout(10, 10));
        pnlNorth.setOpaque(false);
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ THÔNG BÁO", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(new Color(41, 128, 185));
        
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pnlFilter.setBackground(Color.WHITE);
        pnlFilter.add(new JLabel("Tìm kiếm:"));
        txtLocKeyword = new JTextField(20);
        btnLoc = new JButton("Tìm Kiếm");
        ButtonStyleHelper.styleButtonSearch(btnLoc);
        pnlFilter.add(txtLocKeyword); pnlFilter.add(btnLoc);

        pnlNorth.add(lblTitle, BorderLayout.NORTH);
        pnlNorth.add(pnlFilter, BorderLayout.SOUTH);
        add(pnlNorth, BorderLayout.NORTH);

 
        String[] cols = {"STT", "Tiêu đề", "Người gửi", "Ngày tạo", "Nội dung"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        TableSortHelper.enableTableSorting(table);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setDefaultRenderer(new TienIch.CustomTableHeaderRenderer());
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel pnlSouth = new JPanel(new BorderLayout(10, 10));
        pnlSouth.setOpaque(false);

        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBackground(Color.WHITE);
        pnlInput.setBorder(new TitledBorder("Thông tin chi tiết"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; pnlInput.add(new JLabel("Tiêu đề:"), gbc);
        gbc.gridx = 1; txtTieuDe = new JTextField(30); pnlInput.add(txtTieuDe, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlInput.add(new JLabel("Người gửi:"), gbc);
        gbc.gridx = 1; txtNguoiGui = new JTextField(30); pnlInput.add(txtNguoiGui, gbc);
        gbc.gridx = 0; gbc.gridy = 2; pnlInput.add(new JLabel("Nội dung:"), gbc);
        gbc.gridx = 1; txtNoiDung = new JTextArea(4, 30);
        txtNoiDung.setLineWrap(true);
        pnlInput.add(new JScrollPane(txtNoiDung), gbc);

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnThem = createBtn("Thêm Mới", new Color(224, 255, 255));
        ButtonStyleHelper.styleButtonAdd(btnThem);
        btnSua = createBtn("Cập Nhật", new Color(255, 250, 205));
        ButtonStyleHelper.styleButtonEdit(btnSua);
        btnXoa = createBtn("Xóa Bỏ", new Color(255, 228, 225));
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnLamMoi = createBtn("Làm Mới", new Color(220, 220, 220));
        ButtonStyleHelper.styleButtonView(btnLamMoi);
        
        pnlBtns.add(btnThem); 
        pnlBtns.add(btnSua); 
        pnlBtns.add(btnXoa); 
        pnlBtns.add(btnLamMoi);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);
        pnlSouth.add(pnlBtns, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row != -1) {
         
                    txtTieuDe.setText(table.getValueAt(row, 1).toString());
                    txtNguoiGui.setText(table.getValueAt(row, 2).toString());
               
                    txtNoiDung.setText(table.getValueAt(row, 4).toString());
                }
            }
        });
    }

    private JButton createBtn(String t, Color c) {
        JButton b = new JButton(t); 
        b.setBackground(c);
        b.setPreferredSize(new Dimension(120, 35)); 
        return b;
    }

    public void loadTable(List<Thongbao> list) {
        model.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int stt = 1;
        for (Thongbao tb : list) {
            model.addRow(new Object[]{stt++, tb.getTieuDe(), tb.getNguoiGui(), 
                tb.getNgayTao() != null ? sdf.format(tb.getNgayTao()) : "", tb.getNoiDung()});
        }
    }

    public void fillForm(int row) {
        txtTieuDe.setText(table.getValueAt(row, 1).toString());
        txtNguoiGui.setText(table.getValueAt(row, 2).toString());
        txtNoiDung.setText(table.getValueAt(row, 4).toString());
    }

    public void refresh() {
        txtTieuDe.setText(""); txtNguoiGui.setText(""); 
        txtNoiDung.setText(""); txtLocKeyword.setText("");
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
    public String getTieuDe() { 
        return txtTieuDe.getText(); 
    }
    public String getNguoiGui() { 
        return txtNguoiGui.getText(); 
    }
    public String getNoiDung() { 
        return txtNoiDung.getText(); 
    }
    public String getLocKeyword() { 
        return txtLocKeyword.getText(); 
    }
    
}
