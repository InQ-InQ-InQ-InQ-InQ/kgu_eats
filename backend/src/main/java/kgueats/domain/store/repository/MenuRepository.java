package kgueats.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.store.model.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
