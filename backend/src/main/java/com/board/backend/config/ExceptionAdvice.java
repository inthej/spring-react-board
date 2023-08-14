package com.board.backend.config;

import com.board.backend.common.ErrorCode;
import com.board.backend.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex, WebRequest request) {
        final ResponseModel responseModel = ResponseModel.of(false, ErrorCode.SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleTypeException(Exception ex) {
        final ResponseModel<Object> responseModel = ResponseModel.of(false, ErrorCode.ARGUMENT_TYPE_MISMATCH,  ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }
}
