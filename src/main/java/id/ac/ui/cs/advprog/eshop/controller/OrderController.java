package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    private PaymentService paymentService;

    @GetMapping("/create")
    public String createOrderPage() {
        return "createOrder";
    }

    @GetMapping("/history")
    public String historyPage() {
        return "orderHistory";
    }

    @PostMapping("/history")
    public String postHistory(@RequestParam("author") String author, Model model) {
        List<Order> orders = orderService.findAllByAuthor(author);
        model.addAttribute("orders", orders);
        return "orderHistory";
    }

    @GetMapping("/pay/{orderId}")
    public String payOrderPage(@PathVariable String orderId,Model model){
        Order order=orderService.findById(orderId);
        model.addAttribute("order",order);
        return "orderPay";
    }

    @PostMapping("/pay/{orderId}")
    public String payOrder(@PathVariable String orderId,
                           @RequestParam String method,
                           @RequestParam(required=false) String voucherCode,
                           @RequestParam(required=false) String bankName,
                           @RequestParam(required=false) String referenceCode,
                           Model model){
        Order order=orderService.findById(orderId);
        Map<String,String> paymentData=new HashMap<>();
        if(voucherCode!=null)paymentData.put("voucherCode",voucherCode);
        if(bankName!=null)paymentData.put("bankName",bankName);
        if(referenceCode!=null)paymentData.put("referenceCode",referenceCode);

        Payment payment=paymentService.addPayment(order,method,paymentData);
        model.addAttribute("payment",payment);
        return "paymentDetail";
    }
}