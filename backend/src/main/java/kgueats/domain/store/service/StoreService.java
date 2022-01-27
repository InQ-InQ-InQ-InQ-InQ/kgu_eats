package kgueats.domain.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.exception.MenuEntityNotFoundException;
import kgueats.domain.store.exception.StoreEntityNotFoundException;
import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.model.dto.StoreDetailDto;
import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.repository.MenuRepository;
import kgueats.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository storeRepository;
	private final MenuRepository menuRepository;

	public Store getStoreEntity(Long storeId) {
		return storeRepository.findById(storeId).orElseThrow(StoreEntityNotFoundException::new);
	}

	public List<StoreSimpleDto> getStoreList() {
		return storeRepository.findAll()
				.stream().map(StoreSimpleDto::toDto)
				.collect(Collectors.toList());
	}

	public StoreDetailDto getStoreDetail(Long storeId) {
		return StoreDetailDto.toDto(getStoreEntity(storeId));
	}

	public Menu getMenuEntity(Long storeId, Long menuId) {
		return menuRepository.findByStoreIdAndMenuId(storeId, menuId)
			.orElseThrow(MenuEntityNotFoundException::new);
	}

	public List<MenuDto> getMenuList(Long storeId) {
		return menuRepository.findAllByStoreId(storeId)
				.stream().map(MenuDto::toDto)
				.collect(Collectors.toList());
	}

}
