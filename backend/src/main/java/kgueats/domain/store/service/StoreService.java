package kgueats.domain.store.service;

import java.util.List;
import java.util.stream.Collectors;

import kgueats.domain.store.exception.StoreNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.dto.StoreDetailDto;
import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.model.dto.MenuDto;
import kgueats.domain.store.repository.StoreRepository;
import kgueats.domain.store.repository.MenuRepository;


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

    public StoreDetailDto getStoreDetail(Long storeId) {
        return StoreDetailDto.toDto(storeRepository.findById(storeId).orElseThrow(StoreNotFoundException::new));
    }

    public List<MenuDto> getMenuList(Long storeId) {
        return menuRepository.findAllByStoreId(storeId)
                .stream().map(MenuDto::toDto)
                .collect(Collectors.toList());
    }

}
