package com.persistence.trial.service.Implementation;

import com.persistence.trial.Repository.OrderRepository;
import com.persistence.trial.model.OrderItem;
import com.persistence.trial.service.Interface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderItem> getAllOrder() {
        return orderRepository.findAll();

    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }



    @Override
    public OrderItem SaveOrderItem(OrderItem orderItem) {
        return orderRepository.save(orderItem);
    }

   @Override
    public OrderItem UpdateOrderItem(Long id, OrderItem orderItemss) {

        Optional<OrderItem> orderItems = orderRepository.findById(id);

        if (orderItems.isPresent()) {
            OrderItem orderItem1 = orderItems.get();


            orderItem1  .setProductQuantity(orderItemss.getProductQuantity());
            orderItem1.setProductName(orderItemss.getProductName());


            return orderRepository.save(orderItem1);

        }
        return null;
    }

    @Override
    public boolean DeleteOrderItemById(Long id) {
        orderRepository.deleteById(id);

        return false;
    }
}
