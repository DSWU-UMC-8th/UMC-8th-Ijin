package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.MemberMissionResponseDTO;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.NotChallengingMission;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{userId}/missions/{missionId}")
    public ApiResponse<MemberMissionResponseDTO.ChallengeMissionResultDto> challengeMission(
            @PathVariable Long userId,
            @PathVariable @NotChallengingMission Long missionId
    ) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(userId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMissionResultDto(memberMission));
    }
}