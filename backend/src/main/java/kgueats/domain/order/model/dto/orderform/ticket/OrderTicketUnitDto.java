package kgueats.domain.order.model.dto.orderform.ticket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderTicketUnitDto {

	private Long menuId;
	private Long amount;

	@Builder
	@JsonCreator
	public OrderTicketUnitDto(
		@JsonProperty("menuId") Long menuId,
		@JsonProperty("amount") Long amount) {
		this.menuId = menuId;
		this.amount = amount;
	}

}
