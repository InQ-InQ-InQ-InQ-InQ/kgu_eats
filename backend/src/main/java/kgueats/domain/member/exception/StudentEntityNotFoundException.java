package kgueats.domain.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import kgueats.exception.EntityNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentEntityNotFoundException extends EntityNotFoundException {

	public StudentEntityNotFoundException() {
		super("학생을 찾을 수 없습니다.");
	}

}
