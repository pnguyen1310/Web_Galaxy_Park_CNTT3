import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class SanPham extends MyAbstractClass implements MyInterface {
    private int id;
    private String tenSanPham;
    private double gia;

    private int soLuong;

    double totalCost = 0.0;

    public SanPham(int id, String tenSanPham, double gia, int soLuong) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public SanPham() {

    }

    // Getter methods for fields
    public int getId() {
        return id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    List<SanPham> sanPhamList = new ArrayList<>();
    List<SanPham> selectedProducts = new ArrayList<>();

    public List<SanPham> getAllSanPham() {


        try {
            // Establish a database connection
            Connection connection = null;
            connection = DatabaseConnection.getConnection();
            // Prepare and execute SQL query
            String query = "SELECT * FROM sanpham";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Process the query result and print product details
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                tenSanPham = resultSet.getString("ten_sanpham");
                gia = resultSet.getDouble("gia");
                soLuong = resultSet.getInt("so_luong");
                SanPham sanPham = new SanPham(id, tenSanPham, gia, soLuong);
                sanPhamList.add(sanPham);
            }

            // Close resources
        } catch (Exception e) {
            e.printStackTrace();

        }
        return sanPhamList;
    }

    public void printout() {
        System.out.println("ID: " + id);
        System.out.println("Tên Sản Phẩm: " + tenSanPham);
        System.out.println("Số lượng: "+soLuong);
        System.out.println("Giá: " + gia);
    }

    public List<SanPham> chonSanpham() {
        Scanner scanner = new Scanner(System.in);


        boolean lam = true;
        List<SanPham> sanPhamList = getAllSanPham();

            while (lam) {
            System.out.println("Danh sách sản phẩm có sẵn:");
            for (SanPham product : sanPhamList) {
                product.printout();
            }

            int trueProductId;
            int quantity;
            boolean validInput = false;
            SanPham selectedProduct = null;

            while (!validInput) {
                System.out.print("Nhập ID sản phẩm bạn muốn mua (hoặc nhập exit để kết thúc mua hàng): ");
                String productIdStr = scanner.nextLine();

                if (productIdStr.equals("exit")) {
                    lam = false; // Kết thúc mua hàng
                    break;
                }

                try {
                    trueProductId = Integer.parseInt(productIdStr);

                    // Check if trueProductId is a valid product ID
                    boolean productFound = false;
                    for (SanPham product : sanPhamList) {
                        if (product.getId() == trueProductId) {
                            selectedProduct = product;  // Store the selected product
                            productFound = true;
                            break;
                        }
                    }
                    if (!productFound) {
                        System.out.println("Sản phẩm không tồn tại. Vui lòng nhập lại.");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID sản phẩm phải là một số nguyên. Vui lòng nhập lại.");
                }
            }

            if (lam) {
                validInput = false; // Reset for quantity input

                while (!validInput) {
                    System.out.print("Nhập số lượng bạn muốn mua: ");
                    String quantityStr = scanner.nextLine();

                    try {
                        quantity = Integer.parseInt(quantityStr);
                        if (quantity > 0 && quantity <= selectedProduct.getSoLuong()) {

                            selectedProduct.setSoLuong(quantity);
                            validInput = true;
                        } else {
                            System.out.println("Số lượng không hợp lệ hoặc vượt quá số lượng tồn kho.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Số lượng phải là một số nguyên. Vui lòng nhập lại.");
                    }
                }

                selectedProducts.add(selectedProduct);

            }
        }
        inhoadon();
        return selectedProducts;
    }
    public static double deXuatMuaHang() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        double discount = 0.0;
        if (randomNumber < 20) {
            discount = 0.2;
        } else if (randomNumber < 50) {
            discount = 0.3;
        } else if (randomNumber < 80) {
            discount = 0.5;
        } else {
            discount = 0.7;
        }

        System.out.println("Chúc mừng bạn đã trúng giảm giá " + (discount * 100) + "% cho đơn hàng của bạn!");
        return discount;
    }


    public void inhoadon() {

        System.out.println("Danh sách sản phẩm trong hóa đơn:");
        for (SanPham product : selectedProducts) {
            System.out.println("ID: " + product.getId() + ", Tên sản phẩm: " + product.getTenSanPham() +
                    ", Giá: " + product.getGia() + ", Số lượng: " + product.getSoLuong());
            totalCost = product.getGia()* product.getSoLuong();

        }
        System.out.println("Tổng giá trước khi giảm giá: " +totalCost);
    }
}





