package by.yurovski.service;

import by.yurovski.dao.UserDao;
import by.yurovski.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Transactional
    public  User findUserById(int id){
        return  userDao.findUserById(id);
    }
    @Transactional
    public User findUserByLogin(String login){

        return userDao.findUserByLogin(login);
    }
    @Transactional
    public List<User> findAll(){
        return  userDao.findAll();
    }
    @Transactional
    public <S extends User> S save(S s){
        return userDao.save(s);
    }
    @Transactional
    public User findUserByEmail(String email){
        return userDao.findUserByEmail(email);
    }
    @Transactional
    public User findUserByLoginAndPassword(String login, String password){
        return  userDao.findUserByLoginAndPassword(login,password);
    }
}
