package kgueats.domain.order.exception;

public class OrderMenuDateTooLateException extends RuntimeException {

	public OrderMenuDateTooLateException() {
		super("주문시점으로부터 시간이 너무 지났습니다.");
	}

}
