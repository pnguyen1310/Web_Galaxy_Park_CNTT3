import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HangXuatKhau extends SanPham implements MyInterface{
    private String xuatXu;
    private List<HangXuatKhau> danhSachSPXX = new ArrayList<>();
    List<HangXuatKhau> selectedProducts = new ArrayList<>();

    private int quantity;
    Scanner scanner = new Scanner(System.in);

    public HangXuatKhau() {
        // Khởi tạo danh sách sản phẩm xuất khẩu với 5 sản phẩm
        HangXuatKhau HangXuatKhau1 = new HangXuatKhau(1, "Áo khoác", 50000, 30, "Trung Quốc");
        HangXuatKhau HangXuatKhau2 = new HangXuatKhau(2, "Giày thể thao", 100000, 20, "Hàn Quốc");
        HangXuatKhau HangXuatKhau3 = new HangXuatKhau(3, "Điện thoại di động", 14000000, 10, "Nhật Bản");
        HangXuatKhau HangXuatKhau4 = new HangXuatKhau(4, "Máy tính xách tay", 20000000, 15, "Mỹ");
        HangXuatKhau HangXuatKhau5 = new HangXuatKhau(5, "Đồng hồ thông minh", 10000000, 25, "Trung Quốc");
        danhSachSPXX.add(HangXuatKhau1);
        danhSachSPXX.add(HangXuatKhau2);
        danhSachSPXX.add(HangXuatKhau3);
        danhSachSPXX.add(HangXuatKhau4);
        danhSachSPXX.add(HangXuatKhau5);
    }

    public HangXuatKhau(int id, String tenSanPham, double gia, int soLuong, String xuatXu) {
        super(id, tenSanPham, gia, soLuong);
        this.xuatXu = xuatXu;
    }

    public String getXuatXu() {
        return xuatXu;
    }
    public int getQuantity(){
        return quantity;
    }


    public void themSanPham() {
        System.out.println("Nhập id, giá, tên sản phẩm, số lượng và xuất xứ (cách nhau bằng dấu cách): ");
        try{
        int id = scanner.nextInt();
        double gia = scanner.nextDouble();
        scanner.nextLine();
        String tenSanPham = scanner.nextLine();
        int soLuong = scanner.nextInt();
        scanner.nextLine();
        String xuatXu = scanner.nextLine();

        HangXuatKhau hangxk = new HangXuatKhau(id, tenSanPham, gia, soLuong, xuatXu);
        danhSachSPXX.add(hangxk);
        }
        catch (InputMismatchException e){
            System.out.println("Nhập sai định dạng");
            themSanPham();
        }
    }


    public void xoaSanPham(int id) {
        for (int i = 0; i < danhSachSPXX.size(); i++) {
            if (danhSachSPXX.get(i).getId() == id) {
                danhSachSPXX.remove(i);
                System.out.println("Sản phẩm có ID " + id + " đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm với ID " + id + " để xóa.");
    }

    public List<HangXuatKhau> muaSanpham() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Danh sách sản phẩm hàng xuất khẩu:");
            for (SanPham product : danhSachSPXX) {
                product.printout();
            }
            System.out.println("Nhập ID sản phẩm bạn muốn mua (hoặc nhập 'exit' để kết thúc mua hàng): ");
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int selectedProductId = Integer.parseInt(userInput);
                HangXuatKhau selectedProduct = null;

                for (HangXuatKhau product : danhSachSPXX) {
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
                        System.out.println("Bạn đã mua " + quantity + " sản phẩm " + selectedProduct.getTenSanPham() + " Xuất xứ từ " + selectedProduct.getXuatXu() + " với tổng giá là: $" + productCost);

                        totalCost += productCost;

                        // Tạo một bản sao của selectedProduct để lưu vào danh sách
                        HangXuatKhau purchasedProduct = new HangXuatKhau(selectedProduct.getId(), selectedProduct.getTenSanPham(), selectedProduct.getGia(), quantity, selectedProduct.getXuatXu());
                        selectedProducts.add(purchasedProduct);

                        // Giảm số lượng của sản phẩm trong kho
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
        System.out.println("Xuất xứ: " + xuatXu);
        System.out.println();
    }

    @Override
    public void inhoadon() {

        System.out.println("Danh sách sản phẩm trong hóa đơn:");
        for (HangXuatKhau purchasedProduct : selectedProducts) {
            System.out.println("ID: " + purchasedProduct.getId() + ", Tên sản phẩm: " + purchasedProduct.getTenSanPham() +
                    ", Giá: " + purchasedProduct.getGia() + ", Số lượng: " + purchasedProduct.getSoLuong() + ", Xuất xứ : " + purchasedProduct.getXuatXu());
        }
        System.out.println("Tổng giá trước khi giảm giá: " + totalCost);

    }
}
