import java.util.List;

public class HoaDon {



    public static double processPayment(List<SanPham> selectedProducts) {
        double totalCost = 0.0;

        System.out.println("Danh sách sản phẩm trong hóa đơn:");
        for (SanPham product : selectedProducts) {
            System.out.println("ID: " + product.getId() + ", Tên sản phẩm: " + product.getTenSanPham() +
                    ", Giá: " + product.getGia() + ", Số lượng: " + product.getSoLuong());

            totalCost += product.getGia() * product.getSoLuong();
        }

        double discount = SanPham.deXuatMuaHang();
        double discountedCost = totalCost * (1 - discount);

        System.out.println("Tổng giá trước khi giảm giá: " + totalCost);
        System.out.println("Tổng giá sau khi giảm giá (" + discount * 100 + "%): là " + discountedCost);

        return discountedCost;

}}