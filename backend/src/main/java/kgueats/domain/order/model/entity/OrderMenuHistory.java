package kgueats.domain.order.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.store.model.entity.Menu;

@Entity
@Getter
@Table(name = "order_menu_history")
public class OrderMenuHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_menu_history_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private LocalDateTime orderDate;

	@Builder
	public OrderMenuHistory() {
		orderDate = LocalDateTime.now();
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

}
