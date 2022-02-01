package kgueats.domain.ticket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.ticket.model.dto.TicketDto;
import kgueats.domain.ticket.service.TicketService;
import kgueats.exception.ExceptionController;

@RestController
@RequiredArgsConstructor
public class TicketController extends ExceptionController {

	private final AuthService authService;
	private final TicketService ticketService;

	@GetMapping("/tickets")
	public List<TicketDto> getTicketList() {
		Long studentId = authService.getAuthStudentId();
		return ticketService.getTicketList(studentId);
	}

	@GetMapping("/tickets/store/{storeId}")
	public List<TicketDto> getTicketListByStore(
		@PathVariable(value = "storeId") Long storeId) {
		Long studentId = authService.getAuthStudentId();
		return ticketService.getTicketListByStoreId(studentId, storeId);
	}

	@GetMapping("/tickets/menu/{menuId}")
	public List<TicketDto> getTicketListByMenu(
		@PathVariable(value = "menuId") Long menuId) {
		Long studentId = authService.getAuthStudentId();
		return ticketService.getTicketListByMenuId(studentId, menuId);
	}

	@GetMapping("/tickets/{ticketId}")
	public TicketDto getTicket(
		@PathVariable(value = "ticketId") Long ticketId) {
		Long studentId = authService.getAuthStudentId();
		return ticketService.getTicket(studentId, ticketId);
	}

}
