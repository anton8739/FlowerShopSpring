package by.yurovski.dao;


import by.yurovski.entity.Order;
import by.yurovski.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    @Override
    <S extends Order> S save(S s);
    List<Order> findAll();
    List<Order> findAllByUser(User user);
}
