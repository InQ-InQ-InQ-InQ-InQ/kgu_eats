package kgueats.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.store.model.BusinessHour;

@Repository
public interface BusinessHourRepository extends JpaRepository<BusinessHour, Long> {

}
