package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private PaymentService paymentService;

    @Test
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createOrder"));
    }

    @Test
    void testHistoryPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderHistory"));
    }

    @Test
    void testPostHistory() throws Exception {
        List<Order> orders = new ArrayList<>();
        when(orderService.findAllByAuthor("Safira")).thenReturn(orders);
        mockMvc.perform(post("/order/history")
                        .param("author", "Safira"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("orders"))
                .andExpect(view().name("orderHistory"));
    }

    @Test
    void testPayOrderPage() throws Exception {
        Product product=new Product();
        Order order=new Order("1",List.of(product),1L,"Safira");
        when(orderService.findById("1")).thenReturn(order);
        mockMvc.perform(get("/order/pay/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("order"))
                .andExpect(view().name("orderPay"));
    }

    @Test
    void testPayOrderVoucher() throws Exception {
        Order order=org.mockito.Mockito.mock(Order.class);
        Payment payment=new Payment("p1",order,"VOUCHER_CODE",Map.of("voucherCode","ESHOP12345678AB"));
        when(orderService.findById("1")).thenReturn(order);
        when(paymentService.addPayment(any(),any(),any())).thenReturn(payment);

        mockMvc.perform(post("/order/pay/1")
                        .param("method","VOUCHER_CODE")
                        .param("voucherCode","ESHOP12345678AB"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("paymentDetail"));
    }

    @Test
    void testPayOrderBankTransfer() throws Exception {
        Order order=org.mockito.Mockito.mock(Order.class);
        Payment payment=new Payment("p2",order,"BANK_TRANSFER",Map.of("bankName","BCA","referenceCode","123"));
        when(orderService.findById("1")).thenReturn(order);
        when(paymentService.addPayment(any(),any(),any())).thenReturn(payment);

        mockMvc.perform(post("/order/pay/1")
                        .param("method","BANK_TRANSFER")
                        .param("bankName","BCA")
                        .param("referenceCode","123"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("paymentDetail"));
    }

    @Test
    void testPayOrderWithoutOptionalParams() throws Exception {
        Order order=org.mockito.Mockito.mock(Order.class);
        Payment payment=new Payment("p3",order,"COD",Map.of());
        when(orderService.findById("1")).thenReturn(order);
        when(paymentService.addPayment(any(),any(),any())).thenReturn(payment);

        mockMvc.perform(post("/order/pay/1")
                        .param("method","COD"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("payment"))
                .andExpect(view().name("paymentDetail"));
    }
}