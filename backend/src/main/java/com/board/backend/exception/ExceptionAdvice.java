package com.board.backend.exception;

import com.board.backend.common.ErrorCode;
import com.board.backend.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex, WebRequest request) {
        final ResponseModel responseModel = ResponseModel.failure(ErrorCode.SERVER_ERROR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseModel);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleTypeException(Exception ex) {
        final ResponseModel responseModel = ResponseModel.failure(ErrorCode.ARGUMENT_TYPE_MISMATCH, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleValidationException(MethodArgumentNotValidException ex) {
        final List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        final ResponseModel responseModel = ResponseModel.failure(ErrorCode.VALIDATION_FAILED, errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }
}
