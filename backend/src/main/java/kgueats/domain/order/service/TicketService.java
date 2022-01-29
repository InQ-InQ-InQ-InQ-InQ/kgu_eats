package kgueats.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.dto.TicketDto;
import kgueats.domain.order.model.entity.Ticket;
import kgueats.domain.order.repository.TicketRepository;
import kgueats.domain.store.model.entity.Menu;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	public TicketDto issueTicket(Student student, Menu menu, Long amount) {
		Ticket ticket = ticketRepository.findByStudentIdAndMenuId(student.getId(), menu.getId())
			.orElseGet(() -> this.getNewTicket(student, menu));
		ticket.incrementAmount(amount);
		return TicketDto.toDto(ticket);
	}

	private Ticket getNewTicket(Student student, Menu menu) {
		Ticket ticket = new Ticket(0L);
		ticket.assignMenu(menu);
		student.appendTicket(ticket);
		ticketRepository.save(ticket);
		return ticket;
	}

	public List<TicketDto> getTicketList(Long studentId) {
		return ticketRepository.findAllByStudentId(studentId).stream()
			.map(TicketDto::toDto).collect(Collectors.toList());
	}

	public List<TicketDto> getTicketListByMenuId(Long studentId, Long menuId) {
		return ticketRepository.findAllByStudentIdAndMenuId(studentId, menuId).stream()
			.map(TicketDto::toDto).collect(Collectors.toList());
	}

	public List<TicketDto> getTicketListByStoreId(Long studentId, Long storeId) {
		return ticketRepository.findAllByStudentIdAndStoreId(studentId, storeId).stream()
			.map(TicketDto::toDto).collect(Collectors.toList());
	}

}
