package org.global.ecp.hackathon.app.order;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.product.model.Product;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    private final Map<UUID, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }
    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }
    public List<Order> getAll() {

        return orders.values().stream().toList();
    }
    public Order getById(UUID id) {
        return orders.get(id);
    }
    // TODO - Task 12: implement the getById method here
}
