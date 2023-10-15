package com.board.backend.common;

import com.board.backend.common.utils.ValidationUtil;

public class SqlOrderBuilder {
    private final Sort.Order order;
    private final Sort.Direction direction;

    private SqlOrderBuilder(Sort.Order order, Sort.Direction direction) {
        this.order = ValidationUtil.validateNonNull(order, "Order cannot be null");
        this.direction = ValidationUtil.validateNonNull(direction, "Direction cannot be null");
    }

    public static SqlOrderBuilder create(Sort.Order order, Sort.Direction direction) {
        return new SqlOrderBuilder(order, direction);
    }

    public String getSql() {
        return order.name() + " " + direction.name();
    }
}
