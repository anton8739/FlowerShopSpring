package by.yurovski.dao;

import by.yurovski.entity.OrderItem;
import by.yurovski.entity.Product;
import by.yurovski.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderItemDao  extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByUser(User user);
    OrderItem findById(int id);


    void deleteById(int id);
}
