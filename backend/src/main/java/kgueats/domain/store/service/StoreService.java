package kgueats.domain.store.service;

import java.util.List;
import java.util.stream.Collectors;

import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.repository.MenuRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public List<StoreSimpleDto> getStoreList() {
        return storeRepository.findAll()
                .stream().map(StoreSimpleDto::toDto)
                .collect(Collectors.toList());
    }

    public List<MenuDto> getMenuList(Long storeId) {
        return menuRepository.findAllByStoreId(storeId)
                .stream().map(MenuDto::toDto)
                .collect(Collectors.toList());
    }

}