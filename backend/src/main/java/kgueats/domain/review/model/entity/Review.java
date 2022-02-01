package kgueats.domain.review.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

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
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;

	private String content;

	private LocalDateTime writtenDateTime;

	public Boolean updated = Boolean.FALSE;

	public Review(String content) {
		this.content = content;
		this.writtenDateTime = LocalDateTime.now();
	}

	public void assignStudent(Student student) {
		this.student = student;
	}

	public void assignMenu(Menu menu) {
		this.menu = menu;
	}

	public void updateContent(String content) {
		this.content = content;
		this.updated = Boolean.TRUE;
	}

	public boolean isTooLate(int limit) {
		int days = Period.between(writtenDateTime.toLocalDate(), LocalDate.now()).getDays();
		return days > limit;
	}

}
