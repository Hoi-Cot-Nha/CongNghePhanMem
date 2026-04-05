// File này giúp debug kết nối database
// Chạy file này để kiểm tra xem kết nối database có hoạt động không

package Dao;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestHocphiDAO {
    
    public static void main(String[] args) {
        testConnection();
        testLopData();
        testHocphiData();
    }
    
    public static void testConnection() {
        System.out.println("\n=== TEST 1: Kiểm tra kết nối database ===");
        try (Connection cons = ConnectDB.getConnection()) {
            if (cons != null) {
                System.out.println("✓ Kết nối thành công!");
                System.out.println("Database: QuanLyHocSinh");
                System.out.println("Server: localhost:1433");
            } else {
                System.out.println("✗ Kết nối thất bại (con là null)");
            }
        } catch (Exception e) {
            System.out.println("✗ Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void testLopData() {
        System.out.println("\n=== TEST 2: Kiểm tra dữ liệu bảng Lop ===");
        String sql = "SELECT COUNT(*) as total FROM Lop";
        try (Connection cons = ConnectDB.getConnection();
             PreparedStatement ps = cons.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("total");
                System.out.println("✓ Tổng số lớp: " + count);
                
                if (count == 0) {
                    System.out.println("⚠ Cảnh báo: Bảng Lop không có dữ liệu!");
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void testHocphiData() {
        System.out.println("\n=== TEST 3: Kiểm tra dữ liệu bảng HocPhi ===");
        String sql = "SELECT COUNT(*) as total FROM HocPhi";
        try (Connection cons = ConnectDB.getConnection();
             PreparedStatement ps = cons.prepareStatement(sql)) {
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("total");
                System.out.println("✓ Tổng số học phí: " + count);
                
                if (count == 0) {
                    System.out.println("⚠ Cảnh báo: Bảng HocPhi không có dữ liệu!");
                }
            }
        } catch (Exception e) {
            System.out.println("✗ Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
