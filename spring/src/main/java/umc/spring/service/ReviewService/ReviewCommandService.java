package umc.spring.service.ReviewService;

import umc.spring.apiPayload.code.ReviewRequestDTO;
import umc.spring.domain.Review;

public interface ReviewCommandService {
    Review createReview(Long storeId, ReviewRequestDTO.CreateReviewDto request);
}