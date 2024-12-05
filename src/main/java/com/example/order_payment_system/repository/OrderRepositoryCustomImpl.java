package com.example.order_payment_system.repository;

import com.example.order_payment_system.entity.Order;
import com.example.order_payment_system.entity.QOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Order> findCriteria(LocalDate startDate, LocalDate endDate, String status) {
        QOrder order = QOrder.order;

        BooleanBuilder builder = new BooleanBuilder();

        if (startDate != null && endDate != null) {
            builder.and(order.orderDate.between(startDate, endDate));
        }

        if (status != null) {
            builder.and(order.status.eq(status));
        }

        return queryFactory.selectFrom(order)
                .where(builder)
                .fetch();
    }
}
