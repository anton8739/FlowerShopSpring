package by.yurovski.service;

import by.yurovski.dao.OrderItemDao;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;
    @Transactional
    public List<OrderItem> findAllByUser(User user){
        return orderItemDao.findAllByUser(user);
    }
}
