package umc.spring.apiPayload.code;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateReviewDto {
        @NotBlank
        @Size(min = 1, max = 500)
        String title;

        @NotNull
        @DecimalMin(value = "0.0", message = "평점은 0.0 이상만 가능")
        @DecimalMax(value = "5.0", message = "평점은 5.0 이하만 가능")
        Float score;
    }
}