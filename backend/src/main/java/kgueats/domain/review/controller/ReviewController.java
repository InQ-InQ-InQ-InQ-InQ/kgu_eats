package kgueats.domain.review.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import kgueats.domain.auth.service.AuthService;
import kgueats.domain.member.model.entity.Student;
import kgueats.domain.review.model.dto.ReviewGetDto;
import kgueats.domain.review.model.dto.ReviewPatchDto;
import kgueats.domain.review.model.dto.ReviewPostDto;
import kgueats.domain.review.service.ReviewService;
import kgueats.exception.ExceptionController;

@RestController
@RequiredArgsConstructor
public class ReviewController extends ExceptionController {

	private final AuthService authService;
	private final ReviewService reviewService;

	@PostMapping("/reviews")
	public ReviewGetDto postReview(
		@RequestBody ReviewPostDto reviewPostDto) {
		Student student = authService.getAuthStudent();
		return reviewService.saveReview(student, reviewPostDto);
	}

	@PatchMapping("/reviews/{reviewId}")
	public ReviewGetDto updateReview(
		@PathVariable(value = "reviewId") Long reviewId,
		@RequestBody ReviewPatchDto reviewPatchDto) {
		Student student = authService.getAuthStudent();
		return reviewService.updateReview(student, reviewId, reviewPatchDto);
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

}
