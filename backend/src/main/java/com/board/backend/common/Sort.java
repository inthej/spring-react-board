package com.board.backend.common;

public interface Sort {
    enum Column {
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
