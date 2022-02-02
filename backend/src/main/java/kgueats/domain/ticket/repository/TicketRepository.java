package kgueats.domain.ticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.ticket.model.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query(value = "select * from Ticket"
		+ " where student_id = :studentId", nativeQuery = true)
	List<Ticket> findAllByStudentId(@Param("studentId") Long studentId);

	@Query(value = "select * from Ticket"
		+ " where student_id = :studentId and menu_id = :menuId", nativeQuery = true)
	List<Ticket> findAllByStudentIdAndMenuId(@Param("studentId") Long studentId, @Param("menuId") Long storeId);

	@Query(value = "select ticket.* from Ticket as ticket"
		+ " join Menu using(menu_id)"
		+ " where student_id = :studentId and menu.store_id = :storeId", nativeQuery = true)
	List<Ticket> findAllByStudentIdAndStoreId(@Param("studentId") Long studentId, @Param("storeId") Long storeId);

	Optional<Ticket> findByStudentIdAndMenuId(Long studentId, Long menuId);

	@Query(value = "select * from Ticket"
		+ " where student_id = :studentId and ticket_id = :ticketId", nativeQuery = true)
	Optional<Ticket> findByStudentIdAndTicketId(@Param("studentId") Long studentId, @Param("ticketId") Long ticketId);

}
