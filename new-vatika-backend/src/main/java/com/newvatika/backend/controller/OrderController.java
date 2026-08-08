package com.newvatika.backend.controller;

import com.newvatika.backend.entity.Order;
import com.newvatika.backend.entity.OrderStatus;
import com.newvatika.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {


    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestBody Order order
    ) {

        return ResponseEntity.ok(
                orderService.placeOrder(order)
        );
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAll()
        );
    }


    @GetMapping(params = "status")
    public ResponseEntity<List<Order>> getByStatus(
            @RequestParam OrderStatus status
    ) {

        return ResponseEntity.ok(
                orderService.getAll()
                        .stream()
                        .filter(order ->
                                order.getStatus().equals(status))
                        .toList()
        );
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {

        return ResponseEntity.ok(
                orderService.updateStatus(id, status)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id
    ) {

        orderService.delete(id);

        return ResponseEntity.ok(
                "Order deleted successfully"
        );
    }
}