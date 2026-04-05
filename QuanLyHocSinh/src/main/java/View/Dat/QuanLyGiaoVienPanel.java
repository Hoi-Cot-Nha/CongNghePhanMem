package View.Dat;

import Model.ToBoMon;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import TienIch.ButtonStyleHelper;

public class QuanLyGiaoVienPanel extends JPanel {

    private JTable tableGV;
    private DefaultTableModel tableModel;

    private JTextField txtMaGV, txtHoTen, txtSDT, txtTimKiem;
    private JSpinner spNgaySinh;
    private JComboBox<ToBoMon> cboMaToHop;

    private JButton btnThem, btnSua, btnXoa, btnLuu, btnHuy, btnXem, btnTimKiem;

    public QuanLyGiaoVienPanel() {
        initComponents();
    }

   private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== 1. PANEL NORTH: CHỨA TIÊU ĐỀ + TÌM KIẾM =====
        // Sử dụng BorderLayout: Title ở trên, Search ở dưới
        JPanel pnlNorth = new JPanel(new BorderLayout(0, 10));

        // -- Tiêu đề --
        JLabel lblTitle = new JLabel("QUẢN LÝ GIÁO VIÊN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Tạo khoảng hở

        // Thêm tiêu đề vào vị trí trên cùng của pnlNorth
        pnlNorth.add(lblTitle, BorderLayout.NORTH);


        // -- Thanh tìm kiếm --
        JPanel pnlSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlSearch.setBorder(new TitledBorder("Tìm kiếm"));

        pnlSearch.add(new JLabel("Từ khóa:"));
        txtTimKiem = new JTextField(20);
        btnTimKiem = new JButton("Tìm");
        ButtonStyleHelper.styleButtonSearch(btnTimKiem);

        pnlSearch.add(txtTimKiem);
        pnlSearch.add(btnTimKiem);

        // Thêm thanh tìm kiếm vào phần giữa của pnlNorth (nằm dưới tiêu đề)
        pnlNorth.add(pnlSearch, BorderLayout.CENTER);

        // ==> QUAN TRỌNG: Đưa cả khối pnlNorth ra giao diện chính
        add(pnlNorth, BorderLayout.NORTH);


        // ===== 2. TABLE (CENTER) =====
        String[] cols = {"Mã GV", "Họ Tên", "Ngày Sinh", "SĐT", "Tổ Bộ Môn"};
        tableModel = new DefaultTableModel(cols, 0);
        tableGV = new JTable(tableModel);
        tableGV.setRowHeight(25);
        javax.swing.table.DefaultTableCellRenderer headerRenderer = (javax.swing.table.DefaultTableCellRenderer) tableGV.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(new Color(100, 150, 200));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setOpaque(true);
        tableGV.getTableHeader().setDefaultRenderer(headerRenderer);
        add(new JScrollPane(tableGV), BorderLayout.CENTER);


        // ===== 3. FORM INPUT (SOUTH) =====
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.setBorder(new TitledBorder("Thông tin giáo viên"));

        JPanel pnlInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        pnlInput.add(new JLabel("Mã GV:"), gbc);
        gbc.gridx = 1;
        txtMaGV = new JTextField(15);
        pnlInput.add(txtMaGV, gbc);

        gbc.gridx = 2;
        pnlInput.add(new JLabel("Họ Tên:"), gbc);
        gbc.gridx = 3;
        txtHoTen = new JTextField(15);
        pnlInput.add(txtHoTen, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnlInput.add(new JLabel("Ngày Sinh:"), gbc);
        gbc.gridx = 1;
        spNgaySinh = new JSpinner(new SpinnerDateModel());
        spNgaySinh.setEditor(new JSpinner.DateEditor(spNgaySinh, "yyyy-MM-dd"));
        pnlInput.add(spNgaySinh, gbc);

        gbc.gridx = 2;
        pnlInput.add(new JLabel("SĐT:"), gbc);
        gbc.gridx = 3;
        txtSDT = new JTextField(15);
        pnlInput.add(txtSDT, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnlInput.add(new JLabel("Tổ Bộ Môn:"), gbc);
        gbc.gridx = 1;
        cboMaToHop = new JComboBox<>();
        pnlInput.add(cboMaToHop, gbc);

        gbc.gridx = 3;
        btnXem = new JButton("Tải lại DS");
        ButtonStyleHelper.styleButtonView(btnXem);
        pnlInput.add(btnXem, gbc);

        pnlSouth.add(pnlInput, BorderLayout.CENTER);


        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnThem = new JButton("Thêm");
        ButtonStyleHelper.styleButtonAdd(btnThem);
        btnSua  = new JButton("Sửa");
        ButtonStyleHelper.styleButtonEdit(btnSua);
        btnXoa  = new JButton("Xóa");
        ButtonStyleHelper.styleButtonDelete(btnXoa);
        btnLuu  = new JButton("Lưu");
        ButtonStyleHelper.styleButtonSave(btnLuu);
        btnHuy  = new JButton("Hủy");
        ButtonStyleHelper.styleButtonCancel(btnHuy);

        pnlBtn.add(btnThem);
        pnlBtn.add(btnSua);
        pnlBtn.add(btnXoa);
        pnlBtn.add(btnLuu);
        pnlBtn.add(btnHuy);

        pnlSouth.add(pnlBtn, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }


    public JTable getTableGV() { return tableGV; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public JTextField getTxtMaGV() { return txtMaGV; }
    public JTextField getTxtHoTen() { return txtHoTen; }
    public JTextField getTxtSDT() { return txtSDT; }
    public JSpinner getSpNgaySinh() { return spNgaySinh; }
    public JComboBox<ToBoMon> getCboMaToHop() { return cboMaToHop; }

    public JTextField getTxtTimKiem() { return txtTimKiem; }
    public JButton getBtnTimKiem() { return btnTimKiem; }
    public String getTuKhoaTuKiem(){ return txtTimKiem.getText();}
    
    public JButton getBtnThem() { return btnThem; }
    public JButton getBtnSua() { return btnSua; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnLuu() { return btnLuu; }
    public JButton getBtnHuy() { return btnHuy; }
    public JButton getBtnXem() { return btnXem; }
}
