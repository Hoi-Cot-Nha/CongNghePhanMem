package Model;

import java.util.Date;

public class HocSinh {
    private String maHS;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String maLop;
    private String maDT;
    private String sdt;
    private String tenPhuHuynh;
    private String cccd;

    public HocSinh() {}

    public HocSinh(String maHS, String hoTen, String ngaySinh, String gioiTinh, String diaChi, String maLop, String maDT, String sdt, String tenPhuHuynh, String cccd) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.maLop = maLop;
        this.maDT = maDT;
        this.sdt = sdt;
        this.tenPhuHuynh = tenPhuHuynh;
        this.cccd = cccd;
    }

    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaDT() {
        return maDT;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenPhuHuynh() {
        return tenPhuHuynh;
    }

    public void setTenPhuHuynh(String tenPhuHuynh) {
        this.tenPhuHuynh = tenPhuHuynh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    
}
