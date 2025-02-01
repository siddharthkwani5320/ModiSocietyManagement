package com.society.service;

import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.society.config.RazorpayConfig;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService implements OrderPayService {
    @Autowired
    RazorpayConfig razorpayConfig;

    public String createOrder(int amount,String reciptId) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayConfig.getKeyId(), razorpayConfig.getKeySecret());

            JSONObject orderRequest=new JSONObject();
            orderRequest.put("amount", amount * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", reciptId);
            orderRequest.put("payment_capture", 1);

            Order order = razorpayClient.orders.create(orderRequest);
            return order.get("id").toString();
        }catch (Exception e){
            System.out.println("Error Occurred creating payment");
        }

        return "";
    }

    public String createPaymentLinkWithOrder(String orderId, String customerEmail, String customerContact, int amount) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayConfig.getKeyId(), razorpayConfig.getKeySecret());

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount * 100); // Amount in paisa
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("description", "Maintenance Payment");
            paymentLinkRequest.put("customer", new JSONObject()
                    .put("name", "John Doe")
                    .put("email", customerEmail)
                    .put("contact", customerContact)
            );
            paymentLinkRequest.put("notify", new JSONObject()
                    .put("sms", true)
                    .put("email", true)
            );
            paymentLinkRequest.put("callback_url", "http://localhost:8080/api/payment/callback");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
            return paymentLink.get("short_url").toString();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error Occurred creating payment");
        }
        return "";
    }
}
