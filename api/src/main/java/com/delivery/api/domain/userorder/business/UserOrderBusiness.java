package com.delivery.api.domain.userorder.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.storemenu.service.StoreMenuService;
import com.delivery.api.domain.user.model.User;
import com.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import com.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import com.delivery.api.domain.userorder.converter.UserOrderConverter;
import com.delivery.api.domain.userorder.service.UserOrderService;
import com.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import com.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class UserOrderBusiness {

    private final UserOrderService userOrderService;

    private final UserOrderConverter userOrderConverter;

    private final StoreMenuService storeMenuService;

    private final UserOrderMenuConverter userOrderMenuConverter;

    private final UserOrderMenuService userOrderMenuService;


    // 사용자 주문 로직
    // 1. 사용자, 메뉴 id 리스트 받아오기
    // 2. UserOrder 생성
    // 3. UserOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, @Valid UserOrderRequest body) {
        // 받아온 메뉴가 유효한지 체크 => 유효하면 메뉴정보 가져옴.
        var storeMenuEntityList = body.getStoreMenuIdList()
                .stream()
                .map(storeMenuService::getStoreMenuWithThrow)
                .toList();

        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        // 주문 : UserOrder 생성 : user_order 테이블에 데이터 save
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 매핑 : UserOrderMenu 생성 : user_order_menu 테이블에 데이터 save
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    // user order = menu
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(
                            newUserOrderEntity,
                            it
                    );

                    return userOrderMenuEntity;
                })
                .toList(); // .collect(Collectors.toList();

        // 주문 내역 기록 남기기 (user_order_menu 테이블에 저장)
        userOrderMenuEntityList.forEach(userOrderMenuService::order);

        //to Response
        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}