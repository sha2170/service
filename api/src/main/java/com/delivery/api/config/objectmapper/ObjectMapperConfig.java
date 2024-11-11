package com.delivery.api.config.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {

        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); // jdk 8 버전 이후 클래스

        objectMapper.registerModule(new JavaTimeModule()); // local date

        // 모르는 JSON field에 대해서는 값을 무시. 나머지만 파싱
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 빈 객체(필드가 없는 객체)를 직렬화할 때 오류 없이 빈 JSON으로 처리하도록 설정
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Java의 날짜 및 시간 타입(예 : LocalDate, LocalDateTime)을
        // 타임스태프 형식이 아닌 ISO-8601 문자열 형식으로 직렬화하도록 설정함
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new  PropertyNamingStrategies.SnakeCaseStrategy());

        return objectMapper;
    }
}
