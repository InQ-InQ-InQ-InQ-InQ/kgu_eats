package kgueats.domain.review.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.review.exception.ReviewEntityNotFoundException;
import kgueats.domain.review.model.dto.ReviewGetDto;
import kgueats.domain.review.model.dto.ReviewPatchDto;
import kgueats.domain.review.model.dto.ReviewPostDto;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.review.repository.ReviewRepository;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.service.StoreService;
import kgueats.domain.review.exception.ReviewDateTooLateException;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final StoreService storeService;
	private final ReviewRepository reviewRepository;

	public ReviewGetDto saveReview(Student student, Long storeId, ReviewPostDto reviewPostDto) {
		Store store = storeService.getStoreEntity(storeId);
		Menu menu = storeService.getMenuEntity(storeId, reviewPostDto.getMenuId());

		Review review = this.getNewReview(student, menu, reviewPostDto.getContent());
		return ReviewGetDto.toDto(review);
	}

	private Review getNewReview(Student student, Menu menu, String content) {
		Review review = new Review(content);
		student.appendReview(review);
		menu.appendReview(review);
		reviewRepository.save(review);
		return review;
	}

	public ReviewGetDto updateReview(Student student, Long reviewId, ReviewPatchDto reviewPatchDto) {
		Review review = reviewRepository.findByStudentIdAndReviewId(student.getId(), reviewId)
			.orElseThrow(ReviewEntityNotFoundException::new);
		checkReviewDateTooLate(review);

		review.updateContent(reviewPatchDto.getContent());
		reviewRepository.save(review);
		return ReviewGetDto.toDto(review);
	}

	public List<ReviewGetDto> getReviewList(Long storeId) {
		return reviewRepository.findAllByStoreId(storeId)
			.stream().map(ReviewGetDto::toDto).collect(Collectors.toList());
	}

	public ReviewGetDto getReview(Long reviewId) {
		Review reivew =  reviewRepository.findByReviewId(reviewId)
			.orElseThrow(ReviewEntityNotFoundException::new);
		return ReviewGetDto.toDto(reivew);
	}

	public void deleteReview(Student student, Long reviewId) {
		Review review = reviewRepository.findByStudentIdAndReviewId(student.getId(), reviewId)
			.orElseThrow(ReviewEntityNotFoundException::new);
		checkReviewDateTooLate(review);

		student.removeReview(review);
		reviewRepository.delete(review);
	}

	private void checkReviewDateTooLate(Review review) {
		int limitDays = 3;
		if (review.isTooLate(limitDays)) {
			throw new ReviewDateTooLateException();
		}
	}

}
