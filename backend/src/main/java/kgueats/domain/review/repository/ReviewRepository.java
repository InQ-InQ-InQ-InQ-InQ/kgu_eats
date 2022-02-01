package kgueats.domain.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kgueats.domain.review.model.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "select * from Review review"
					+ " join Menu menu on review.menu_id = menu.menu_id"
					+ " where store_id = :storeId", nativeQuery = true)
	List<Review> findAllByStoreId(@Param("storeId") Long storeId);

	@Query(value = "select * from Review review"
					+ " where student_id = :studentId"
					+ " and review_id = :reviewId", nativeQuery = true)
	Optional<Review> findByStudentIdAndReviewId(
		@Param("studentId") Long studentId, @Param("reviewId") Long reviewId);

	@Query(value = "select * from Review review"
					+ " where review_id = :reviewId", nativeQuery = true)
	Optional<Review> findByReviewId(@Param("reviewId") Long reviewId);

}
