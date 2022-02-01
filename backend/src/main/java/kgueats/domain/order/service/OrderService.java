package kgueats.domain.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.exception.OrderHistoryEntityNotFoundException;
import kgueats.domain.order.model.dto.orderform.menu.OrderMenuDto;
import kgueats.domain.order.model.dto.orderform.ticket.OrderTicketDto;
import kgueats.domain.order.model.dto.orderform.ticket.OrderTicketUnitDto;
import kgueats.domain.order.model.dto.orderhistory.OrderMenuHistoryDto;
import kgueats.domain.order.model.dto.orderhistory.OrderTicketHistoryDto;
import kgueats.domain.order.model.entity.OrderMenuHistory;
import kgueats.domain.order.model.entity.OrderTicketHistory;
import kgueats.domain.order.model.entity.OrderTicketHistoryUnit;
import kgueats.domain.order.repository.OrderMenuHistoryRepository;
import kgueats.domain.order.repository.OrderTicketHistoryRepository;
import kgueats.domain.order.repository.OrderTicketHistoryUnitRepository;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.service.StoreService;
import kgueats.domain.ticket.model.entity.Ticket;
import kgueats.domain.ticket.service.TicketService;
import kgueats.exception.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final StoreService storeService;
	private final TicketService ticketService;

	private final OrderMenuHistoryRepository orderMenuHistoryRepository;
	private final OrderTicketHistoryRepository orderTicketHistoryRepository;
	private final OrderTicketHistoryUnitRepository orderTicketHistoryUnitRepository;

	@Transactional(rollbackFor = EntityNotFoundException.class)
	public OrderTicketHistoryDto payForTicket(Student student, OrderTicketDto orderTicketDto) {
		Store store = storeService.getStoreEntity(orderTicketDto.getStoreId());

		OrderTicketHistory orderTicketHistory = new OrderTicketHistory();
		orderTicketHistory.assignStudent(student);
		orderTicketHistoryRepository.save(orderTicketHistory);

		for (OrderTicketUnitDto orderTicketUnitDto : orderTicketDto.getOrderTicketUnitDtos()) {
			Menu menu = storeService.getMenuEntity(store.getId(), orderTicketUnitDto.getMenuId());
			Ticket ticket = ticketService.issueTicket(student, menu, orderTicketUnitDto.getAmount());

			OrderTicketHistoryUnit orderTicketHistoryUnit = new OrderTicketHistoryUnit(orderTicketUnitDto.getAmount());
			orderTicketHistoryUnit.assignTicket(ticket);
			orderTicketHistoryUnitRepository.save(orderTicketHistoryUnit);

			orderTicketHistory.appendOrderTicketHistoryUnit(orderTicketHistoryUnit);
		}

		return OrderTicketHistoryDto.toDto(orderTicketHistory);
	}

	@Transactional(rollbackFor = EntityNotFoundException.class)
	public OrderMenuHistoryDto payForMenu(Student student, OrderMenuDto orderMenuDto) {
		Menu menu = storeService.getMenuEntity(orderMenuDto.getStoreId(), orderMenuDto.getMenuId());
		Ticket ticket = ticketService.getTicketEntityByMenuId(student.getId(), orderMenuDto.getMenuId());
		ticket.decreaseAmount();

		OrderMenuHistory orderMenuHistory = new OrderMenuHistory();
		orderMenuHistory.assignMenu(menu);
		orderMenuHistory.assignStudent(student);
		orderMenuHistoryRepository.save(orderMenuHistory);

		return OrderMenuHistoryDto.toDto(orderMenuHistory);
	}

	public List<OrderTicketHistoryDto> getOrderTicketHistoryList(Long studentId) {
		return orderTicketHistoryRepository.findAllByStudentId(studentId).stream()
			.map(OrderTicketHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderTicketHistoryDto> getOrderTicketHistoryListByMenuId(Long studentId, Long menuId) {
		return orderTicketHistoryRepository.findAllByStudentIdAndMenuId(studentId, menuId).stream()
			.map(OrderTicketHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderTicketHistoryDto> getOrderTicketHistoryListByStoreId(Long studentId, Long storeId) {
		return orderTicketHistoryRepository.findAllByStudentIdAndStoreId(studentId, storeId).stream()
			.map(OrderTicketHistoryDto::toDto).collect(Collectors.toList());
	}

	public OrderTicketHistoryDto getOrderTicketHistory(Long studentId, Long storeId) {
		OrderTicketHistory orderHistory = orderTicketHistoryRepository
			.findByStudentIdAndOrderHistoryId(studentId, storeId)
			.orElseThrow(OrderHistoryEntityNotFoundException::new);
		return OrderTicketHistoryDto.toDto(orderHistory);
	}

	public List<OrderMenuHistoryDto> getOrderMenuHistoryList(Long studentId) {
		return orderMenuHistoryRepository.findAllByStudentId(studentId).stream()
			.map(OrderMenuHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderMenuHistoryDto> getOrderMenuHistoryListByMenuId(Long studentId, Long menuId) {
		return orderMenuHistoryRepository.findAllByStudentIdAndMenuId(studentId, menuId).stream()
			.map(OrderMenuHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderMenuHistoryDto> getOrderMenuHistoryListByStoreId(Long studentId, Long storeId) {
		return orderMenuHistoryRepository.findAllByStudentIdAndStoreId(studentId, storeId).stream()
			.map(OrderMenuHistoryDto::toDto).collect(Collectors.toList());
	}

	public OrderMenuHistoryDto getOrderMenuHistory(Long studentId, Long storeId) {
		OrderMenuHistory orderHistory = orderMenuHistoryRepository
			.findByStudentIdAndOrderHistoryId(studentId, storeId)
			.orElseThrow(OrderHistoryEntityNotFoundException::new);
		return OrderMenuHistoryDto.toDto(orderHistory);
	}

}
