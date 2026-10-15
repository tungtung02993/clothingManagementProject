import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== SALES MANAGEMENT =====");
            System.out.println("1. Add product");
            System.out.println("2. Add customer");
            System.out.println("3. View products");
            System.out.println("4. View customers");
            System.out.println("5. Create order");
            System.out.println("6. View orders");
            System.out.println("7. View total revenue");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    addCustomer();
                    break;
                case 3:
                    viewProducts();
                    break;
                case 4:
                    viewCustomers();
                    break;
                case 5:
                    createOrder();
                    break;
                case 6:
                    viewOrders();
                    break;
                case 7:
                    viewRevenue();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    public static void addProduct() {
        try {
            System.out.print("Product ID: ");
            String id = sc.nextLine();

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId().equalsIgnoreCase(id)) {
                    System.out.println("Product ID already exists!");
                    return;
                }
            }

            System.out.print("Product name: ");
            String name = sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            System.out.print("Size: ");
            String size = sc.nextLine();

            System.out.print("Color: ");
            String color = sc.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Quantity: ");
            int quantity = Integer.parseInt(sc.nextLine());

            Product product = new Product(
                    id, name, category, size, color, price, quantity
            );

            products.add(product);
            System.out.println("Product added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addCustomer() {
        try {
            System.out.print("Customer ID: ");
            String id = sc.nextLine();

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCustomerId().equalsIgnoreCase(id)) {
                    System.out.println("Customer ID already exists!");
                    return;
                }
            }

            System.out.print("Customer name: ");
            String name = sc.nextLine();

            System.out.print("Membership (Regular/VIP): ");
            String membership = sc.nextLine();

            Customer customer = new Customer(id, name, membership);

            customers.add(customer);
            System.out.println("Customer added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        System.out.println("\n===== PRODUCT LIST =====");

        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));

            if (products.get(i).isLowStock()) {
                System.out.println("Warning: Low stock!");
            }
        }
    }

    public static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("\n===== CUSTOMER LIST =====");

        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
    }

    public static void createOrder() {
        if (customers.isEmpty() || products.isEmpty()) {
            System.out.println("Please add products and customers first.");
            return;
        }

        try {
            System.out.print("Transaction ID: ");
            String transactionId = sc.nextLine();

            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getTransactionId()
                        .equalsIgnoreCase(transactionId)) {
                    System.out.println("Transaction ID already exists!");
                    return;
                }
            }

            System.out.print("Order date (e.g. 2026-10-15): ");
            String date = sc.nextLine();

            System.out.println("\n===== CUSTOMER LIST =====");
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(customers.get(i));
            }

            System.out.print("Enter customer ID: ");
            String customerId = sc.nextLine();

            Customer customer = null;

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCustomerId()
                        .equalsIgnoreCase(customerId)) {
                    customer = customers.get(i);
                    break;
                }
            }

            if (customer == null) {
                System.out.println("Customer not found!");
                return;
            }

            Order order = new Order(transactionId, date, customer);

            int addMore;

            do {
                viewProducts();

                System.out.print("Enter product ID: ");
                String productId = sc.nextLine();

                Product product = null;

                for (int i = 0; i < products.size(); i++) {
                    if (products.get(i).getProductId()
                            .equalsIgnoreCase(productId)) {
                        product = products.get(i);
                        break;
                    }
                }

                if (product == null) {
                    System.out.println("Product not found!");
                } else {
                    System.out.print("Quantity to buy: ");
                    int quantity = Integer.parseInt(sc.nextLine());

                    OrderDetail detail = new OrderDetail(product, quantity);
                    order.addOrderDetail(detail);

                    System.out.println("Product added to order.");
                }

                System.out.print("Add another product? (1 = Yes, 0 = No): ");
                addMore = Integer.parseInt(sc.nextLine());

            } while (addMore == 1);

            if (order.getOrderDetails().isEmpty()) {
                System.out.println("Order was not created because it is empty.");
                return;
            }

            System.out.println("\n===== ORDER SUMMARY =====");
            System.out.println("Subtotal: " + order.calculateSubtotal());
            System.out.println("Discount: " + order.calculateDiscount());
            System.out.println("Final amount: " + order.calculateFinalAmount());

            System.out.print("Complete this order? (1 = Yes, 0 = No): ");
            int confirm = Integer.parseInt(sc.nextLine());

            if (confirm == 1) {
                order.completeOrder();
                orders.add(order);
                System.out.println("Order completed successfully!");
            } else {
                System.out.println("Order was not completed.");
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No completed orders.");
            return;
        }

        System.out.println("\n===== COMPLETED ORDERS =====");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);

            System.out.println("Transaction ID: " + order.getTransactionId());
            System.out.println("Date: " + order.getDate());
            System.out.println("Customer: " + order.getCustomer().getName());
            System.out.println("Subtotal: " + order.calculateSubtotal());
            System.out.println("Discount: " + order.calculateDiscount());
            System.out.println("Final amount: " + order.calculateFinalAmount());
            System.out.println("----------------------------");
        }
    }

    public static void viewRevenue() {
        double totalRevenue = 0;

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).isCompleted()) {
                totalRevenue += orders.get(i).calculateFinalAmount();
            }
        }

        System.out.println("Total revenue: " + totalRevenue);
    }
}

