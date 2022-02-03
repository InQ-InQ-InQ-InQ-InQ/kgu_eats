package kgueats.domain.store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.model.dto.StoreDetailDto;
import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.service.StoreService;
import kgueats.exception.ExceptionController;

@RestController
@RequiredArgsConstructor
public class StoreController extends ExceptionController {

	private final StoreService storeService;

	@GetMapping("/stores")
	public List<StoreSimpleDto> getStoreList() {
		return storeService.getStoreList();
	}

	@GetMapping("/stores/{storeId}")
	public StoreDetailDto getStoreDetail(@PathVariable Long storeId) {
		return storeService.getStoreDetail(storeId);
	}

	@GetMapping("/stores/{storeId}/menus")
	public List<MenuDto> getMenuList(@PathVariable Long storeId) {
		return storeService.getMenuList(storeId);
	}

}
