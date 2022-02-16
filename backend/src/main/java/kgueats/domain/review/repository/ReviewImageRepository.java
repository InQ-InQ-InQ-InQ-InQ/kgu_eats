package kgueats.domain.review.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.review.model.entity.ReviewImage;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

	@Query(value = "select * from review_image"
		+ " join review using(review_id)"
		+ " where student_id = :studentId and review_image_id = :reviewImageId", nativeQuery = true)
	Optional<ReviewImage> findByStudentIdAndReviewImageId(
		@Param("studentId") Long studentId, @Param("reviewImageId") Long reviewImageId);

}
