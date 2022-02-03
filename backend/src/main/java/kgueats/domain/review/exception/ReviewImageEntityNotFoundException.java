package kgueats.domain.review.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewImageEntityNotFoundException extends EntityNotFoundException {

	public ReviewImageEntityNotFoundException() {
		super("리뷰 이미지를 찾을 수 없습니다.");
	}

}
