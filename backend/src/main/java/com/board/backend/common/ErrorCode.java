package com.board.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BOARD_NOT_FOUND("글이 존재하지 않습니다");

    private String message;
}
