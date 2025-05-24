package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.ReviewRequestDTO;
import umc.spring.apiPayload.code.ReviewResponseDTO;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDto> createReview(
            @PathVariable @ExistStore Long storeId,
            @RequestBody @Valid ReviewRequestDTO.CreateReviewDto request
    ) {
        Review review = reviewCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateReviewResultDto(review));
    }
}