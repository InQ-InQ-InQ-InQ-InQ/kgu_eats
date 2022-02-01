package kgueats.domain.ticket.model.entity;

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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.entity.OrderTicketHistoryUnit;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.ticket.exception.TicketAmountNotEnoughException;

@Entity
@Getter
@Table(name = "ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long id;

	@OneToMany(mappedBy = "ticket")
	private List<OrderTicketHistoryUnit> orderTicketHistoryUnits = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private Long amount;

	public Ticket(Long amount) {
		this.amount = amount;
	}

	public void appendOrderTicketUnitHistory(OrderTicketHistoryUnit orderTicketHistoryUnit) {
		this.orderTicketHistoryUnits.add(orderTicketHistoryUnit);
		orderTicketHistoryUnit.assignTicket(this);
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

	public void increaseAmount(Long amount) {
		this.amount += amount;
	}

	public void decreaseAmount() {
		if (this.isEmpty()) {
			throw new TicketAmountNotEnoughException();
		}
		this.amount -= 1;
	}

	private boolean isEmpty() {
		return (this.amount == 0L);
	}

}
