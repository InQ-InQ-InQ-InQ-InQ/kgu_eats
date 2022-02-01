package kgueats.domain.order.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.ticket.model.entity.Ticket;

@Entity
@Getter
@Table(name = "order_ticket_history_unit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderTicketHistoryUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_ticket_history_unit_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_ticket_history_id")
	private OrderTicketHistory orderTicketHistory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	private Long amount;

	@Builder
	public OrderTicketHistoryUnit(Long amount) {
		this.amount = amount;
	}

	public void assignOrderTicketHistory(OrderTicketHistory orderTicketHistory) {
		this.orderTicketHistory = orderTicketHistory;
	}

	public void assignTicket(Ticket ticket) {
		this.ticket = ticket;
	}


}
