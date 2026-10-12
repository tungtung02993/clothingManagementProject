package util;

public class ValidUtils {
    // BR3 & BR4: Kiểm tra chuỗi rỗng
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // BR5: Kiểm tra giá sản phẩm (> 0)
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // BR6: Kiểm tra số lượng tồn kho (>= 0)
    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }
}
