package kgueats.domain.review.exception;

public class ReviewDateTooLateException extends RuntimeException {

	public ReviewDateTooLateException() {
		super("주문시점으로부터 3일이 지났으므로 기능을 이용할 수 없습니다.");
	}

}
