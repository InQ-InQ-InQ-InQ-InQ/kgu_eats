package kgueats.domain.ticket.model.entity;

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

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.store.model.entity.Menu;

@Entity
@Getter
@Table(name = "ticket")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private Long amount;

	public Ticket(Long amount) {
		this.amount = amount;
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

	public void incrementAmount(Long amount) {
		this.amount += amount;
	}

}
