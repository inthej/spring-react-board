package com.board.backend.model;

import com.board.backend.common.AppConstants;
import com.board.backend.common.Sort;
import lombok.Data;

import java.util.List;

public class PageListDto {
    @Data
    public static class Response<T> {
        private final long total;
        private final int pages;
        private final List<T> list;
    }

    @Data
    public static abstract class Request {
        private int page;
        private int size;
        private Sort.Column column = Sort.Column.id;
        private Sort.Direction direction = Sort.Direction.asc;

        public void setPage(int page) {
            this.page = Math.max(page, AppConstants.DEFAULT_PAGE);
        }

        public void setSize(int size) {
            this.size = size > 0 ? size : AppConstants.DEFAULT_PAGE_SIZE;
        }
    }
}
