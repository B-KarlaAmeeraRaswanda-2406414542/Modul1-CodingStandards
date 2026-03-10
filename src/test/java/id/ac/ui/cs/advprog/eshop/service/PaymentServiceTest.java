package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;
    Map<String,String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);

        products.add(product);

        order = new Order(
                "13652556-012a-4c07-b546-54eb1396d79b",
                products,
                1708560000L,
                "Safira Sudrajat"
        );

        paymentData = new HashMap<>();
        paymentData.put("bank","BCA");
    }

    @Test
    void testAddPayment() {

        Payment payment = new Payment(
                "payment-1",
                order,
                "BANK_TRANSFER",
                paymentData
        );

        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order,"BANK_TRANSFER",paymentData);

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(order.getId(), result.getOrder().getId());
    }

    @Test
    void testGetPaymentFound() {

        Payment payment = new Payment(
                "payment-1",
                order,
                "BANK_TRANSFER",
                paymentData
        );

        doReturn(payment).when(paymentRepository).findById("payment-1");

        Payment result = paymentService.getPayment("payment-1");

        assertEquals("payment-1", result.getId());
    }

    @Test
    void testGetPaymentNotFound() {

        doReturn(null).when(paymentRepository).findById("zzz");

        Payment result = paymentService.getPayment("zzz");

        assertNull(result);
    }

    @Test
    void testGetAllPayments() {

        List<Payment> payments = new ArrayList<>();

        payments.add(new Payment("1",order,"BANK_TRANSFER",paymentData));
        payments.add(new Payment("2",order,"BANK_TRANSFER",paymentData));

        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();

        assertEquals(2, results.size());
    }
}