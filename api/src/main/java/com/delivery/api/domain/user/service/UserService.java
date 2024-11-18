package com.delivery.api.domain.user.service;

import com.delivery.db.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service    // 해당 도메인만 처리
public class UserService {

    private final UserRepository userRepository;
}