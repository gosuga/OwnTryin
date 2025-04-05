package com.persistence.trial.OrderController;

import com.persistence.trial.model.OrderItem;
import com.persistence.trial.service.Interface.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public OrderItem saveOrder(@RequestBody OrderItem orderItem) {
        return orderService.SaveOrderItem(orderItem);
    }

    @GetMapping("/{id}")
    public OrderItem getTheorderbyId(@PathVariable Long id) {
        return orderService.getOrderItemById(id);
    }


    @GetMapping
    public List<OrderItem>getAllTheorder(){
        return  orderService.getAllOrder();
    }

    @PutMapping("/{id}")
    public OrderItem orderItem(@PathVariable Long id,@RequestBody OrderItem orderItem) {
        return orderService.UpdateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheOrder(@PathVariable Long id) {

        if (!orderService.DeleteOrderItemById(id)) {
            throw new IllegalArgumentException("Invalid order id" + id);
        }

        orderService.DeleteOrderItemById(id);
        return ResponseEntity.accepted().build();

    }


}
