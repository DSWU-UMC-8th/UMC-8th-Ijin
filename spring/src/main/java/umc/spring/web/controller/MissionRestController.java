package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.MissionRequestDTO;
import umc.spring.apiPayload.code.MissionResponseDTO;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.CreateMissionResultDto> createMission(
            @PathVariable @ExistStore Long storeId,
            @RequestBody @Valid MissionRequestDTO.CreateMissionDto request
    ) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDto(mission));
    }
}