package by.yurovski.service;

import by.yurovski.dao.OrderDao;
import by.yurovski.entity.Order;
import by.yurovski.entity.OrderItem;
import by.yurovski.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    @Transactional
    public Order saveAndFlush(Order order){
        return orderDao.saveAndFlush(order);
    }
    @Transactional
    public List<Order> findAll(){
        return orderDao.findAll();
    }
    @Transactional
    public List<Order> findAllByUser(User user){
        return orderDao.findAllByUser(user);
    }
}
