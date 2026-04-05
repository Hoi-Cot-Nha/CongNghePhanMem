package View.Tien;

import Model.HanhKiem;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class HanhKiemPanel extends JPanel {
    
    // --- Khai báo Component ---
    private JButton btnXuatExcel;
    // Bộ lọc và tìm kiếm
    private JTextField txtMaLop, txtNamHoc, txtTimKiem; 
    private JComboBox<String> cboHocKy, cboXepLoai;
    
    // Bảng hiển thị
    private JTable table;
    private DefaultTableModel model;
    
    // Nút chức năng
    private JButton btnXem, btnTimKiem, btnLuu, btnXoa, btnMoi; 

    // Form nhập liệu chi tiết
    private JTextField txtMaHS, txtTenHS;
    private JTextArea txtNhanXet;

    public HanhKiemPanel() {
        initComponents();
    }

    private void initComponents() {
        // Setup layout chính: Kiểu Border (Bắc - Trung - Nam)
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. PHẦN TRÊN (NORTH): Tiêu đề + Bộ lọc + Tìm kiếm
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));
        
        // Tiêu đề to đùng
        JLabel lblTitle = new JLabel("QUẢN LÝ HẠNH KIỂM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);

        JPanel pnlTools = new JPanel(new GridLayout(2, 1, 5, 5));

        // Panel Lọc (Mã lớp, Năm học, Học kỳ)
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlFilter.setBorder(new TitledBorder("Lọc theo lớp (Mặc định)"));
        
        pnlFilter.add(new JLabel("Mã Lớp:"));
        txtMaLop = new JTextField("10A1", 8); pnlFilter.add(txtMaLop); 
        
        pnlFilter.add(new JLabel("Năm Học:"));
        txtNamHoc = new JTextField("2024-2025", 8); pnlFilter.add(txtNamHoc);
        
        pnlFilter.add(new JLabel("Học Kỳ:"));
        cboHocKy = new JComboBox<>(new String[]{"1", "2"}); pnlFilter.add(cboHocKy);
        
        btnXem = new JButton("Lọc Danh Sách");
        pnlFilter.add(btnXem);
        pnlTools.add(pnlFilter);

        // Panel Tìm kiếm nhanh
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlSearch.setBorder(new TitledBorder("Tìm kiếm nhanh"));
        
        pnlSearch.add(new JLabel("Nhập Tên hoặc Mã HS:"));
        txtTimKiem = new JTextField(20); pnlSearch.add(txtTimKiem);
        
        btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setBackground(new Color(255, 102, 0)); // Màu cam nổi bật
        btnTimKiem.setForeground(Color.WHITE);
        pnlSearch.add(btnTimKiem);
        pnlTools.add(pnlSearch);

        pnlNorth.add(pnlTools, BorderLayout.CENTER);
        add(pnlNorth, BorderLayout.NORTH);
        
        // Nút Xuất Excel (Style màu xanh lá)
        btnXuatExcel = new JButton("Xuất Excel");
        btnXuatExcel.setBackground(new Color(30, 130, 76)); 
        btnXuatExcel.setForeground(Color.WHITE);
        btnXuatExcel.setPreferredSize(new Dimension(120, 35));

        // 2. PHẦN GIỮA (CENTER): Bảng dữ liệu
        String[] cols = {"Mã HS", "Tên HS", "Năm Học", "Học Kỳ", "Xếp Loại", "Nhận Xét"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // 3. PHẦN DƯỚI (SOUTH): Form nhập liệu + Nút tác vụ
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBorder(new TitledBorder("Cập nhật Hạnh Kiểm"));
        
        // GridBagLayout để căn chỉnh form nhập liệu cho đẹp
        JPanel pnlInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dòng 1: Mã HS + Tên HS (Readonly vì chỉ cập nhật HK, k sửa tên ở đây)
        gbc.gridx=0; gbc.gridy=0; pnlInput.add(new JLabel("Mã HS:"), gbc);
        gbc.gridx=1; gbc.gridy=0; txtMaHS = new JTextField(15); txtMaHS.setEditable(false); pnlInput.add(txtMaHS, gbc);
        
        gbc.gridx=2; gbc.gridy=0; pnlInput.add(new JLabel("Tên HS:"), gbc);
        gbc.gridx=3; gbc.gridy=0; txtTenHS = new JTextField(15); txtTenHS.setEditable(false); pnlInput.add(txtTenHS, gbc);
        
        // Dòng 2: Xếp loại + Nhận xét
        gbc.gridx=0; gbc.gridy=1; pnlInput.add(new JLabel("Xếp Loại:"), gbc);
        gbc.gridx=1; gbc.gridy=1; 
        cboXepLoai = new JComboBox<>(new String[]{"Tot", "Kha", "Trung Binh", "Yeu"}); 
        pnlInput.add(cboXepLoai, gbc);
        
        gbc.gridx=2; gbc.gridy=1; pnlInput.add(new JLabel("Nhận Xét:"), gbc);
        gbc.gridx=3; gbc.gridy=1; 
        txtNhanXet = new JTextArea(2, 20); 
        pnlInput.add(new JScrollPane(txtNhanXet), gbc);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);

        // Panel chứa các nút bấm cuối cùng
        JPanel pnlBtn = new JPanel();
        btnLuu = new JButton("Lưu / Cập Nhật");
        btnLuu.setPreferredSize(new Dimension(150, 35));
        
        btnXoa = new JButton("Xóa");
        btnXoa.setPreferredSize(new Dimension(100, 35));
        
        btnMoi = new JButton("Làm Mới Form");
        
        pnlBtn.add(btnXuatExcel);
        pnlBtn.add(btnLuu); pnlBtn.add(btnXoa); pnlBtn.add(btnMoi);
        pnlSouth.add(pnlBtn, BorderLayout.SOUTH);
        
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- CÁC HÀM GETTER DỮ LIỆU TỪ FORM (ĐỂ CONTROLLER GỌI) ---
    public String getMaLopFilter() { return txtMaLop.getText().trim(); }
    public String getNamHocFilter() { return txtNamHoc.getText().trim(); }
    public int getHocKyFilter() { return Integer.parseInt(cboHocKy.getSelectedItem().toString()); }
    public String getTuKhoaTimKiem() { return txtTimKiem.getText().trim(); } 
    
    // Đóng gói dữ liệu từ form nhập thành Object HanhKiem
    public HanhKiem getHanhKiemInput() {
        HanhKiem hk = new HanhKiem();
        hk.setMaHS(txtMaHS.getText());
        hk.setNamHoc(getNamHocFilter()); // Lấy năm từ ô lọc
        hk.setHocKy(getHocKyFilter());   // Lấy kỳ từ ô lọc
        hk.setXepLoai(cboXepLoai.getSelectedItem().toString());
        hk.setNhanXet(txtNhanXet.getText());
        return hk;
    }

    // --- CÁC HÀM SETTER (HIỂN THỊ DỮ LIỆU) ---
    // Đổ danh sách Hạnh kiểm lên bảng
    public void setTableData(List<HanhKiem> list) {
        model.setRowCount(0);
        for (HanhKiem hk : list) {
            model.addRow(new Object[]{
                hk.getMaHS(), hk.getTenHS(), hk.getNamHoc(), hk.getHocKy(), hk.getXepLoai(), hk.getNhanXet()
            });
        }
    }
    
    // Khi click vào bảng -> Đổ dữ liệu ngược lại form nhập
    public void fillFormInput(int row) {
        if(row >= 0) {
            txtMaHS.setText(model.getValueAt(row, 0).toString());
            txtTenHS.setText(model.getValueAt(row, 1).toString());
     
            cboXepLoai.setSelectedItem(model.getValueAt(row, 4).toString());
            Object nx = model.getValueAt(row, 5);
            txtNhanXet.setText(nx != null ? nx.toString() : "");
        }
    }
    
    // Reset trắng form nhập
    public void clearForm() {
        txtMaHS.setText(""); txtTenHS.setText(""); txtNhanXet.setText("");
    }
    
    // Tiện ích hiện thông báo
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public JTable getTable() { return table; }

    // --- GÁN SỰ KIỆN (Controller sẽ dùng các hàm này) ---
    public void addBtnXemListener(ActionListener log) { btnXem.addActionListener(log); }
    public void addBtnTimKiemListener(ActionListener log) { btnTimKiem.addActionListener(log); }
    public void addBtnLuuListener(ActionListener log) { btnLuu.addActionListener(log); }
    public void addBtnXoaListener(ActionListener log) { btnXoa.addActionListener(log); }
    public void addBtnMoiListener(ActionListener log) { btnMoi.addActionListener(log); }
    public void addTableMouseListener(MouseAdapter log) { table.addMouseListener(log); }
    public void addBtnXuatExcelListener(ActionListener log) { btnXuatExcel.addActionListener(log); }
}