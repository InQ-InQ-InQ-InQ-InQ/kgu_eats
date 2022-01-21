package kgueats.domain.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordNotMatchException extends RuntimeException {

	public PasswordNotMatchException() {
		super("비밀번호가 일치하지 않습니다.");
	}

}
