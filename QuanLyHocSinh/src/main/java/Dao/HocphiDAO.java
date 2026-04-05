/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Connection.ConnectDB;
import Model.Hocphi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class HocphiDAO {
    public List<Hocphi> getHocPhiByLop(String maLop, int hocKy, String namHoc) {
        List<Hocphi> list = new ArrayList<>();

        System.out.println("\n=== HocphiDAO.getHocPhiByLop ===");
        System.out.println("Tham số: maLop=" + maLop + ", hocKy=" + hocKy + ", namHoc=" + namHoc);

        String sql = "SELECT hp.* FROM HocPhi hp " +
                     "JOIN HocSinh hs ON hp.MaHS = hs.MaHS " +
                     "WHERE hs.MaLop = ? AND hp.HocKy = ? AND hp.NamHoc = ?";

        try {
            Connection cons = ConnectDB.getConnection();
            if (cons == null) {
                System.out.println("❌ HocphiDAO: Kết nối database là null!");
                return list;
            }
            
            System.out.println("✓ Kết nối thành công");
            
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, maLop);
            ps.setInt(2, hocKy);
            ps.setString(3, namHoc);

            System.out.println("✓ PreparedStatement tạo thành công");

            ResultSet rs = ps.executeQuery();
            System.out.println("✓ executeQuery() thành công");

            while (rs.next()) {
                Hocphi hp = new Hocphi();
                hp.setMaHP(rs.getInt("MaHP"));
                hp.setMaHS(rs.getString("MaHS"));
                hp.setHocKy(rs.getInt("HocKy"));
                hp.setNamHoc(rs.getString("NamHoc"));
                hp.setTongTien(rs.getLong("TongTien"));
                hp.setMienGiam(rs.getLong("MienGiam"));
                hp.setPhaiDong(rs.getLong("PhaiDong"));
                hp.setTrangThai(rs.getString("TrangThai"));
                list.add(hp);
            }
            
            System.out.println("✓ Tìm được " + list.size() + " dòng");
            
            rs.close();
            ps.close();
            cons.close();
            
        } catch (Exception e) {
            System.out.println("❌ Exception trong getHocPhiByLop: " + e.getClass().getName());
            System.out.println("❌ Message: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=== Kết thúc getHocPhiByLop ===\n");
        return list;
    }

    public List<Hocphi> getAllHocPhi() {
        List<Hocphi> list = new ArrayList<>();
        String sql = "SELECT * FROM HocPhi ORDER BY MaHS, HocKy, NamHoc";

        try (Connection cons = ConnectDB.getConnection();
             PreparedStatement ps = cons.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Hocphi hp = new Hocphi();
                hp.setMaHP(rs.getInt("MaHP"));
                hp.setMaHS(rs.getString("MaHS"));
                hp.setHocKy(rs.getInt("HocKy"));
                hp.setNamHoc(rs.getString("NamHoc"));
                hp.setTongTien(rs.getLong("TongTien"));
                hp.setMienGiam(rs.getLong("MienGiam"));
                hp.setPhaiDong(rs.getLong("PhaiDong"));
                hp.setTrangThai(rs.getString("TrangThai"));
                list.add(hp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public boolean saveHocPhi(Hocphi hp) {
        
        String sqlCheckHS = "SELECT COUNT(*) FROM HocSinh WHERE MaHS = ?";

    
        String sqlCheckExist = "SELECT COUNT(*) FROM HocPhi WHERE MaHS=? AND HocKy=? AND NamHoc=?";

        String sqlInsert = "INSERT INTO HocPhi(MaHS, HocKy, NamHoc, TongTien, MienGiam, PhaiDong, TrangThai) VALUES(?,?,?,?,?,?,?)";
        String sqlUpdate = "UPDATE HocPhi SET TongTien=?, MienGiam=?, PhaiDong=?, TrangThai=? WHERE MaHS=? AND HocKy=? AND NamHoc=?";

        try (Connection cons = ConnectDB.getConnection()) {
 
            PreparedStatement psCheckHS = cons.prepareStatement(sqlCheckHS);
            psCheckHS.setString(1, hp.getMaHS());
            ResultSet rsHS = psCheckHS.executeQuery();
            if (rsHS.next() && rsHS.getInt(1) == 0) {
                System.out.println("Lỗi: Mã học sinh không tồn tại trong hệ thống!");
                return false; 
            }

            PreparedStatement psCheckEx = cons.prepareStatement(sqlCheckExist);
            psCheckEx.setString(1, hp.getMaHS());
            psCheckEx.setInt(2, hp.getHocKy());
            psCheckEx.setString(3, hp.getNamHoc());
            ResultSet rsEx = psCheckEx.executeQuery();
            rsEx.next();
            boolean isExist = rsEx.getInt(1) > 0;

            if (!isExist) {
                
                PreparedStatement ps = cons.prepareStatement(sqlInsert);
                ps.setString(1, hp.getMaHS());
                ps.setInt(2, hp.getHocKy());
                ps.setString(3, hp.getNamHoc());
                ps.setLong(4, hp.getTongTien());
                ps.setLong(5, hp.getMienGiam());
                ps.setLong(6, hp.getPhaiDong());
                ps.setString(7, hp.getTrangThai());
                return ps.executeUpdate() > 0;
            } else {
                
                PreparedStatement ps = cons.prepareStatement(sqlUpdate);
                ps.setLong(1, hp.getTongTien());
                ps.setLong(2, hp.getMienGiam());
                ps.setLong(3, hp.getPhaiDong());
                ps.setString(4, hp.getTrangThai());
                ps.setString(5, hp.getMaHS());
                ps.setInt(6, hp.getHocKy());
                ps.setString(7, hp.getNamHoc());
                return ps.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }


    public boolean deleteHocPhi(int maHP) {
        String sql = "DELETE FROM HocPhi WHERE MaHP=?";
        try (Connection cons = ConnectDB.getConnection();
             PreparedStatement ps = cons.prepareStatement(sql)) {

            ps.setInt(1, maHP);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
