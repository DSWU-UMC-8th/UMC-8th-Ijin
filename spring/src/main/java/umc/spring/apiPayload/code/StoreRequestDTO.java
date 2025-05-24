package umc.spring.apiPayload.code;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class StoreRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateStoreDto {
        @NotBlank
        @Size(min = 1, max = 50)
        String name;

        @NotBlank
        String address;

        @NotNull
        @DecimalMin(value = "0.0", message = "평점은 0.0 이상만 가능")
        @DecimalMax(value = "5.0", message = "평점은 5.0 이하만 가능")
        Float score;
    }
}