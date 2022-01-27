package kgueats.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.OrderHistoryUnit;

@Repository
public interface OrderHistoryUnitRepository extends JpaRepository<OrderHistoryUnit, Long> {

}
