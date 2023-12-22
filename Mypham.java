import java.util.ArrayList;
import java.util.List;
public class Mypham extends SanPham{
    private int id;
    private String ten;
    private double gia;
    private int soluong;
    private String nhanHang;
    private String mua;
    private List<SanPham> danhsachSanPham = new ArrayList<>();

    public static  Mypham matna = new Mypham(1, "Mặt nạ", 20000, 20,"Dior","Mùa đông");
    public static  Mypham son_duong = new Mypham(2, "Son dưỡng", 30000, 30,"Gucci","Mùa hè");
    public static  Mypham mascara = new Mypham(3, "Mascara", 40000, 50,"Maybeline","Mùa xuân");
    public static  Mypham phan_nen = new Mypham(4, "Phấn nền", 35000, 60,"Too Faced","Mùa thu");


    public Mypham(int id, String tenSanPham, double gia, int soLuong, String nhanhang, String mua) {
       super(id, tenSanPham, gia, soLuong);
       this.nhanHang = nhanHang;
       this.mua = mua;
    }


    public int getId() {
        return id;
    }

    public double getGia() {
        return gia;
    }

    public String getNhanHang() {
        return nhanHang;
    }

    public String getMua() {
        return mua;
    }

    // Phương thức in thông tin của sản phẩm
    public void printout() {
        System.out.println("ID: " + id);
        System.out.println("Giá: " + gia);
        System.out.println("Nhãn hàng: " + nhanHang);
        System.out.println("Mùa: " + mua);
    }



}
