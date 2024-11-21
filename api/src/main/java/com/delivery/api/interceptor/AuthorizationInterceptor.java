package com.delivery.api.interceptor;

import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.error.TokenErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.token.business.TokenBusiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, chrome의 경우 GET, POST OPTIONS = pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // js, html, png recource를 요청하는 경우 = pass
        if(handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // TODO: header 검증
        var accssToken = request.getHeader("authorization-token");
        if(accssToken==null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUNT);
        }

        var userId = tokenBusiness.validationAccessToken(accssToken);

        if(userId != null){
            // context에 저장
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);

            return true; //토큰이 유효한 경우에만 통과 처리
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");
    }
}