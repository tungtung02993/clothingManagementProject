package repository;

import entities.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderRepository class handles the storage and retrieval of Order objects.
 */
public class OrderRepository {
    // Private attributes
    private List<Order> orders;

    // Public methods
    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public void saveOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public List<Order> findAll() {
        return this.orders;
    }

    public Order findById(String id) {
        for (Order order : orders) {
            if (order.getTransactionId().equalsIgnoreCase(id)) {
                return order;
            }
        }
        return null;
    }
}
