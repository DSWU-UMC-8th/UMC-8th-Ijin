package umc.spring.service.StoreService;

import umc.spring.apiPayload.code.StoreRequestDTO;
import umc.spring.domain.Store;

public interface StoreCommandService {
    Store createStore(Long regionId, StoreRequestDTO.CreateStoreDto request);
}