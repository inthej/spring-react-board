package com.board.backend.model;

import com.board.backend.common.ErrorCode;
import com.board.backend.common.utils.ObjectUtil;
import com.board.backend.common.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor(staticName = "of")
public class ErrorModel {
    private String code;
    private String message;
    private ExceptionInfo info;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor(staticName = "of")
    public static class ExceptionInfo {
        private String exceptionMessage;
        private String stackTrace;
    }

    public static ErrorModel of(ErrorCode code, Exception ex) {
        final String exceptionMessage = ObjectUtil.nonEmpty(ex) ? ex.getMessage()
                                                                : null;
        final String stackTrace = ObjectUtil.nonEmpty(ex) ? getStackTraceAsString(ex)
                                                          : null;
        final ExceptionInfo info = (StringUtil.nonEmpty(exceptionMessage) || StringUtil.nonEmpty(stackTrace)) ? ExceptionInfo.of(exceptionMessage, stackTrace)
                                                                                                              : null;
        return ErrorModel.of(code.name(), code.getMessage(), info);
    }

    private static String getStackTraceAsString(Exception ex) {
        final StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
