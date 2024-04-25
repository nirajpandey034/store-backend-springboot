package storeBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import storeBackend.dao.OrderProductRepository;
import storeBackend.dao.ProductRepository;
import storeBackend.entity.OrderProduct;
import storeBackend.entity.Product;

@Service
public class OrderProductService {

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Integer> placeOrder(Map<String, String> order, Integer orderId) {

        ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper
        List<Integer> arr = new ArrayList<>();
        try {
            arr = objectMapper.readValue(order.get("products"), new TypeReference<ArrayList<Integer>>() {
            });

            // listing all the products in a list, using ids passed in request
            List<Product> list = new ArrayList<>();
            list = productRepository.findAllById(arr);

            // creating a list of orderProduct type
            List<OrderProduct> orderList = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {

                OrderProduct tempMap = new OrderProduct(orderId, list.get(i));

                orderList.add(tempMap);
            }
            // inserting in bulk the above created list of orderProduct
            orderProductRepository.saveAll(orderList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public List<OrderProduct> getOrderProducts(List<Integer> orderList) {

        return orderProductRepository.findByOrderIdIn(orderList);

    }
}
