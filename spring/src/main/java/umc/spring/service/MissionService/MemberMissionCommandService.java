package umc.spring.service.MissionService;

import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMission challengeMission(Long userId, Long missionId);

    MemberMission completeMission(Long memberId, Long missionId);
}