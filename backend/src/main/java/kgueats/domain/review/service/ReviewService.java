package kgueats.domain.review.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import kgueats.domain.member.model.entity.Student;
import kgueats.domain.review.model.dto.ReviewGetDto;
import kgueats.domain.review.model.dto.ReviewPostDto;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.review.repository.ReviewRepository;
import kgueats.domain.store.model.entity.Menu;
import kgueats.domain.store.model.entity.Store;
import kgueats.domain.store.service.StoreService;

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

}
