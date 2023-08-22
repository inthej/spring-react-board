package com.board.backend.common;

import com.board.backend.common.utils.ValidationUtil;

import java.util.Objects;

public class SqlOrderBuilder {
    private final Sort.Column column;
    private final Sort.Direction direction;

    private SqlOrderBuilder(Sort.Column column, Sort.Direction direction) {
        this.column = ValidationUtil.validateNonNull(column, "Column cannot be null");
        this.direction = ValidationUtil.validateNonNull(direction, "Direction cannot be null");
    }

    public static SqlOrderBuilder createOrder(Sort.Column column, Sort.Direction direction) {
        return new SqlOrderBuilder(column, direction);
    }

    public String getSql() {
        return column.name() + " " + direction.name();
    }
}
