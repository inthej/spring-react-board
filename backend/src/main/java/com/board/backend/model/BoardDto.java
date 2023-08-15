package com.board.backend.model;

import com.board.backend.common.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDto {

    @Getter
    @Setter
    public static class Response {
        private long id;
        private String title;
        private String content;
        private String writer;
        private long view_count;
        private Long created_id;
        @JsonFormat(pattern = AppConstants.DATETIME_FORMAT)
        private LocalDateTime created_dt;
        @JsonFormat(pattern = AppConstants.DATETIME_FORMAT)
        private LocalDateTime modified_dt;
    }

    @Getter
    @Setter
    public static class Detail extends BoardDto.Response {
        private String password;
    }

    @Getter
    @Setter
    public static class Create {
        private Long id;

        @Size(max = 255)
        @NotBlank(message = "제목을 입력하세요")
        private String title;

        @NotBlank(message = "내용을 입력하세요")
        private String content;

        @Size(max = 255)
        @NotBlank(message = "작성자를 입력하세요")
        private String writer;

        @Size(max = 255)
        private String password;

        private Long created_id;
    }

    @Getter
    @Setter
    public static class Update {
        @Size(max = 255)
        @NotBlank(message = "제목을 입력하세요")
        private String title;

        @NotBlank(message = "내용을 입력하세요")
        private String content;

        @Size(max = 255)
        @NotBlank(message = "작성자를 입력하세요")
        private String writer;

        @Size(max = 255)
        private String password;
    }
}
