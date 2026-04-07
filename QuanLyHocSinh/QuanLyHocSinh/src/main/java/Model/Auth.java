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

    // Chuẩn hóa role về chữ thường
    private static String role() {
        return currentRole == null ? "" : currentRole.toLowerCase().trim();
    }

    public static boolean isAdmin() {
        return role().equals("admin");
    }

    public static boolean isGiaoVien() {
        return role().contains("giáo viên") || role().contains("giao vien");
    }

    public static boolean isHocSinh() {
        String role = role(); // dùng hàm đã có

        return role.equals("hocsinh")   // ✅ FIX CHÍNH
                || role.equals("hoc sinh")
                || role.contains("học sinh");
    }
}