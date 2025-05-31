package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.apiPayload.code.MissionRequestDTO;
import umc.spring.apiPayload.code.MissionResponseDTO;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .storeName(mission.getStore().getName())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.MyMissionPreViewDTO myMissionPreViewDTO(MemberMission memberMission) {
        return MissionResponseDTO.MyMissionPreViewDTO.builder()
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .build();
    }

    public static MissionResponseDTO.MyMissionPreViewListDTO myMissionPreViewListDTO(Page<MemberMission> memberMissionList) {
        List<MissionResponseDTO.MyMissionPreViewDTO> myMissionPreViewDTOList = memberMissionList.stream()
                .map(MissionConverter::myMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MyMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(myMissionPreViewDTOList.size())
                .missionList(myMissionPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.CompleteMissionResultDTO toCompleteMissionResultDTO(MemberMission memberMission) {
        return MissionResponseDTO.CompleteMissionResultDTO.builder()
                .missionId(memberMission.getMission().getId())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .status(memberMission.getStatus())
                .completedAt(LocalDate.now())
                .build();
    }
}