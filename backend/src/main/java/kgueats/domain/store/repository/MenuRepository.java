package kgueats.domain.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.store.model.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	List<Menu> findAllByStoreId(Long storeId);

	@Query(value = "select * from menu where (store_id = :storeId and menu_id = :menuId)", nativeQuery = true)
	Optional<Menu> findByStoreIdAndMenuId(@Param("storeId") Long storeId, @Param("menuId") Long menuId);

}
