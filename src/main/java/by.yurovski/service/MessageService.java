package by.yurovski.service;

import by.yurovski.dao.MessageDao;
import by.yurovski.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDao messageDao;

    @Transactional
    public <S extends Message> S save(S s){
        return messageDao.save(s);
    }
    @Transactional
    public List<Message> findAll(){
        return messageDao.findAll();
    }
}
