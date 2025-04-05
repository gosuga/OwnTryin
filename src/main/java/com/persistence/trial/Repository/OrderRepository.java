package com.persistence.trial.Repository;

import com.persistence.trial.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Long> {

}
