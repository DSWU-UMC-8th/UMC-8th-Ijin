package umc.spring.service.MissionService;

import umc.spring.apiPayload.code.MissionRequestDTO;
import umc.spring.domain.Mission;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.CreateMissionDto request);
}