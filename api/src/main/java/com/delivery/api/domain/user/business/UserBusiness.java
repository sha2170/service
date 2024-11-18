package com.delivery.api.domain.user.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.api.domain.user.controller.model.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입 처리 로직
     * 1. request dto -> entity (converter)
     * 2. entity -> save  (service)
     * 3. save Entity -> response dto (converter)
     * 4. response return
     */
    public UserResponse register(@Valid UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);

    }
}