package com.persistence.trial.service.Interface;

import com.persistence.trial.model.OrderItem;

import java.util.List;

public interface OrderService {

    List<OrderItem> getAllOrder();

    OrderItem getOrderItemById(Long id);

   // Order getAllOrderId();

    OrderItem SaveOrderItem(OrderItem orderItem);

   // OrderItem UpdateOrderItem(Long id, OrderItem orderItem);

    OrderItem UpdateOrderItem(Long id, OrderItem orderItemss);

    boolean DeleteOrderItemById(Long id);

}
