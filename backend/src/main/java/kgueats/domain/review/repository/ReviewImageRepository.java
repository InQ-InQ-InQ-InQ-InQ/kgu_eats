package kgueats.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kgueats.domain.review.model.entity.ReviewImage;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

}
