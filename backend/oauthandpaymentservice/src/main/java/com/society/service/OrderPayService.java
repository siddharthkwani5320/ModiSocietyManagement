package com.society.service;

public interface OrderPayService {
    String createOrder(int amount,String reciptId);
    String createPaymentLinkWithOrder(String orderId, String emailId, String contact, int amount);
}
