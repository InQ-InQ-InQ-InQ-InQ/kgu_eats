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

@Entity
@Getter
@Table(name = "order_ticket_history")
public class OrderTicketHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_ticket_history_id")
	private Long id;

	@OneToMany(mappedBy = "orderTicketHistory")
	private List<OrderTicketHistoryUnit> orderTicketHistoryUnits = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	private LocalDateTime orderDate;

	@Builder
	public OrderTicketHistory() {
		orderDate = LocalDateTime.now();
	}

	public void appendOrderTicketHistoryUnit(OrderTicketHistoryUnit orderTicketHistoryUnit) {
		this.orderTicketHistoryUnits.add(orderTicketHistoryUnit);
		orderTicketHistoryUnit.assignOrderTicketHistory(this);
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

}
