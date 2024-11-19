package com.delivery.api.domain.user.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.user.controller.model.UserLoginRequest;
import com.delivery.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.api.domain.user.controller.model.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;

    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입 처리 로직
     * 1. request dto -> entity (converter)
     * 2. entity -> save (service)
     * 3. save Entity -> response dto (converter)
     * 4. response return
     */
    public UserResponse register(@Valid UserRegisterRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);
        return response;

    /*      return Optional.ofNuallable(request)
                    .map(userConverter::toEntity)
                    .map(userService::register)
                    .map(userConverter::toResponse)
                    .orElseThrow() -> new ApiException(ErrorCode.NULL_POINT, "request is null")); */
        }

    /**
     * 로그인 로직
     * 1. email, password를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성 -> 일단은 코드로 확인
     * 4. token response
     */
    public UserResponse login(@Valid UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        // TODO : 토큰 생성

        return userConverter.toResponse(userEntity);
    }
}
