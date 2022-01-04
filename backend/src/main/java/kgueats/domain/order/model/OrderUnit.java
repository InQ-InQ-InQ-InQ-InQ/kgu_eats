package kgueats.domain.order.model;

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

import kgueats.domain.store.model.Food;

@Entity
@Getter
@Table(name = "order_unit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "order_unit_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id")
	private Food food;

	private Long amount;

	public OrderUnit(Long amount) {
		this.amount = amount;
	}

	public void assignOrder(Order order) {
		this.order = order;
	}

	public void assignFood(Food food) {
		this.food = food;
	}

}
