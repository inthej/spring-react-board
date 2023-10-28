package com.board.backend.common;

public interface Sort {
    enum Order {
        no,
        title,
        content,
        writer,
        view_count,
    }

    enum Direction {
        asc, desc
    }
}
