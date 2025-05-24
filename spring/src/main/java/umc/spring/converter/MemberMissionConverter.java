package umc.spring.converter;

import umc.spring.apiPayload.code.MemberMissionResponseDTO;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.enums.MissionStatus;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGE)
                .build();
    }

    public static MemberMissionResponseDTO.ChallengeMissionResultDto toChallengeMissionResultDto(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeMissionResultDto.builder()
                .memberMissionId(memberMission.getId())
                .status(memberMission.getStatus().toString())
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .build();
    }
}