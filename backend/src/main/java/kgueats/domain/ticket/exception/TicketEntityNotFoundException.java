package kgueats.domain.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TicketEntityNotFoundException extends EntityNotFoundException {

	public TicketEntityNotFoundException() {
		super("식권을 찾을 수 없습니다.");
	}

}
