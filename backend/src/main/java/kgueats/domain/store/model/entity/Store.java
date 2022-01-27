package kgueats.domain.store.model.entity;

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

import kgueats.domain.order.model.entity.OrderHistory;

@Entity
@Getter
@Table(name = "store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long id;

	@OneToMany(mappedBy = "store")
	private List<BusinessHour> businessHours = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<Menu> menus = new ArrayList<>();

	@OneToMany(mappedBy = "store")
	private List<OrderHistory> purchaseHistories = new ArrayList<>();

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

	public void appendMenu(Menu menu) {
		this.menus.add(menu);
		menu.assignStore(this);
	}

	public void appendOrder(OrderHistory orderHistory) {
		this.purchaseHistories.add(orderHistory);
		orderHistory.assignStore(this);
	}

}
