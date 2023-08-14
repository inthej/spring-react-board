package com.board.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SERVER_ERROR("서버 오류가 발생하였습니다."),
    ARGUMENT_TYPE_MISMATCH("전달된 파라미터의 타입이 올바르지 않습니다."),
    VALIDATION_FAILED("입력값의 검증에 실패하였습니다."),
    // DB
    BOARD_NOT_FOUND("해당 글을 찾을 수 없습니다.");

    private String message;
}
