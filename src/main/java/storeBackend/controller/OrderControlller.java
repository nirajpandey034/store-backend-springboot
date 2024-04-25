package storeBackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import storeBackend.entity.OrderProduct;
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

        try {
            Integer orderId = orderUserService.placeOrder(order);
            orderProductService.placeOrder(order, orderId);
            return ResponseEntity.ok().body("{\"Order Placed \": \"" + orderId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/getOrder")
    public ResponseEntity<?> getAllOrders(@Param("userId") Integer userId) {
        List<Integer> list = new ArrayList<>();
        list = orderUserService.getOrderList(userId);

        List<OrderProduct> orderProductList = new ArrayList<>();
        orderProductList = orderProductService.getOrderProducts(list);

        return ResponseEntity.ok().body(orderProductList);
    }
}
