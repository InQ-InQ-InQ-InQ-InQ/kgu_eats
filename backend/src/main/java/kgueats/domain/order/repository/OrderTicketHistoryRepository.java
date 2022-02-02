package kgueats.domain.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.OrderTicketHistory;

@Repository
public interface OrderTicketHistoryRepository extends JpaRepository<OrderTicketHistory, Long> {

	@Query(value = "select * from Order_Ticket_History"
		+ " where student_id = :studentId", nativeQuery = true)
	List<OrderTicketHistory> findAllByStudentId(@Param("studentId") Long studentId);

	@Query(value = "select history.* from Order_Ticket_History as history"
		+ " join Order_ticket_history_unit using(order_ticket_history_id)"
		+ " join Ticket using(ticket_id)"
		+ " where Ticket.student_id = :studentId and menu_id = :menuId", nativeQuery = true)
	List<OrderTicketHistory> findAllByStudentIdAndMenuId(
		@Param("studentId") Long studentId, @Param("menuId") Long menuId);

	@Query(value = "select history.* from Order_Ticket_History as history"
		+ " join Order_Ticket_History_Unit using(order_ticket_history_id)"
		+ " join Ticket using(ticket_id)"
		+ " join Menu using(menu_id)"
		+ " where Ticket.student_id = :studentId and store_id = :storeId", nativeQuery = true)
	List<OrderTicketHistory> findAllByStudentIdAndStoreId(
		@Param("studentId") Long studentId, @Param("storeId") Long storeId);

	@Query(value = "select * from Order_Ticket_History"
		+ " where student_id = :studentId and order_ticket_history_id = :orderTicketHistoryId", nativeQuery = true)
	Optional<OrderTicketHistory> findByStudentIdAndOrderHistoryId(
		@Param("studentId") Long studentId, @Param("orderTicketHistoryId") Long orderTicketHistoryId);

}
