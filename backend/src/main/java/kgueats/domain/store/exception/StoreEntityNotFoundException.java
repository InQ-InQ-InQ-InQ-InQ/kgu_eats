package kgueats.domain.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreEntityNotFoundException extends EntityNotFoundException {

	public StoreEntityNotFoundException() {
		super("매장을 찾을 수 없습니다.");
	}

}
