package kgueats.domain.store.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.store.model.dto.StoreSimpleDto;
import kgueats.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<StoreSimpleDto> getStoreList() {
        return storeRepository.findAll()
                .stream().map(StoreSimpleDto::toDto)
                .collect(Collectors.toList());
    }

}
