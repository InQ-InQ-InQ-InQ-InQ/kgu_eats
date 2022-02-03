package kgueats.domain.review.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.order.model.entity.OrderMenuHistory;
import kgueats.domain.order.service.OrderService;
import kgueats.domain.review.exception.ReviewDateTooLateException;
import kgueats.domain.review.exception.ReviewEntityNotFoundException;
import kgueats.domain.review.model.dto.ReviewGetDto;
import kgueats.domain.review.model.dto.ReviewPatchDto;
import kgueats.domain.review.model.dto.ReviewPostDto;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.review.model.entity.ReviewImage;
import kgueats.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final OrderService orderService;
	private final ReviewImageService reviewImageService;
	private final ReviewRepository reviewRepository;

	public ReviewGetDto saveReview(
		Student student, ReviewPostDto reviewPostDto,
		List<MultipartFile> images) throws Exception {
		OrderMenuHistory orderMenuHistory = orderService.getOrderMenuHistoryEntity(
			student.getId(), reviewPostDto.getOrderMenuHistoryId());
		orderService.checkReviewPossible(orderMenuHistory);

		Review review = this.getNewReview(student, orderMenuHistory, reviewPostDto.getContent());
		reviewImageService.uploadImages(review, images);

		return ReviewGetDto.toDto(review);
	}

	private Review getNewReview(Student student, OrderMenuHistory orderMenuHistory, String content) {
		Review review = new Review(content);
		student.appendReview(review);
		orderMenuHistory.assignReview(review);
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
		Review review = reviewRepository.findByReviewId(reviewId)
			.orElseThrow(ReviewEntityNotFoundException::new);
		return ReviewGetDto.toDto(review);
	}

	public void deleteReview(Student student, Long reviewId) {
		Review review = reviewRepository.findByStudentIdAndReviewId(student.getId(), reviewId)
			.orElseThrow(ReviewEntityNotFoundException::new);
		checkReviewDateTooLate(review);

		reviewImageService.deleteUploadedFiles(review.getReviewImages());
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
