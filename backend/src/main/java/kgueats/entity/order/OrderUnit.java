package kgueats.entity.order;

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

import kgueats.entity.store.Food;

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
	@JoinColumn(name = "food_id")
	private Food food;

	private Long amount;

	public OrderUnit(Long amount) {
		this.amount = amount;
	}

	public void assignOrder(Order order) {
		order.appendOrderUnit(this);
		this.order = order;
	}

	public void assignFood(Food food) {
		food.appendOrderUnit(this);
		this.food = food;
	}

}
