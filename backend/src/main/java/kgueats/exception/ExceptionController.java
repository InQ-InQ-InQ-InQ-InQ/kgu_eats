package kgueats.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {

	@ExceptionHandler(RuntimeException.class)
	public String handle(EntityNotFoundException exception) {
		return exception.getMessage();
	}

}
