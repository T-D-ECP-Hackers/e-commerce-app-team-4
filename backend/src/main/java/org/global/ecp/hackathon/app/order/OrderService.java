package org.global.ecp.hackathon.app.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.email.EmailService;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.order.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    // TODO - Task 9: implement this method
    public UUID createOrder(final OrderRequest orderRequest) {

        UUID randomUUID = UUID.randomUUID();

        LocalDateTime orderDateTime = LocalDateTime.now();

        Order order = new Order(randomUUID, orderDateTime, orderRequest.getTotalCost(), orderRequest.getBasket().getBasketProducts());

        order.complete();

        if (orderRequest.getBasket().getBasketProducts() == null) {
            return null;
        }

        orderRepository.addOrder(order);

        emailService.sendEmail(order);

        return order.getId();

    }

    public List<Order> getAllOrders() {

        return orderRepository.getAll();
    }

    public void completeOrder(UUID orderId) {
        Order order = orderRepository.getById(orderId);

        if (order == null) {
            log.warn("Order with ID {} does not exist.", orderId);
            return;
        }

        if (order.isCompleted()) {
            log.warn("Order with ID {} has already been completed.", orderId);
            return;
        }

        order.complete();
    }
}
