package kgueats.domain.store.controller;

import java.util.List;

import kgueats.domain.store.model.dto.MenuDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.service.StoreService;


@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores")
    public ResponseEntity<List<StoreSimpleDto>> getStoreList() {
        return ResponseEntity.ok(storeService.getStoreList());
    }

    @GetMapping("/stores/{storeId}/menus")
    public ResponseEntity<List<MenuDto>> getMenuList(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeService.getMenuList(storeId));
    }

}
