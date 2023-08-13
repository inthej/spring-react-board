package com.board.backend.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        // JavaTimeModule을 ObjectMapper에 등록하여 Java 8의 LocalDate와 LocalDateTime 등의 날짜/시간 타입을 지원하게 합니다.
        objectMapper.registerModule(new JavaTimeModule());
        // 기본적인 날짜/시간 포맷 변경
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 객체의 필드 값이 null인 경우, JSON 출력 시 해당 필드를 제외하게 설정합니다.
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
