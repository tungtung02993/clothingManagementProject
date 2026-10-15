package ui;

import entities.Customer;
import entities.Order;
import entities.Product;
import repository.OrderRepository;
import repository.ProductRepository;
import service.ReportService;
import service.SalesService;

import java.util.List;
import java.util.Scanner;

public class MainReport {
    // Chấm đỏ = private
    private Scanner scanner;
    private OrderRepository orderRepo;
    private ProductRepository productRepo;
    private SalesService salesService;
    private ReportService reportService;

    // Chấm xanh = public
    public MainReport() {
        this.scanner = new Scanner(System.in);
        this.orderRepo = new OrderRepository();
        this.productRepo = new ProductRepository(); // Giả định bạn đã có class này
        
        this.salesService = new SalesService(productRepo, orderRepo);
        this.reportService = new ReportService(orderRepo, productRepo);
        
        // Load dữ liệu mẫu ở đây (nếu cần test)
        // initDummyData(); 
    }

    public void start() {
        int choice = -1;
        do {
            System.out.println("\n===== CLOTHING STORE MANAGEMENT =====");
            System.out.println("1. Tạo giao dịch bán hàng (Create Sale)");
            System.out.println("2. Báo cáo doanh thu tháng (Monthly Report)");
            System.out.println("3. Top sản phẩm bán chạy (Best Selling Products)");
            System.out.println("4. Top khách hàng chi tiêu cao (Highest Spending)");
            System.out.println("0. Thoát (Exit)");
            System.out.print("Chọn chức năng: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleCreateTransaction();
                    break;
                case 2:
                    handleMonthlyReport();
                    break;
                case 3:
                    handleBestSellingProducts();
                    break;
                case 4:
                    handleHighestSpendingCustomers();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        } while (choice != 0);
    }

    private void handleCreateTransaction() {
        System.out.println("\n--- TẠO GIAO DỊCH MỚI ---");
        System.out.print("Nhập mã giao dịch: ");
        String transId = scanner.nextLine();
        
        System.out.print("Nhập ngày giao dịch (VD: 15/10/2026): ");
        String date = scanner.nextLine();
        
        // Trong thực tế, bạn sẽ tìm Customer từ CustomerRepository.
        // Ở đây tạo tạm một Customer giả lập để test[cite: 1, 2]
        Customer customer = new Customer("C01", "Nguyen Van A", "0123456789", "HCM"); 
        
        Order order = salesService.createTransaction(transId, date, customer);
        
        boolean addMore = true;
        while (addMore) {
            System.out.print("Nhập mã sản phẩm cần mua: ");
            String pId = scanner.nextLine();
            System.out.print("Nhập số lượng: ");
            int qty = Integer.parseInt(scanner.nextLine());
            
            salesService.addProductToTransaction(order, pId, qty);
            
            System.out.print("Tiếp tục thêm sản phẩm? (Y/N): ");
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("N")) {
                addMore = false;
            }
        }
        
        salesService.confirmSale(order);
    }

    private void handleMonthlyReport() {
        System.out.println("\n--- BÁO CÁO DOANH THU THÁNG ---");
        System.out.print("Nhập tháng (1-12): ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập năm: ");
        int year = Integer.parseInt(scanner.nextLine());
        
        reportService.generateMonthlyReport(month, year);
    }

    private void handleBestSellingProducts() {
        System.out.println("\n--- SẢN PHẨM BÁN CHẠY NHẤT ---");
        List<Product> bestSellers = reportService.getBestSellingProducts();
        if (bestSellers.isEmpty()) {
            System.out.println("Chưa có dữ liệu bán hàng.");
        } else {
            for (Product p : bestSellers) {
                System.out.println("- " + p.getName() + " (ID: " + p.getId() + ")");
            }
        }
    }

    private void handleHighestSpendingCustomers() {
        System.out.println("\n--- KHÁCH HÀNG CHI TIÊU NHIỀU NHẤT ---");
        List<Customer> topCustomers = reportService.getHighestSpendingCustomers();
        if (topCustomers.isEmpty()) {
            System.out.println("Chưa có dữ liệu giao dịch.");
        } else {
            for (Customer c : topCustomers) {
                System.out.println("- " + c.getName() + " (Phone: " + c.getPhone() + ")");
            }
        }
    }

    public static void main(String[] args) {
        MainReport ui = new MainReport();
        ui.start();
    }
}