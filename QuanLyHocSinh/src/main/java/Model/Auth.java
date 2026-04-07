package Model;

public class Auth {

    public static String currentUser = "";
    public static String currentRole = "";
    public static String maNguoiDung = "";

    public static void clear() {
        currentUser = "";
        currentRole = "";
        maNguoiDung = "";
    }

    public static boolean isLogin() {
        return currentUser != null && !currentUser.isEmpty();
    }

    // Chuẩn hóa role
    private static String role() {
        return currentRole == null ? "" : currentRole.trim().toLowerCase();
    }

    public static boolean isAdmin() {
        return role().equals("admin");
    }

    public static boolean isGiaoVien() {
        return role().equals("giaovien")
                || role().equals("giao vien")
                || role().equals("giáo viên");
    }

    public static boolean isHocSinh() {
        return role().equals("hocsinh")
                || role().equals("hoc sinh")
                || role().equals("học sinh");
    }
}