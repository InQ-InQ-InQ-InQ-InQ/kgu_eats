package kgueats.domain.ticket.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.ticket.model.dto.TicketDto;
import kgueats.domain.ticket.service.TicketService;

@RestController
@RequiredArgsConstructor
public class TicketController {

	private final AuthService authService;
	private final TicketService ticketService;

	@GetMapping("/tickets")
	public ResponseEntity<List<TicketDto>> getTicketList() {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(ticketService.getTicketList(studentId));
	}

	@GetMapping("/tickets/store/{storeId}")
	public ResponseEntity<List<TicketDto>> getTicketListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(ticketService.getTicketListByStoreId(studentId, storeId));
	}

	@GetMapping("/tickets/menu/{menuId}")
	public ResponseEntity<List<TicketDto>> getTicketListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ResponseEntity.ok(ticketService.getTicketListByMenuId(studentId, menuId));
	}

}
