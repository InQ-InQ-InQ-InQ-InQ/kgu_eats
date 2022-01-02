package kgueats.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.OrderUnit;

@Repository
public interface OrderUnitRepository extends JpaRepository<OrderUnit, Long> {

}
