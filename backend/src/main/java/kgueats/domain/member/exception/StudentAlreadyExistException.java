package kgueats.domain.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentAlreadyExistException extends RuntimeException {

	public StudentAlreadyExistException() {
		super("회원이 이미 존재합니다.");
	}

}
