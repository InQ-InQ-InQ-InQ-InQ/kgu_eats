package kgueats.domain.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TicketAmountNotEnoughException extends RuntimeException {

	public TicketAmountNotEnoughException() {
		super("식권 수량이 부족합니다.");
	}

}
