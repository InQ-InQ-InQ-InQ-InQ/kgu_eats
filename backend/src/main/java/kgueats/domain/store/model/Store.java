package kgueats.domain.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.order.model.Order;

@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "store_id")
	private Long id;

	@OneToMany(mappedBy = "store")
	private List<BusinessHour> businessHours = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<Food> foods = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<Order> orders = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private CampusEnum campus;

	private String name;

	private String location;

	public Store(CampusEnum campus, String name, String location) {
		this.campus = campus;
		this.name = name;
		this.location = location;
	}

	public void appendBusinessHour(BusinessHour businessHour) {
		this.businessHours.add(businessHour);
		businessHour.assignStore(this);
	}

	public void appendFood(Food food) {
		this.foods.add(food);
		food.assignStore(this);
	}

	public void appendOrder(Order order) {
		this.orders.add(order);
		order.assignStore(this);
	}

}
