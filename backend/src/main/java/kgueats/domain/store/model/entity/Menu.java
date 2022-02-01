package kgueats.domain.store.model.entity;

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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.order.model.entity.OrderMenuHistory;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.ticket.model.entity.Ticket;

@Entity
@Getter
@Table(name = "menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Long id;

	@OneToMany(mappedBy = "menu")
	private List<Ticket> tickets = new ArrayList<>();

	@OneToMany(mappedBy = "menu")
	private List<Review> reviews = new ArrayList<>();

	@OneToMany(mappedBy = "menu")
	private List<OrderMenuHistory> orderMenuHistories = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	private String name;

	private Long price;

	public Menu(String name, Long price) {
		this.name = name;
		this.price = price;
	}

	public void appendTicket(Ticket ticket) {
		this.tickets.add(ticket);
		ticket.assignMenu(this);
	}

	public void appendReview(Review review) {
		this.reviews.add(review);
		review.assignMenu(this);
	}

	public void appendOrderMenuHistory(OrderMenuHistory orderMenuHistory) {
		this.orderMenuHistories.add(orderMenuHistory);
		orderMenuHistory.assignMenu(this);
	}

	public void assignStore(Store store) {
		this.store = store;
	}

}
