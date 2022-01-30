package kgueats.domain.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.dto.orderform.OrderDto;
import kgueats.domain.order.model.dto.orderhistory.OrderHistoryDto;
import kgueats.domain.order.service.OrderService;
import kgueats.domain.ticket.model.dto.TicketDto;

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

	@GetMapping("/order/history")
	public ResponseEntity<List<OrderHistoryDto>> getOrderHistoryList() {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryList(studentId));
	}

	@GetMapping("/order/history/menu/{menuId}")
	public ResponseEntity<List<OrderHistoryDto>> getOrderHistoryListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryListByMenuId(studentId, menuId));
	}

	@GetMapping("/order/history/store/{storeId}")
	public ResponseEntity<List<OrderHistoryDto>> getOrderHistoryListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryListByStoreId(studentId, storeId));
	}

}
