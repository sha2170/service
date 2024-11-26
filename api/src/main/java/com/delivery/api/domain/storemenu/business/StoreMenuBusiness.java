package com.delivery.api.domain.storemenu.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import com.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import com.delivery.api.domain.storemenu.service.StoreMenuService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;

    private final StoreMenuConverter storeMenuConverter;

    // 가게 메뉴 등록
    public StoreMenuResponse register(
            StoreMenuRegisterRequest request
    ){
        // req -> entity : save -> response
        var entity = storeMenuConverter.toEntity(request);
        var newEntity = storeMenuService.register(entity);
        var response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    // 특정 가게에 있는 메뉴 검색
    public List<StoreMenuResponse> search(
            Long storeId
    ){
        var list = storeMenuService.getStoreMenuByStoreId(storeId);

        // entity list -> response list 변환
        return list.stream()
                .map(storeMenuConverter::toResponse)
                .collect(Collectors.toList());
        /*return list.stream()
                .map( it -> {
                    return storeMenuConverter.toResponse(it);
                })
                .collect(Collectors.toList());*/
    }
}