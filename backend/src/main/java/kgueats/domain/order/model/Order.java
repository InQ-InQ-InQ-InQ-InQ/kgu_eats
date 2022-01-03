package kgueats.domain.order.model;

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

import kgueats.domain.member.model.Student;
import kgueats.domain.store.model.Store;
import lombok.Getter;

@Entity
@Getter
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@OneToMany(mappedBy = "order")
	private List<OrderUnit> orderUnits = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	private LocalDateTime orderDate;

	public Order() {
		orderDate = LocalDateTime.now();
	}

	public void appendOrderUnit(OrderUnit orderUnit) {
		this.orderUnits.add(orderUnit);
		orderUnit.assignOrder(this);
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignStore(Store store) {
		this.store = store;
	}

}
