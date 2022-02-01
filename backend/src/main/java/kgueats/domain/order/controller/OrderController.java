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
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderTicketHistoryList() {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderTicketHistoryList(studentId));
	}

	@GetMapping("/order/tickets/history/menu/{menuId}")
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderTicketHistoryListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderTicketHistoryListByMenuId(studentId, menuId));
	}

	@GetMapping("/order/tickets/history/store/{storeId}")
	public ResponseEntity<List<OrderTicketHistoryDto>> getOrderTicketHistoryListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderTicketHistoryListByStoreId(studentId, storeId));
	}

	@GetMapping("/order/tickets/history/{orderHistoryId}")
	public ResponseEntity<OrderTicketHistoryDto> getOrderTicketHistory(
		@PathVariable(value = "orderHistoryId") Long orderHistoryId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderTicketHistory(studentId, orderHistoryId));
	}

	@GetMapping("/order/menus/history")
	public ResponseEntity<List<OrderMenuHistoryDto>> getOrderMenuHistoryList() {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderMenuHistoryList(studentId));
	}

	@GetMapping("/order/menus/history/menu/{menuId}")
	public ResponseEntity<List<OrderMenuHistoryDto>> getOrderMenuHistoryListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderMenuHistoryListByMenuId(studentId, menuId));
	}

	@GetMapping("/order/menus/history/store/{storeId}")
	public ResponseEntity<List<OrderMenuHistoryDto>> getOrderMenuHistoryListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderMenuHistoryListByStoreId(studentId, storeId));
	}

	@GetMapping("/order/menus/history/{orderHistoryId}")
	public ResponseEntity<OrderMenuHistoryDto> getOrderMenuHistory(
		@PathVariable(value = "orderHistoryId") Long orderHistoryId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(orderService.getOrderMenuHistory(studentId, orderHistoryId));
	}

}
