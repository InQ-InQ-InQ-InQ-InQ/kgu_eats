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
import kgueats.domain.order.model.dto.orderform.menu.OrderMenuDto;
import kgueats.domain.order.model.dto.orderform.ticket.OrderTicketDto;
import kgueats.domain.order.model.dto.orderhistory.OrderMenuHistoryDto;
import kgueats.domain.order.model.dto.orderhistory.OrderTicketHistoryDto;
import kgueats.domain.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

	private final AuthService authService;
	private final OrderService orderService;

	@PostMapping("/order/ticket")
	public ResponseEntity<OrderTicketHistoryDto> orderTicket(@RequestBody OrderTicketDto orderTicketDto) {
		Student student = authService.getAuthStudent();
		return ResponseEntity.ok(orderService.payForTicket(student, orderTicketDto));
	}

	@PostMapping("/order/menu")
	public ResponseEntity<OrderMenuHistoryDto> orderMenu(@RequestBody OrderMenuDto orderMenuDto) {
		Student student = authService.getAuthStudent();
		return ResponseEntity.ok(orderService.payForMenu(student, orderMenuDto));
	}

	@GetMapping("/order/tickets/history")
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderHistoryList() {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryList(studentId));
	}

	@GetMapping("/order/tickets/history/menu/{menuId}")
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderHistoryListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryListByMenuId(studentId, menuId));
	}

	@GetMapping("/order/tickets/history/store/{storeId}")
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderHistoryListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistoryListByStoreId(studentId, storeId));
	}

	@GetMapping("/order/tickets/history/{orderHistoryId}")
	public ResponseEntity<OrderTicketHistoryDto> getOrderHistory(
		@PathVariable(value = "orderHistoryId") Long orderHistoryId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderHistory(studentId, orderHistoryId));
	}

}
