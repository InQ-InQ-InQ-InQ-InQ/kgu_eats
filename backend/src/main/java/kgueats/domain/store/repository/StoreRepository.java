package kgueats.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.store.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
