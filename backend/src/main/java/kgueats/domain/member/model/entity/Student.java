package kgueats.domain.member.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.order.model.entity.Order;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

	@Id
	@Column(name = "student_id")
	private Long id;

	@OneToMany(mappedBy = "student")
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<Ticket> tickets = new ArrayList<>();

	private String username;

	private String password;

	public Student(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public void appendOrder(Order order) {
		this.orders.add(order);
		order.assignStudent(this);
	}

	public void appendTicket(Ticket ticket) {
		this.tickets.add(ticket);
		ticket.assignStudent(this);
	}

}
