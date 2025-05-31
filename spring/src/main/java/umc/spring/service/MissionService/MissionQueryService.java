package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;

public interface MissionQueryService {
    Page<Mission> getStoreMissionList(Long storeId, Integer page);
    Page<MemberMission> getMyMissionList(Long memberId, Integer page);
}