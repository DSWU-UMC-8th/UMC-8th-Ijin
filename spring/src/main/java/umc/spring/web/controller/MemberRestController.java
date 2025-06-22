package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.MemberRequestDTO;
import umc.spring.apiPayload.code.MemberResponseDTO;
import umc.spring.apiPayload.code.MissionResponseDTO;
import umc.spring.apiPayload.code.ReviewResponseDTO;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.service.ReviewService.*;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.service.MissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.ExistMember;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberQueryService memberQueryService;

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
            description = "유저가 내 정보를 조회하는 API입니다.",
            security = { @SecurityRequirement(name = "JWT TOKEN") }
    )
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API",description = "유저가 로그인하는 API입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }


    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. 한 페이지당 10개씩 조회됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지는 1 이상이어야 합니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.")
    })
    public ApiResponse<ReviewResponseDTO.MyReviewPreViewListDTO> getMyReviews(
            @PathVariable(name = "memberId") @ExistMember Long memberId,
            @Parameter(description = "페이지 번호. 1부터 시작합니다.") @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewList = reviewQueryService.getMyReviewList(memberId, page);
        return ApiResponse.onSuccess(ReviewConverter.myReviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "특정 회원이 진행중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. 한 페이지당 10개씩 조회됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이지는 1 이상이어야 합니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.MyMissionPreViewListDTO> getMyMissions(
            @PathVariable(name = "memberId") @ExistMember Long memberId,
            @Parameter(description = "페이지 번호, 1부터 시작합니다") @CheckPage @RequestParam(name = "page") Integer page) {

        Page<MemberMission> memberMissionList = missionQueryService.getMyMissionList(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.myMissionPreViewListDTO(memberMissionList));
    }

    @PatchMapping("/{memberId}/missions/{missionId}/complete")
    @Operation(summary = "진행 중인 미션 완료로 바꾸기 API", description = "진행 중인 미션을 완료 상태로 변경하는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "미션을 찾을 수 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4003", description = "이미 완료된 미션입니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4004", description = "진행 중인 미션이 아닙니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.")
    })
    public ApiResponse<MissionResponseDTO.CompleteMissionResultDTO> completeMission(
            @PathVariable(name = "memberId") @ExistMember Long memberId,
            @PathVariable(name = "missionId") Long missionId) {

        MemberMission completedMission = memberMissionCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess(MissionConverter.toCompleteMissionResultDTO(completedMission));
    }
}