package View.Tien;

import Model.Diem;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

public class QuanLyDiemPanel extends JPanel {

    // --- Khai báo các Component ---
    // Bộ lọc dữ liệu (Lớp, Môn, Học Kỳ)
    private JTextField txtLocMaLop;
    private JComboBox<String> cboLocMon, cboLocHocKy;
    private JButton btnLocDuLieu;
    
    // Bảng hiển thị điểm
    private JTable tableDiem;
    private DefaultTableModel tableModel;
    
    // Form nhập liệu / Cập nhật điểm
    private JTextField txtMaHS, txtTenHS, txtDiemMieng, txtDiem15p, txtDiem1Tiet, txtDiemThi;
    private JButton btnCapNhat;
    
    // Tìm kiếm & Tiện ích
    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JButton btnXuatExcel;

    public QuanLyDiemPanel() {
        initComponents();
    }

    private void initComponents() {
        // Setup layout tổng thể: Border (Padding 10px)
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 1. PHẦN TRÊN (NORTH): Tiêu đề + Thanh công cụ (Lọc & Search)
        JPanel pnlNorth = new JPanel(new BorderLayout(5, 5));

        // Tiêu đề to đậm
        JLabel lblTitle = new JLabel("QUẢN LÝ ĐIỂM HỌC SINH", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        pnlNorth.add(lblTitle, BorderLayout.NORTH);

        JPanel pnlToolBar = new JPanel(new GridLayout(2, 1, 5, 5));

        // Panel Bộ Lọc
        JPanel pnlFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlFilter.setBorder(new TitledBorder("Lọc theo lớp (Mặc định)"));
        
        pnlFilter.add(new JLabel("Mã Lớp:"));
        txtLocMaLop = new JTextField("10A1", 8); 
        pnlFilter.add(txtLocMaLop);

        pnlFilter.add(new JLabel("Môn:"));
        cboLocMon = new JComboBox<>(new String[]{"TOAN", "VAN", "ANH", "LY", "HOA"}); 
        pnlFilter.add(cboLocMon);

        pnlFilter.add(new JLabel("Học Kỳ:"));
        cboLocHocKy = new JComboBox<>(new String[]{"1", "2"}); 
        pnlFilter.add(cboLocHocKy);

        btnLocDuLieu = new JButton("Lọc");
        pnlFilter.add(btnLocDuLieu);
        pnlToolBar.add(pnlFilter);

        // Panel Tìm Kiếm Nhanh
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        pnlSearch.setBorder(new TitledBorder("Tìm kiếm nhanh"));
        
        pnlSearch.add(new JLabel("Nhập Tên hoặc Mã HS:"));
        txtTimKiem = new JTextField(20);
        pnlSearch.add(txtTimKiem);
        
        btnTimKiem = new JButton("Tìm Kiếm");
        btnTimKiem.setBackground(new Color(255, 102, 0)); // Màu cam
        btnTimKiem.setForeground(Color.WHITE);
        pnlSearch.add(btnTimKiem);
        
        pnlToolBar.add(pnlSearch);
        
        pnlNorth.add(pnlToolBar, BorderLayout.CENTER);
        this.add(pnlNorth, BorderLayout.NORTH);

        // 2. PHẦN GIỮA (CENTER): Bảng Điểm
        String[] columnNames = {"Mã HS", "Họ Tên", "Môn", "HK", "Điểm Miệng", "15 Phút", "1 Tiết", "Điểm Thi", "Trung Bình"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableDiem = new JTable(tableModel);
        tableDiem.setRowHeight(25);
        this.add(new JScrollPane(tableDiem), BorderLayout.CENTER);

        // 3. PHẦN DƯỚI (SOUTH): Form Cập Nhật + Nút Bấm
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBorder(new TitledBorder("Cập nhật điểm"));
        
        // Dùng GridBagLayout để căn chỉnh các ô nhập điểm cho thẳng hàng
        JPanel pnlInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); gbc.fill = GridBagConstraints.HORIZONTAL;

        // Hàng 1: Thông tin HS (Readonly)
        gbc.gridx=0; gbc.gridy=0; pnlInput.add(new JLabel("Mã HS:"), gbc);
        gbc.gridx=1; gbc.gridy=0; txtMaHS=new JTextField(12); txtMaHS.setEditable(false); pnlInput.add(txtMaHS, gbc);
        gbc.gridx=2; gbc.gridy=0; pnlInput.add(new JLabel("Họ Tên:"), gbc);
        gbc.gridx=3; gbc.gridy=0; txtTenHS=new JTextField(12); txtTenHS.setEditable(false); pnlInput.add(txtTenHS, gbc);

        // Hàng 2: Điểm Miệng & 15p
        gbc.gridx=0; gbc.gridy=1; pnlInput.add(new JLabel("Điểm Miệng:"), gbc);
        gbc.gridx=1; gbc.gridy=1; txtDiemMieng=new JTextField(); pnlInput.add(txtDiemMieng, gbc);
        
        gbc.gridx=2; gbc.gridy=1; pnlInput.add(new JLabel("Điểm 15p:"), gbc);
        gbc.gridx=3; gbc.gridy=1; txtDiem15p=new JTextField(); pnlInput.add(txtDiem15p, gbc);

        // Hàng 3: 1 Tiết & Điểm Thi
        gbc.gridx=0; gbc.gridy=2; pnlInput.add(new JLabel("Điểm 1 Tiết:"), gbc);
        gbc.gridx=1; gbc.gridy=2; txtDiem1Tiet=new JTextField(); pnlInput.add(txtDiem1Tiet, gbc);
        
        gbc.gridx=2; gbc.gridy=2; pnlInput.add(new JLabel("Điểm Thi:"), gbc);
        gbc.gridx=3; gbc.gridy=2; txtDiemThi=new JTextField(); pnlInput.add(txtDiemThi, gbc);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);
        
        // Panel chứa nút Lưu và Xuất Excel
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCapNhat = new JButton("Lưu / Cập Nhật Điểm");
        btnCapNhat.setPreferredSize(new Dimension(200, 40));
        pnlButton.add(btnCapNhat);
        
        // Nút Xuất Excel (Style xanh lá)
        btnXuatExcel = new JButton("Xuất Excel");
        btnXuatExcel.setBackground(new Color(30, 130, 76));
        btnXuatExcel.setForeground(Color.WHITE);
        btnXuatExcel.setPreferredSize(new Dimension(130, 40));
        pnlButton.add(btnXuatExcel);
        
        pnlSouth.add(pnlButton, BorderLayout.SOUTH);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- Các hàm Getter dữ liệu từ Form (Cho Controller gọi) ---
    public String getMaLopFilter() { return txtLocMaLop.getText().trim(); }
    public String getMaMonFilter() { return cboLocMon.getSelectedItem().toString(); }
    public int getHocKyFilter() { return Integer.parseInt(cboLocHocKy.getSelectedItem().toString()); }
    public String getTuKhoaTimKiem() { return txtTimKiem.getText().trim(); }

    // Đóng gói dữ liệu nhập thành Object Diem
    public Diem getDiemInput() {
        Diem d = new Diem();
        d.setMaHS(txtMaHS.getText());
        d.setMaMH(getMaMonFilter()); // Lấy môn đang chọn ở filter
        d.setHocKy(getHocKyFilter()); // Lấy học kỳ đang chọn ở filter
        try {
            // Parse điểm, nếu lỗi format thì return null để Controller xử lý
            d.setDiemMieng(Double.parseDouble(txtDiemMieng.getText()));
            d.setDiem15p(Double.parseDouble(txtDiem15p.getText()));
            d.setDiem1Tiet(Double.parseDouble(txtDiem1Tiet.getText()));
            d.setDiemThi(Double.parseDouble(txtDiemThi.getText()));
        } catch (Exception e) { return null; }
        return d;
    }

    // --- Hàm hiển thị dữ liệu (Setter) ---
    // Đổ list điểm lên bảng
    public void setTableData(List<Diem> list) {
        tableModel.setRowCount(0);
        for (Diem d : list) {
            // Làm tròn điểm trung bình 2 chữ số thập phân
            double dtb = Math.round(d.getDiemTB() * 100.0) / 100.0;
            tableModel.addRow(new Object[]{
                d.getMaHS(), 
                d.getTenHS(), 
                d.getMaMH(), 
                d.getHocKy(),
                d.getDiemMieng(), 
                d.getDiem15p(),   
                d.getDiem1Tiet(), 
                d.getDiemThi(),   
                dtb               
            });
        }
    }

    // Click vào dòng -> Đổ ngược dữ liệu vào form nhập
    public void fillFormInput(int row) {
        if (row >= 0) {
            txtMaHS.setText(tableModel.getValueAt(row, 0).toString());
            txtTenHS.setText(tableModel.getValueAt(row, 1).toString());
            
            txtDiemMieng.setText(tableModel.getValueAt(row, 4).toString());
            txtDiem15p.setText(tableModel.getValueAt(row, 5).toString());
            txtDiem1Tiet.setText(tableModel.getValueAt(row, 6).toString());
            txtDiemThi.setText(tableModel.getValueAt(row, 7).toString());
        }
    }

    // --- Tiện ích & Gán sự kiện ---
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public JTable getTable() { return tableDiem; }

    public void addBtnXemListener(ActionListener action) { btnLocDuLieu.addActionListener(action); }
    public void addBtnTimKiemListener(ActionListener action) { btnTimKiem.addActionListener(action); } 
    public void addBtnCapNhatListener(ActionListener action) { btnCapNhat.addActionListener(action); }
    public void addTableMouseListener(MouseAdapter adapter) { tableDiem.addMouseListener(adapter); }
    public void addBtnXuatExcelListener(ActionListener ac) { btnXuatExcel.addActionListener(ac); }
}