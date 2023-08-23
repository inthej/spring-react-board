package com.board.backend.common;

public interface Sort {
    enum Order {
        id,
        title,
        content,
        writer,
        view_count,
    }

    enum Direction {
        asc, desc
    }
}
