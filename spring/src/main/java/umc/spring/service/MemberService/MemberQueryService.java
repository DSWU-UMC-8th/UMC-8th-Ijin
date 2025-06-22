package umc.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.apiPayload.code.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}