package kgueats.domain.order.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

import kgueats.domain.order.model.entity.Ticket;
import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.model.entity.Menu;

@Getter
public class TicketDto {

	private Long id;
	private MenuDto menuDto;
	private Long amount;

	@Builder
	@JsonCreator
	public TicketDto(
		@JsonProperty("ticketId") Long id,
		@JsonProperty("menu") Menu menu,
		@JsonProperty("amount") Long amount) {
		this.id = id;
		this.menuDto = MenuDto.toDto(menu);
		this.amount = amount;
	}

	public static TicketDto toDto(Ticket ticket) {
		return TicketDto.builder()
			.id(ticket.getId())
			.menu(ticket.getMenu())
			.amount(ticket.getAmount())
			.build();
	}

}
