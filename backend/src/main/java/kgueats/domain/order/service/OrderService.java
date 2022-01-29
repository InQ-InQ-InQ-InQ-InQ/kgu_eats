package kgueats.domain.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.dto.TicketDto;
import kgueats.domain.order.model.dto.orderform.OrderDto;
import kgueats.domain.order.model.dto.orderform.OrderUnitDto;
import kgueats.domain.order.model.dto.orderhistory.OrderHistoryDto;
import kgueats.domain.order.model.entity.OrderHistory;
import kgueats.domain.order.model.entity.OrderHistoryUnit;
import kgueats.domain.order.repository.OrderHistoryRepository;
import kgueats.domain.order.repository.OrderHistoryUnitRepository;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.service.StoreService;
import kgueats.exception.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final StoreService storeService;
	private final TicketService ticketService;

	private final OrderHistoryRepository orderHistoryRepository;
	private final OrderHistoryUnitRepository orderHistoryUnitRepository;

	@Transactional(rollbackFor = EntityNotFoundException.class)
	public List<TicketDto> payForOrder(Student student, OrderDto orderDto) {
		List<TicketDto> ticketDtos = new ArrayList<>();
		Store store = storeService.getStoreEntity(orderDto.getStoreId());

		OrderHistory orderHistory = new OrderHistory();
		orderHistory.assignStore(store);
		orderHistory.assignStudent(student);
		orderHistoryRepository.save(orderHistory);

		for (OrderUnitDto orderUnitDto : orderDto.getOrderUnitDtos()) {
			Menu menu = storeService.getMenuEntity(store.getId(), orderUnitDto.getMenuId());
			ticketDtos.add(ticketService.issueTicket(student, menu, orderUnitDto.getAmount()));

			OrderHistoryUnit orderHistoryUnit = new OrderHistoryUnit(orderUnitDto.getAmount());
			orderHistoryUnit.assignMenu(menu);
			orderHistoryUnit.assignOrderHistory(orderHistory);
			orderHistoryUnitRepository.save(orderHistoryUnit);
		}

		return ticketDtos;
	}

	public List<OrderHistoryDto> getOrderHistoryList(Long studentId) {
		return orderHistoryRepository.findAllByStudentId(studentId).stream()
			.map(OrderHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderHistoryDto> getOrderHistoryListByMenuId(Long studentId, Long menuId) {
		return orderHistoryRepository.findAllByStudentIdAndMenuId(studentId, menuId).stream()
			.map(OrderHistoryDto::toDto).collect(Collectors.toList());
	}

	public List<OrderHistoryDto> getOrderHistoryListByStoreId(Long studentId, Long storeId) {
		return orderHistoryRepository.findAllByStudentIdAndStoreId(studentId, storeId).stream()
			.map(OrderHistoryDto::toDto).collect(Collectors.toList());
	}

}
