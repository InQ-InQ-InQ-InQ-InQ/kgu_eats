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
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.store.model.entity.Menu;

@Entity
@Getter
@Table(name = "order_unit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_unit_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private Long amount;

	public OrderUnit(Long amount) {
		this.amount = amount;
	}

	public void assignOrder(Order order) {
		this.order = order;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

}
