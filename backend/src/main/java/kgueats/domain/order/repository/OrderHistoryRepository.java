package kgueats.domain.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.order.model.entity.OrderHistory;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

	@Query(value = "select * from Order_History"
					+ " where student_id = :studentId", nativeQuery = true)
	List<OrderHistory> findAllByStudentId(@Param("studentId") Long studentId);

	@Query(value = "select * from Order_History"
					+ " where student_id = :studentId and store_id = :storeId", nativeQuery = true)
	List<OrderHistory> findAllByStudentIdAndMenuId(@Param("studentId") Long studentId, @Param("storeId") Long storeId);

	@Query(value = "select * from Order_History history"
					+ " join Order_History_Unit history_unit"
					+ " on history.order_history_id = history_unit.order_history_id"
					+ " where student_id = :studentId and menu_id = :menuId", nativeQuery = true)
	List<OrderHistory> findAllByStudentIdAndStoreId(@Param("studentId") Long studentId, @Param("menuId") Long storeId);

	@Query(value = "select * from Order_History"
		+ " where student_id = :studentId and order_history_id = :orderHistoryId", nativeQuery = true)
	Optional<OrderHistory> findByStudentIdAndOrderHistoryId(
		@Param("studentId") Long studentId, @Param("orderHistoryId") Long orderHistoryId);

}
