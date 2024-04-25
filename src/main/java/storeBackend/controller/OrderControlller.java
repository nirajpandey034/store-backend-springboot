package storeBackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import storeBackend.services.OrderProductService;
import storeBackend.services.OrderUserService;

@Controller
@RequestMapping("/api/order")
public class OrderControlller {

    @Autowired
    public OrderProductService orderProductService;

    @Autowired
    public OrderUserService orderUserService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, String> order) {

        Integer orderId = orderUserService.placeOrder(order);
        orderProductService.placeOrder(order, orderId);
        return ResponseEntity.ok().body("{\"Order Placed \": \"" + orderId + "\"}");

    }

}
