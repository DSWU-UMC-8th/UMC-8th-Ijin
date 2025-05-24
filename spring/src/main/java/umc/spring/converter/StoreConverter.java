package umc.spring.converter;


import umc.spring.apiPayload.code.StoreRequestDTO;
import umc.spring.apiPayload.code.StoreResponseDTO;
import umc.spring.domain.Region;
import umc.spring.domain.Store;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.CreateStoreDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(request.getScore())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.CreateStoreResultDto toCreateStoreResultDto(Store store) {
        return StoreResponseDTO.CreateStoreResultDto.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .build();
    }
}