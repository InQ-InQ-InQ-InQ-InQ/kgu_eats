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

import kgueats.domain.store.model.entity.Menu;

@Entity
@Getter
@Table(name = "order_history_unit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHistoryUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_history_unit_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_history_id")
	private OrderHistory orderHistory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private Long amount;

	@Builder
	public OrderHistoryUnit(Long amount) {
		this.amount = amount;
	}

	public void assignOrderHistory(OrderHistory orderHistory) {
		this.orderHistory = orderHistory;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

}
