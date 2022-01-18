package kgueats.domain.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StoreNotFoundException extends RuntimeException {

	public StoreNotFoundException() {
		super("매장을 찾을 수 없습니다.");
	}

}
