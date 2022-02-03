package kgueats.domain.review.exception;

public class ReviewAlreadyWrittenException extends RuntimeException {

	public ReviewAlreadyWrittenException() {
		super("리뷰를 이미 작성하셨습니다.");
	}

}
