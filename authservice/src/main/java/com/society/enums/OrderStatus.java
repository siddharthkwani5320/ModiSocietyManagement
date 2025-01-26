package com.society.enums;

import com.society.entity.Order;

public enum OrderStatus {
    CREATED(1),
    COMPLETED(2),
    FAILED(3);
    private int statusCode;
    OrderStatus(int code){
        this.statusCode = code;
    }

    public OrderStatus getOrderStatus(int statusCode){
        switch (statusCode){
            case 1: return CREATED;
            case 2: return COMPLETED;
            case 3: return FAILED;
            default: return CREATED;
        }
    }
}
