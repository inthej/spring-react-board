package com.board.backend.model;

import com.board.backend.common.ErrorCode;
import com.board.backend.common.utils.ObjectUtil;
import com.board.backend.common.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ErrorModel {
    private String code;
    private String message;
    private ErrorData data;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    private static class ErrorData {
        private String exceptionMessage;
        private String stackTrace;
    }

    public static ErrorModel of(ErrorCode code, Exception ex) {
        final String exceptionMessage = ObjectUtil.nonEmpty(ex) ? ex.getMessage() : null;
        final String stackTrace = ObjectUtil.nonEmpty(ex) ? getStackTraceAsString(ex) : null;
        final boolean hasData = StringUtil.nonEmpty(exceptionMessage) || StringUtil.nonEmpty(stackTrace);
        final ErrorData data = hasData ? ErrorData.of(exceptionMessage, stackTrace) : null;
        return ErrorModel.of(code.name(), code.getMessage(), data);
    }

    private static String getStackTraceAsString(Exception ex) {
        return Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
    }
}
