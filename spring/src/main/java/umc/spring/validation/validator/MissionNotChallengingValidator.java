package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.NotChallengingMission;
import umc.spring.domain.enums.MissionStatus;

@Component
@RequiredArgsConstructor
public class MissionNotChallengingValidator implements ConstraintValidator<NotChallengingMission, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        // 사용자 ID 1
        return !memberMissionRepository.existsByMissionIdAndMemberIdAndStatus(missionId, 1L, MissionStatus.CHALLENGE);
    }
}