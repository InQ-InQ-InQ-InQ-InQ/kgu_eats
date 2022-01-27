package kgueats.domain.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	Optional<Ticket> findByStudentIdAndMenuId(Long studentId, Long menuId);

}
