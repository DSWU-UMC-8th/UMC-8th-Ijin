package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.MissionRequestDTO;
import umc.spring.apiPayload.code.MissionResponseDTO;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.*;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.CreateMissionResultDto> createMission(
            @PathVariable @ExistStore Long storeId,
            @RequestBody @Valid MissionRequestDTO.CreateMissionDto request
    ) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toCreateMissionResultDto(mission));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. 한 페이지당 10개씩 조회됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지는 1 이상이어야 합니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "가게를 찾을 수 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getStoreMissions(
            @PathVariable(name = "storeId") @ExistStore Long storeId,
            @Parameter(description = "페이지 번호. 1부터 시작합니다.") @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Mission> missionList = missionQueryService.getStoreMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(missionList));
    }
}