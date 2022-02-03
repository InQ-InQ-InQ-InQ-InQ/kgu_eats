package kgueats.domain.review.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "review_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_image_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "review_id")
	private Review review;

	private String originFileName;

	private String filePath;

	private Long fileSize;

	@Builder
	public ReviewImage(String originFileName, String filePath, Long fileSize) {
		this.originFileName = originFileName;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}

	public void assignReview(Review review) {
		this.review = review;
	}

}
