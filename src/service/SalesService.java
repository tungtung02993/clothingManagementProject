package service;

import entities.Customer;
import entities.Order;
import entities.OrderDetail;
import entities.Product;
import repository.OrderRepository;
import repository.ProductRepository;

/**
 * SalesService handles the business logic for creating and processing sales transactions.
 */
public class SalesService {
    // Private attributes (Chấm đỏ)
    private ProductRepository productRepo;
    private OrderRepository orderRepo;

    // Public methods (Chấm xanh)
    public SalesService(ProductRepository productRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Order createTransaction(String transactionId, String date, Customer customer) {
        return new Order(transactionId, date, customer);
    }

    public boolean addProductToTransaction(Order order, String productId, int quantity) {
        if (order == null || quantity <= 0) {
            return false;
        }

        Product product = productRepo.findById(productId);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + productId);
            return false;
        }

        if (product.getQuantity() < quantity) {
            System.out.println("Sản phẩm " + product.getName() + " không đủ số lượng trong kho!");
            return false;
        }

        OrderDetail detail = new OrderDetail(product, quantity);
        order.addOrderDetail(detail);
        return true;
    }

    public boolean confirmSale(Order order) {
        if (order == null || order.getOrderDetails().isEmpty()) {
            System.out.println("Giao dịch không hợp lệ hoặc danh sách sản phẩm rỗng!");
            return false;
        }

        // Trừ số lượng tồn kho của từng sản phẩm trong hóa đơn
        for (OrderDetail detail : order.getOrderDetails()) {
            Product product = detail.getProduct();
            product.reduceStock(detail.getQuantity());
            productRepo.updateProduct(product);
        }

        // Lưu hóa đơn vào danh sách / CSDL
        orderRepo.saveOrder(order);
        System.out.println("Xác nhận thanh toán thành công cho đơn hàng: " + order.getTransactionId());
        return true;
    }
}
