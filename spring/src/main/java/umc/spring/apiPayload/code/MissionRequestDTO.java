package umc.spring.apiPayload.code;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateMissionDto {
        @NotNull
        Integer reward;

        @NotNull
        @Future
        LocalDate deadline;

        @NotBlank
        String missionSpec;
    }
}