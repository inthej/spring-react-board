package com.board.backend.model;

import com.board.backend.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ResponseModel<T> {
    private boolean success;
    private T data;
    private ErrorModel error;

    public static <T> ResponseModel<T> success(T data) {
        return ResponseModel.of(true, data, null);
    }

    public static ResponseModel failure(ErrorCode code) {
        return ResponseModel.of(false, null, ErrorModel.of(code.name(), code.getMessage(), null));
    }

    public static ResponseModel failure(ErrorCode code, String errorMessage) {
        return ResponseModel.of(false, null, ErrorModel.of(code.name(), errorMessage, null));
    }

    public static ResponseModel failure(ErrorCode code, List<String> errors) {
        final String errorMessage = String.join(", ", errors);
        return ResponseModel.of(false, null, ErrorModel.of(code.name(), errorMessage, null));
    }

    public static ResponseModel failure(ErrorCode code, Exception ex) {
        return ResponseModel.of(false, null, ErrorModel.of(code, ex));
    }
}
