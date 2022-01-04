package kgueats.domain.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.store.model.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findAll();

}
