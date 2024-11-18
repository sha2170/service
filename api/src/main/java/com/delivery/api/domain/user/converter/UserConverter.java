package com.delivery.api.domain.user.converter;

import com.delivery.api.common.annotation.Converter;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    // UserRegisterRequest => UserEntity
    public UserEntity toEntity(UserRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it -> {
                    // to entity
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }
}