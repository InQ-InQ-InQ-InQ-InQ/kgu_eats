package kgueats.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.OrderMenuHistory;

@Repository
public interface OrderMenuHistoryRepository extends JpaRepository<OrderMenuHistory, Long> {

}
