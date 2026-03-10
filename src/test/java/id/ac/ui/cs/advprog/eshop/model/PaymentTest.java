package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ABC123");

        java.util.List<Product> products = new java.util.ArrayList<>();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(1);

        products.add(product);

        order = new Order(
                "order-1",
                products,
                1708560000L,
                "Safira Sudrajat"
        );
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment(
                "payment-1",
                order,
                "VOUCHER_CODE",
                paymentData
        );

        assertEquals("payment-1", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("WAITING_PAYMENT", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment(
                "payment-1",
                order,
                "VOUCHER_CODE",
                paymentData
        );

        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment(
                "payment-1",
                order,
                "VOUCHER_CODE",
                paymentData
        );

        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusOtherValue() {
        Order order=org.mockito.Mockito.mock(Order.class);
        Payment payment=new Payment("PAY1",order,"BANK_TRANSFER",null);

        payment.setStatus("WAITING_PAYMENT");

        assertEquals("WAITING_PAYMENT",payment.getStatus());
    }
}