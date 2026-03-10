package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {

    String id;
    Order order;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = "WAITING_PAYMENT";
    }

    public void setStatus(String status) {
        this.status = status;

        if (status.equals("SUCCESS")) {
            order.setStatus("SUCCESS");
        }
        else if (status.equals("REJECTED")) {
            order.setStatus("FAILED");
        }
    }
}