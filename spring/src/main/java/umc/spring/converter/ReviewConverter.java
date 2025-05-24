package umc.spring.converter;


import umc.spring.apiPayload.code.ReviewRequestDTO;
import umc.spring.apiPayload.code.ReviewResponseDTO;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewDto request, Store store, Member member) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .store(store)
                .member(member)
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResultDto toCreateReviewResultDto(Review review) {
        return ReviewResponseDTO.CreateReviewResultDto.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .build();
    }
}