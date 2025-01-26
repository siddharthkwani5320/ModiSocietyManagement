package com.society.entity;

import com.society.enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    String orderId;
    OrderStatus status;
    LocalDateTime createdAt;
}
