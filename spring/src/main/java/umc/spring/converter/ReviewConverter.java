package umc.spring.converter;


import org.springframework.data.domain.Page;
import umc.spring.apiPayload.code.ReviewRequestDTO;
import umc.spring.apiPayload.code.ReviewResponseDTO;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;

import java.util.List;
import java.util.stream.Collectors;

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


    public static ReviewResponseDTO.MyReviewPreViewDTO myReviewPreViewDTO(Review review) {
        return ReviewResponseDTO.MyReviewPreViewDTO.builder()
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .title(review.getTitle())
                .build();
    }

    public static ReviewResponseDTO.MyReviewPreViewListDTO myReviewPreViewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.MyReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::myReviewPreViewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}