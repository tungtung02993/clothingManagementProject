public class Product {
    private final String productId;
    private String name;
    private String category;
    private String size;
    private String color;
    private double price;
    private int quantity;

    public Product(String productId, String name, String category,
                   String size, String color, double price, int quantity) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()
                || category == null || category.trim().isEmpty()
                || size == null || size.trim().isEmpty()
                || color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Product information cannot be empty.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        this.productId = productId;
        this.name = name;
        this.category = category;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isLowStock() {
        return quantity <= 5;
    }

    public void reduceStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock.");
        }

        quantity = quantity - amount;
    }

    @Override
    public String toString() {
        return productId + " - " + name
                + " - Price: " + price
                + " - Quantity: " + quantity;
    }
}
