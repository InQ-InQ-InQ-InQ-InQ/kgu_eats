package kgueats.domain.review.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.review.model.dto.ReviewGetDto;
import kgueats.domain.review.model.dto.ReviewPatchDto;
import kgueats.domain.review.model.dto.ReviewPostDto;
import kgueats.domain.review.service.ReviewImageService;
import kgueats.domain.review.service.ReviewService;
import kgueats.exception.ExceptionController;

@RestController
@RequiredArgsConstructor
public class ReviewController extends ExceptionController {

	private final AuthService authService;
	private final ReviewService reviewService;
	private final ReviewImageService reviewImageService;

	@PostMapping("/reviews")
	public ReviewGetDto postReview(
		@RequestParam(value = "orderMenuHistoryId") Long orderMenuHistoryId,
		@RequestParam(value = "content") String content,
		@RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception {
		Student student = authService.getAuthStudent();
		return reviewService.saveReview(student, new ReviewPostDto(orderMenuHistoryId, content), images);
	}

	@PatchMapping("/reviews/{reviewId}")
	public ReviewGetDto updateReview(
		@PathVariable(value = "reviewId") Long reviewId,
		@RequestParam(value = "content") String content,
		@RequestPart(value = "images", required = false) List<MultipartFile> images) throws Exception {
		Student student = authService.getAuthStudent();
		return reviewService.updateReview(student, reviewId, new ReviewPatchDto(content), images);
	}

	@GetMapping("/reviews/store/{storeId}")
	public List<ReviewGetDto> getReviewList(
		@PathVariable(value = "storeId") Long storeId) {
		return reviewService.getReviewList(storeId);
	}

	@GetMapping("/reviews/{reviewId}")
	public ReviewGetDto getReview(
		@PathVariable(value = "reviewId") Long reviewId) {
		return reviewService.getReview(reviewId);
	}

	@DeleteMapping("/reviews/{reviewId}")
	public String deleteReview(
		@PathVariable(value = "reviewId") Long reviewId) {
		Student student = authService.getAuthStudent();
		reviewService.deleteReview(student, reviewId);
		return "success";
	}

	@DeleteMapping("/reviews/images/{reviewImageId}")
	public String deleteReviewImage(
		@PathVariable(value = "reviewImageId") Long reviewImageId) {
		Long studentId = authService.getAuthStudentId();
		reviewImageService.deleteReviewImage(studentId, reviewImageId);
		return "success";
	}

	@GetMapping("/reviews/images/{reviewImageId}")
	public byte[] getReviewImage(
		@PathVariable(value = "reviewImageId") Long reviewImageId) throws IOException {
		Long studentId = authService.getAuthStudentId();
		return reviewImageService.getReviewImageAsBytes(studentId, reviewImageId);
	}

}
