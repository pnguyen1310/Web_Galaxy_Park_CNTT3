import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangBanhKeo extends SanPham {
    private String loai;
    private List<HangBanhKeo> danhSachSPBK = new ArrayList<>();
    List<HangBanhKeo> selectedProducts = new ArrayList<>();
    private double temp = 0.0;
    private int quantity;
    Scanner scanner = new Scanner(System.in);

    public HangBanhKeo() {
        // Khởi tạo danh sách sản phẩm bánh kẹo với 5 sản phẩm
        HangBanhKeo HangBanhKeo1 = new HangBanhKeo(1, "Bánh quy hạt dẻ cười", 20000, 50, "Bánh quy");
        HangBanhKeo HangBanhKeo2 = new HangBanhKeo(2, "Bánh bông lan trứng muối", 30000, 50, "Bánh bông lan");
        HangBanhKeo HangBanhKeo3 = new HangBanhKeo(3, "Bánh xốp Nabati", 10000, 50, "Bánh xốp");
        HangBanhKeo HangBanhKeo4 = new HangBanhKeo(4, "Bánh gạo vị cốm sữa Richy Jinju", 26500, 50, "Bánh gạo");
        HangBanhKeo HangBanhKeo5 = new HangBanhKeo(5, "Rainbow Macaron", 50000, 50, "Bánh ngọt");
        danhSachSPBK.add(HangBanhKeo1);
        danhSachSPBK.add(HangBanhKeo2);
        danhSachSPBK.add(HangBanhKeo3);
        danhSachSPBK.add(HangBanhKeo4);
        danhSachSPBK.add(HangBanhKeo5);
    }

    public HangBanhKeo(int id, String tenSanPham, double gia, int soLuong, String loai) {
        super(id, tenSanPham, gia, soLuong);
        this.loai = loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public List<HangBanhKeo> muaSanpham() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Danh sách sản phẩm hàng xuất khẩu:");
            for (SanPham product : danhSachSPBK) {
                product.printout();
            }
            System.out.println("Nhập ID sản phẩm bạn muốn mua (hoặc nhập 'exit' để kết thúc mua hàng): ");
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int selectedProductId = Integer.parseInt(userInput);
                HangBanhKeo selectedProduct = null;

                for (HangBanhKeo product : danhSachSPBK) {
                    if (product.getId() == selectedProductId) {
                        selectedProduct = product;
                        break;
                    }
                }

                if (selectedProduct != null) {
                    System.out.println("Nhập số lượng bạn muốn mua: ");
                    int quantity = scanner.nextInt();
                    if (quantity <= 0) {
                        System.out.println("Số lượng không hợp lệ. Vui lòng nhập số lượng hợp lệ.");
                        continue;
                    }

                    if (quantity > selectedProduct.getSoLuong()) {
                        System.out.println("Sản phẩm này không đủ số lượng trong kho. Vui lòng chọn số lượng ít hơn.");
                    } else {
                        double productCost = selectedProduct.getGia() * quantity;
                        System.out.println("Bạn đã mua " + quantity + " sản phẩm " + selectedProduct.getTenSanPham() + " với tổng giá là: $" + productCost);

                        totalCost += productCost;


                        HangBanhKeo purchasedProduct = new HangBanhKeo();
                        selectedProducts.add(purchasedProduct);


                        selectedProduct.setSoLuong(selectedProduct.getSoLuong() - quantity);
                    }
                } else {
                    System.out.println("Không tìm thấy sản phẩm với ID " + selectedProductId);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID sản phẩm không hợp lệ. Vui lòng nhập ID hợp lệ hoặc 'exit' để kết thúc mua hàng.");
            }
        }

        inhoadon();
        return selectedProducts;
    }

    @Override
    public void printout() {
        super.printout(); // Gọi phương thức in ra từ lớp cha (SanPham)
        System.out.println("Loại: " + loai);
        System.out.println();
    }

    @Override
    public void inhoadon() {

        System.out.println("Danh sách sản phẩm trong hóa đơn:");
        for (HangBanhKeo purchasedProduct : selectedProducts) {
            System.out.println("ID: " + purchasedProduct.getId() + ", Tên sản phẩm: " + purchasedProduct.getTenSanPham() +
                    ", Giá: " + purchasedProduct.getGia() + ", Số lượng: " + purchasedProduct.getSoLuong() + ", Loại: " + purchasedProduct.getLoai());
        }
        System.out.println("Tổng giá trước khi giảm giá: " + totalCost);

    }
}
