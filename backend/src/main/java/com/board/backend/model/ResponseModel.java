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

    public static <T> ResponseModel<T> of(boolean success, T data) {
        return ResponseModel.of(success, data, null);
    }

    public static ResponseModel of(boolean success, ErrorCode code) {
        return ResponseModel.of(success, null, ErrorModel.of(code.name(), code.getMessage(), null));
    }

    public static ResponseModel of(boolean success, ErrorCode code, Exception ex) {
        return ResponseModel.of(success, null, code, ex);
    }

    public static <T> ResponseModel<T> of(boolean success, T data, ErrorCode code, Exception ex) {
        final ErrorModel error = (code != null) ? ErrorModel.of(code, ex) : null;
        return ResponseModel.of(success, data, error);
    }
}
