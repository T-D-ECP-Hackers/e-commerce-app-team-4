package org.global.ecp.hackathon.app.order.web;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.order.OrderService;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.order.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order Endpoint", description = "Create product orders and list all orders that were made.")
@Slf4j
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody final OrderRequest orderRequest) {

        UUID order = orderService.createOrder(orderRequest);
        if (order == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> completeOrder(@RequestParam("orderId") UUID orderId) {
        orderService.completeOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

