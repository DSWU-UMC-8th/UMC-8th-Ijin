package umc.spring.converter;

import umc.spring.apiPayload.code.MissionRequestDTO;
import umc.spring.apiPayload.code.MissionResponseDTO;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.CreateMissionDto request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.CreateMissionResultDto toCreateMissionResultDto(Mission mission) {
        return MissionResponseDTO.CreateMissionResultDto.builder()
                .missionId(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }
}