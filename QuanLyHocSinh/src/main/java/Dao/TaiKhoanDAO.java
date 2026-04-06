package Dao;

import Model.TaiKhoan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.ConnectDB;

public class TaiKhoanDAO {

    
    public String checkLogin(String user, String pass) {
        String sql = "SELECT Quyen FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
             
                return rs.getString("Quyen"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  
    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

 
    public boolean insert(TaiKhoan tk) {
        String sql = """
            INSERT INTO TaiKhoan
            (TenDangNhap, MatKhau, Quyen, MaNguoiDung)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getQuyen());
            ps.setString(4, tk.getMaNguoiDung());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(TaiKhoan tk) {
        String sql = """
            UPDATE TaiKhoan
            SET MatKhau = ?, Quyen = ?, MaNguoiDung = ?
            WHERE TenDangNhap = ?
        """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getQuyen());
            ps.setString(3, tk.getMaNguoiDung());
            ps.setString(4, tk.getTenDangNhap());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(String tenDangNhap) {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<TaiKhoan> search(String keyword) {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = """
            SELECT * FROM TaiKhoan
            WHERE TenDangNhap LIKE ?
               OR Quyen LIKE ?
               OR MaNguoiDung LIKE ?
        """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    private TaiKhoan map(ResultSet rs) throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setTenDangNhap(rs.getString("TenDangNhap"));
        tk.setMatKhau(rs.getString("MatKhau"));
        tk.setQuyen(rs.getString("Quyen"));
        tk.setMaNguoiDung(rs.getString("MaNguoiDung"));
        return tk;
    }
    // Thêm cho phân quyền tài khoản
    public TaiKhoan checkLoginFull(String tenDangNhap, String matKhau) {
        TaiKhoan tk = null;
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";

        try (java.sql.Connection con = ConnectDB.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = new TaiKhoan();
                    tk.setTenDangNhap(rs.getString("TenDangNhap"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    tk.setQuyen(rs.getString("Quyen"));
                    tk.setMaNguoiDung(rs.getString("MaNguoiDung")); // Lấy mã để phân quyền dữ liệu
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}