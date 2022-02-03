package kgueats.domain.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderHistoryEntityNotFoundException extends EntityNotFoundException {

	public OrderHistoryEntityNotFoundException() {
		super("구매내역을 찾을 수 없습니다.");
	}

}
