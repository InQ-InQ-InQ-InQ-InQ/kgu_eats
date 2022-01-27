package kgueats.domain.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.dto.OrderDto;
import kgueats.domain.order.model.dto.TicketDto;
import kgueats.domain.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final AuthService authService;
	private final OrderService orderService;

	@PostMapping("/order/pay")
	public ResponseEntity<List<TicketDto>> orderMenus(@RequestBody OrderDto orderDto) {
		Student student = authService.getAuthStudent();
		return ResponseEntity.ok(orderService.payForOrder(student, orderDto));
	}

}
