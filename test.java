import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập id khach hang : ");
        int CustomerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập ten khach hang: ");
        String CustomerName = scanner.nextLine();

        System.out.print("Nhập so du khach hang : ");
        int CustomerBalance = scanner.nextInt();

        // Gọi hàm để lưu sản phẩm vào cơ sở dữ liệu
        boolean result = saveProductToDatabase(CustomerId, CustomerName, CustomerBalance);

        if (result) {
            System.out.println("Sản phẩm đã được lưu vào cơ sở dữ liệu.");
        } else {
            System.out.println("Không thể lưu sản phẩm vào cơ sở dữ liệu.");
        }
    }

    public static boolean saveProductToDatabase(int CustomerId , String CustomerName, int CustomerBalance) {
        String jdbcURL = "jdbc:mysql://localhost:3306/cua_hang"; // Thay đổi thành URL của cơ sở dữ liệu thực tế
        String username = "root"; // Thay đổi thành tên đăng nhập của bạn
        String password = "choinhanh12"; // Thay đổi thành mật khẩu của bạn

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            String sql = "INSERT INTO khachhang (id, ho_ten, so_du) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, CustomerId);
            preparedStatement.setString(2, CustomerName);
            preparedStatement.setInt(3, CustomerBalance);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            return rowsAffected > 0; // Trả về true nếu có dòng bị ảnh hưởng (dữ liệu đã được thêm)

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
