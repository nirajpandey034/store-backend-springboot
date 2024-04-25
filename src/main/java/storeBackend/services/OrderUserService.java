package storeBackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import storeBackend.dao.OrderUserRepository;
import storeBackend.dao.UserRepository;
import storeBackend.entity.OrderUser;
import storeBackend.entity.User;

@Service
public class OrderUserService {

    @Autowired
    OrderUserRepository orderUserRepository;

    @Autowired
    UserRepository userRepository;

    public Integer placeOrder(Map<String, String> order) {
        User user = userRepository.findById(Integer.parseInt(order.get("userId")))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        OrderUser orderUser = new OrderUser();
        orderUser.setUser(user);
        orderUser = orderUserRepository.save(orderUser);
        return orderUser.getOrder_user_id();
    }

    public List<Integer> getOrderList(Integer userId) {
        List<OrderUser> list = new ArrayList<>();

        list = orderUserRepository.findAllByUserId(userId);

        List<Integer> orderIds = new ArrayList<>();
        orderIds = list.stream().map(item -> item.getOrder_user_id())
                .collect(Collectors.toList());

        return orderIds;
    }
}
