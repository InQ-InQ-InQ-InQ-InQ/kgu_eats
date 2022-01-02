package kgueats.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
