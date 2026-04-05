package Model;

public class Diem {
    private String maHS;
    private String tenHS; 
    private String maMH;
    private int hocKy;
    

    private double diemMieng;  
    private double diem15p;  
    private double diem1Tiet;
    private double diemThi;    

    public Diem() {}

    public Diem(String maHS, String maMH, int hocKy, double diemMieng, double diem15p, double diem1Tiet, double diemThi) {
        this.maHS = maHS;
        this.maMH = maMH;
        this.hocKy = hocKy;
        this.diemMieng = diemMieng;
        this.diem15p = diem15p;
        this.diem1Tiet = diem1Tiet;
        this.diemThi = diemThi;
    }

    public String getMaHS() { return maHS; }
    public void setMaHS(String maHS) { this.maHS = maHS; }

    public String getTenHS() { return tenHS; }
    public void setTenHS(String tenHS) { this.tenHS = tenHS; }

    public String getMaMH() { return maMH; }
    public void setMaMH(String maMH) { this.maMH = maMH; }

    public int getHocKy() { return hocKy; }
    public void setHocKy(int hocKy) { this.hocKy = hocKy; }


    public double getDiemMieng() { return diemMieng; }
    public void setDiemMieng(double diemMieng) { this.diemMieng = diemMieng; }

    public double getDiem15p() { return diem15p; }
    public void setDiem15p(double diem15p) { this.diem15p = diem15p; }

    public double getDiem1Tiet() { return diem1Tiet; }
    public void setDiem1Tiet(double diem1Tiet) { this.diem1Tiet = diem1Tiet; }

    public double getDiemThi() { return diemThi; }
    public void setDiemThi(double diemThi) { this.diemThi = diemThi; }

    // TÍNH ĐIỂM TRUNG BÌNH (Công thức chuẩn: Miệng + 15p + 1Tiết*2 + Thi*3 chia 7)
    public double getDiemTB() {
        return (diemMieng + diem15p + diem1Tiet * 2 + diemThi * 3) / 7.0;
    }
}