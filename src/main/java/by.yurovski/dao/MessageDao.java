package by.yurovski.dao;


import by.yurovski.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao  extends JpaRepository<Message, Long>  {
    @Override
    <S extends Message> S save(S s);
    List<Message> findAll();
}
