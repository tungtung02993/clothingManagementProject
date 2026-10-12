package entities;

public class Product {
    
    private String productID;
    private String productName;
    private String category;
    private String size;
    private String color ;
    private float price ;
    private int quantity;
    
    public Product(){
    }
    
    public Product(String ID, String name, 
                String category, String size, 
                String color, float price, int quantity){
        
        this.productID = ID;
        this.productName = name;
        this.category = category;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getter

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
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

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    
    //Setter
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void updateStock(int amount) {
        this.quantity = quantity - amount;
    }
    
    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s, %s, %.2f, %d", 
                productID, productName, category, size, color,
                price, quantity);
    }
            
}
