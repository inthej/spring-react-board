package com.board.backend.model;

import com.board.backend.common.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCommentDto {
    @Getter
    @Setter
    public static class Response {
        private long id;
        private long bid;
        private String content;
        private String writer;
        private Long created_id;
        @JsonFormat(pattern = AppConstants.DATETIME_FORMAT)
        private LocalDateTime created_dt;
        @JsonFormat(pattern = AppConstants.DATETIME_FORMAT)
        private LocalDateTime modified_dt;
    }

    @Getter
    @Setter
    public static class Detail extends BoardCommentDto.Response {
        @JsonIgnore
        private String password;
    }

    @Getter
    @Setter
    public static class ListItem extends BoardCommentDto.Response {
        private Long rownum;
    }

    @Getter
    @Setter
    public static class Create {
        private Long id;

        @NotBlank(message = "댓글 내용을 입력하세요")
        private String content;

        @Size(max = 255)
        @NotBlank(message = "댓글 작성자를 입력하세요")
        private String writer;

        @Size(max = 255)
        private String password;

        private Long created_id;
    }

    @Getter
    @Setter
    public static class Update {
        @NotBlank(message = "댓글 내용을 입력하세요")
        private String content;

        @Size(max = 255)
        @NotBlank(message = "댓글 작성자를 입력하세요")
        private String writer;

        @Size(max = 255)
        private String password;
    }

    @Getter
    @Setter
    public static class RequestList extends PageListDto.Request {
    }

    @Getter
    @Setter
    public static class ResponseList extends PageListDto.Response<BoardCommentDto.ListItem> {
        public ResponseList(long total, int pages, List<ListItem> list) {
            super(total, pages, list);
        }
    }
}
