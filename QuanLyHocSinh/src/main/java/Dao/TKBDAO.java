/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connection.ConnectDB;
import Model.TKB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */
public class TKBDAO {

    public List<TKB> getAll() {
    List<TKB> list = new ArrayList<>();
    String sql = "SELECT * FROM ThoiKhoaBieu";

    try (
        Connection c = ConnectDB.getConnection();
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            list.add(new TKB(
                rs.getInt("MaTKB"),
                rs.getString("MaLop"),
                rs.getString("MaMH"),
                rs.getString("MaGV"),
                rs.getString("MaPhong"),
                rs.getInt("Thu"),
                rs.getInt("TietBatDau"),
                rs.getInt("TietKetThuc")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


        public List<TKB> getByLop(String maLop) {
        List<TKB> list = new ArrayList<>();
        String sql = "SELECT * FROM ThoiKhoaBieu WHERE MaLop = ?";

        try (
            Connection c = ConnectDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, maLop);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new TKB(
                    rs.getInt("MaTKB"),
                    rs.getString("MaLop"),
                    rs.getString("MaMH"),
                    rs.getString("MaGV"),
                    rs.getString("MaPhong"),
                    rs.getInt("Thu"),
                    rs.getInt("TietBatDau"),
                    rs.getInt("TietKetThuc")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean isTrungPhongTiet(TKB t) {
        String sql = """
            SELECT COUNT(*) FROM ThoiKhoaBieu
            WHERE MaPhong = ?
              AND Thu = ?
              AND NOT (TietKetThuc < ? OR TietBatDau > ?)
        """;

        try (
            Connection c = ConnectDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, t.getMaPhong());
            ps.setInt(2, t.getThu());
            ps.setInt(3, t.getTietBatDau());
            ps.setInt(4, t.getTietKetThuc());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void insert(TKB t) {
        String sql = """
            INSERT INTO ThoiKhoaBieu
            (MaLop, MaMH, MaGV, MaPhong, Thu, TietBatDau, TietKetThuc)
            VALUES (?,?,?,?,?,?,?)
        """;

        try (
            Connection c = ConnectDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, t.getMaLop());
            ps.setString(2, t.getMaMH());
            ps.setString(3, t.getMaGV());
            ps.setString(4, t.getMaPhong());
            ps.setInt(5, t.getThu());
            ps.setInt(6, t.getTietBatDau());
            ps.setInt(7, t.getTietKetThuc());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ThoiKhoaBieu WHERE MaTKB = ?";

        try (
            Connection c = ConnectDB.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
