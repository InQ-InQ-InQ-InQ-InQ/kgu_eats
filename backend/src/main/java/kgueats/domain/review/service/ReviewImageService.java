package kgueats.domain.review.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import kgueats.domain.review.exception.ReviewImageEntityNotFoundException;
import kgueats.domain.review.model.entity.Review;
import kgueats.domain.review.model.entity.ReviewImage;
import kgueats.domain.review.repository.ReviewImageRepository;

@Service
@RequiredArgsConstructor
public class ReviewImageService {

	private final ReviewImageRepository reviewImageRepository;

	@Value("${file.upload.review.directory}")
	private String savePath;

	public void uploadImages(Review review, List<MultipartFile> images) throws Exception {
		List<ReviewImage> reviewImages = this.parseFileInfo(images);
		reviewImages.stream().forEach(reviewImage -> {
			review.appendReviewImage(reviewImage);
			saveReviewImage(reviewImage);
		});
	}

	private void saveReviewImage(ReviewImage reviewImage) {
		reviewImageRepository.save(reviewImage);
	}

	private List<ReviewImage> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {
		List<ReviewImage> fileList = new ArrayList<>();

		if (!CollectionUtils.isEmpty(multipartFiles)) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String currentDate = now.format(dateTimeFormatter);

			String uploadPath = concatPath(System.getProperty("user.dir"), savePath, currentDate);

			File file = new File(uploadPath);
			if (!file.exists()) {
				if (!file.mkdirs()) {
					System.out.println("file: was not successful");
				}
			}

			for (MultipartFile multipartFile : multipartFiles) {
				String originalFileExtension;
				String contentType = multipartFile.getContentType();

				if (ObjectUtils.isEmpty(contentType)) {
					break;
				}

				if (contentType.contains("image/jpeg")) {
					originalFileExtension = ".jpg";
				} else if (contentType.contains("image/png")) {
					originalFileExtension = ".png";
				} else {
					break;
				}

				String newFileName = System.nanoTime() + originalFileExtension;

				ReviewImage reviewImage = ReviewImage.builder()
					.originFileName(multipartFile.getOriginalFilename())
					.filePath(concatPath(currentDate, newFileName))
					.fileSize(multipartFile.getSize())
					.build();
				fileList.add(reviewImage);


				file = new File(concatPath(uploadPath, newFileName));
				multipartFile.transferTo(file);

				file.setWritable(true);
				file.setReadable(true);
			}
		}

		return fileList;
	}

	private String concatPath(String...paths) {
		return String.join((File.separator), paths);
	}

	public void deleteUploadedFiles(List<ReviewImage> reviewImages) {
		reviewImages.forEach(this::deleteUploadedFile);
	}

	private void deleteUploadedFile(ReviewImage reviewImage) {
		File file = new File(concatPath(savePath, reviewImage.getFilePath()));
		if (file.exists()) {
			file.delete();
		}
	}

	public void deleteReviewImage(Long studentId, Long reviewImageId) {
		ReviewImage reviewImage = reviewImageRepository.findByStudentIdAndReviewImageId(studentId, reviewImageId)
			.orElseThrow(ReviewImageEntityNotFoundException::new);
		deleteUploadedFile(reviewImage);
		reviewImageRepository.delete(reviewImage);
	}

	public byte[] getReviewImageAsBytes(Long studentId, Long reviewImageId) throws IOException {
		ReviewImage reviewImage = reviewImageRepository.findByStudentIdAndReviewImageId(studentId, reviewImageId)
			.orElseThrow(ReviewImageEntityNotFoundException::new);
		return IOUtils.toByteArray(new FileInputStream(concatPath(savePath, reviewImage.getFilePath())));
	}

}
