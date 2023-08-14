package com.board.backend.model;

import com.board.backend.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ResponseModel<T> {
    private boolean success;
    private T data;
    private ErrorModel error;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    private static class ErrorModel {
        private String code;
        private String message;
    }

    public static <T> ResponseModel<T> of(boolean success, T data) {
        return ResponseModel.of(success, data, null);
    }

    public static ResponseModel of(boolean success, ErrorCode code) {
        return ResponseModel.of(success, null, ErrorModel.of(code.name(), code.getMessage()));
    }
}
