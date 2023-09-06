package com.board.backend.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    SERVER_ERROR("서버 오류 발생"),
    ARGUMENT_TYPE_MISMATCH("파라미터 타입 불일치"),
    VALIDATION_FAILED("입력값 검증 실패"),
    INVALID_JSON("잘못된 JSON 형식"),
    UNKNOWN_FIELD("알 수 없는 필드 전달"),
    INVALID_ARGUMENT("잘못된 인자 전달됨"),
    PASSWORD_MISMATCH("비밀번호 불일치"),
    // DB
    BOARD_NOT_FOUND("글을 찾을 수 없음"),
    BOARD_REPLY_NOT_FOUND("댓글을 찾을 수 없음");

    private String message;
}
