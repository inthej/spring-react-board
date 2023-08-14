package com.board.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SERVER_ERROR("서버 에러"),
    ARGUMENT_TYPE_MISMATCH("잘못된 파라미터타입이 전달되었습니다."),
    // DB
    BOARD_NOT_FOUND("글이 존재하지 않습니다");

    private String message;
}
