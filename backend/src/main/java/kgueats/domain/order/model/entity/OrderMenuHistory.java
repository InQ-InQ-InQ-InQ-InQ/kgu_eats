package kgueats.domain.order.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.store.model.entity.Menu;

@Entity
@Getter
@Table(name = "order_menu_history")
public class OrderMenuHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_menu_history_id")
	private Long id;

	@OneToOne(mappedBy = "orderMenuHistory")
	private Review review;

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

	public void assignReview(Review review) {
		this.review = review;
		review.assignOrderMenuHistory(this);
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

	public boolean isTooLate(int limitDays) {
		int days = Period.between(orderDate.toLocalDate(), LocalDate.now()).getDays();
		return (days > limitDays);
	}

	public boolean isReviewWritten() {
		return (review != null);
	}

}
