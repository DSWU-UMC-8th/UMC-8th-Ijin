package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.StoreRequestDTO;
import umc.spring.apiPayload.code.StoreResponseDTO;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}/stores")
    public ApiResponse<StoreResponseDTO.CreateStoreResultDto> createStore(
            @PathVariable @ExistRegion Long regionId,
            @RequestBody @Valid StoreRequestDTO.CreateStoreDto request
    ) {
        Store store = storeCommandService.createStore(regionId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDto(store));
    }
}