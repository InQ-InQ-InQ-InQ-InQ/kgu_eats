package kgueats.domain.review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewEntityNotFoundException extends EntityNotFoundException {

	public ReviewEntityNotFoundException() {
		super("리뷰를 찾을 수 없습니다.");
	}

}
