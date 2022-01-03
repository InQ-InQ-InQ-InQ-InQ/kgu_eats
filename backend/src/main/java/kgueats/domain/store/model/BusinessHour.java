package kgueats.domain.store.model;

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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "business_hour")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_hour_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;

	private LocalDateTime openTime;

	private LocalDateTime closeTime;

	public BusinessHour(LocalDateTime openTime, LocalDateTime closeTime) {
		this.openTime = openTime;
		this.closeTime = closeTime;
	}

	public void assignStore(Store store) {
		this.store = store;
	}

}
