package kgueats.domain.member.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import kgueats.domain.order.model.entity.OrderHistory;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.ticket.model.entity.Ticket;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student implements UserDetails {

	@Id
	@Column(name = "student_id")
	private Long id;

	@OneToMany(mappedBy = "student")
	private List<OrderHistory> purchaseHistories = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<Ticket> tickets = new ArrayList<>();

	@OneToMany(mappedBy = "student")
	private List<Review> reviews = new ArrayList<>();

	private String username;

	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();

	@Builder
	public Student(Long id, String username, String password, List<String> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public void appendOrder(OrderHistory orderHistory) {
		this.purchaseHistories.add(orderHistory);
		orderHistory.assignStudent(this);
	}

	public void appendTicket(Ticket ticket) {
		this.tickets.add(ticket);
		ticket.assignStudent(this);
	}

	public void appendReview(Review review) {
		this.reviews.add(review);
		review.assignStudent(this);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
