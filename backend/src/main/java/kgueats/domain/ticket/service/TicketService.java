package kgueats.domain.ticket.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.ticket.exception.TicketEntityNotFoundException;
import kgueats.domain.ticket.model.dto.TicketDto;
import kgueats.domain.ticket.model.entity.Ticket;
import kgueats.domain.ticket.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	public Ticket issueTicket(Student student, Menu menu, Long amount) {
		Ticket ticket = ticketRepository.findByStudentIdAndMenuId(student.getId(), menu.getId())
			.orElseGet(() -> this.getNewTicket(student, menu));
		ticket.increaseAmount(amount);
		return ticket;
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

	public TicketDto getTicket(Long studentId, Long ticketId) {
		return TicketDto.toDto(getTicketEntityById(studentId, ticketId));
	}

	public Ticket getTicketEntityById(Long studentId, Long ticketId) {
		return ticketRepository.findByStudentIdAndTicketId(studentId, ticketId)
			.orElseThrow(TicketEntityNotFoundException::new);
	}

	public Ticket getTicketEntityByMenuId(Long studentId, Long menuId) {
		return ticketRepository.findByStudentIdAndMenuId(studentId, menuId)
			.orElseThrow(TicketEntityNotFoundException::new);
	}

}
