package kgueats.domain.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.OrderMenuHistory;

@Repository
public interface OrderMenuHistoryRepository extends JpaRepository<OrderMenuHistory, Long> {

	@Query(value = "select * from Order_Menu_History"
		+ " where student_id = :studentId", nativeQuery = true)
	List<OrderMenuHistory> findAllByStudentId(@Param("studentId") Long studentId);

	@Query(value = "select * from Order_Menu_History"
		+ " where student_id = :studentId and store_id = :storeId", nativeQuery = true)
	List<OrderMenuHistory> findAllByStudentIdAndMenuId(
		@Param("studentId") Long studentId, @Param("storeId") Long storeId);

	@Query(value = "select * from Order_Menu_History history"
		+ " join Order_Menu_History_Unit history_unit"
		+ " on history.order_menu_history_id = history_unit.order_menu_history_id"
		+ " where student_id = :studentId and menu_id = :menuId", nativeQuery = true)
	List<OrderMenuHistory> findAllByStudentIdAndStoreId(
		@Param("studentId") Long studentId, @Param("menuId") Long storeId);

	@Query(value = "select * from Order_Menu_History"
		+ " where student_id = :studentId and order_menu_history_id = :orderMenuHistoryId", nativeQuery = true)
	Optional<OrderMenuHistory> findByStudentIdAndOrderHistoryId(
		@Param("studentId") Long studentId, @Param("orderMenuHistoryId") Long orderMenuHistoryId);

}
