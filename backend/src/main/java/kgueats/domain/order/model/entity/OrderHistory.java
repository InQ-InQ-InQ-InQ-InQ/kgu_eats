package kgueats.domain.order.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.store.model.entity.Store;

@Entity
@Getter
@Table(name = "order_history")
public class OrderHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_history_id")
	private Long id;

	@OneToMany(mappedBy = "orderHistory")
	private List<OrderHistoryUnit> orderHistoryUnits = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	private LocalDateTime orderDate;

	@Builder
	public OrderHistory() {
		orderDate = LocalDateTime.now();
	}

	public void appendOrderHistoryUnit(OrderHistoryUnit orderHistoryUnit) {
		this.orderHistoryUnits.add(orderHistoryUnit);
		orderHistoryUnit.assignOrderHistory(this);
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignStore(Store store) {
		this.store = store;
	}

}
