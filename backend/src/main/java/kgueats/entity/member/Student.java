package kgueats.entity.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.entity.order.Order;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;

	@OneToMany(mappedBy = "student")
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<Ticket> tickets = new ArrayList<>();

	private String username;

	private String password;

	public Student(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void appendOrder(Order order) {
		orders.add(order);
	}

	public void appendTicket(Ticket ticket) {
		tickets.add(ticket);
	}

}
