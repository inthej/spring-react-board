package com.board.backend.model;

import com.board.backend.common.AppConstants;
import com.board.backend.common.Sort;
import com.board.backend.common.utils.ObjectUtil;
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
        private int page = AppConstants.DEFAULT_PAGE;
        private int size = AppConstants.DEFAULT_PAGE_SIZE;
        private Sort.Order order = Sort.Order.id;
        private Sort.Direction direction = Sort.Direction.desc;

        public void setPage(int page) {
            this.page = (page > 0) ? page : AppConstants.DEFAULT_PAGE;
        }

        public void setSize(int size) {
            this.size = (size > 0) ? size : AppConstants.DEFAULT_PAGE_SIZE;
        }

        public void setOrder(Sort.Order order) {
            this.order = ObjectUtil.nonEmpty(order) ? order : Sort.Order.id;
        }

        public void setDirection(Sort.Direction direction) {
            this.direction = ObjectUtil.nonEmpty(direction) ? direction : Sort.Direction.desc;
        }
    }
}
