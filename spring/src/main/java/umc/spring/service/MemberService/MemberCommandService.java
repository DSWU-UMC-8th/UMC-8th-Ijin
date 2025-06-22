package umc.spring.service.MemberService;

import umc.spring.apiPayload.code.MemberRequestDTO;
import umc.spring.apiPayload.code.MemberResponseDTO;
import umc.spring.domain.Member;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}