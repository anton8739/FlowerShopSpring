package by.yurovski.service;

import by.yurovski.dao.UserVerificationTokenDao;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserVerificationTokenService {
    @Autowired
    UserVerificationTokenDao userVerificationTokenDao;

    @Transactional
    public <S extends UserVerificationToken> S save(S s){
        return userVerificationTokenDao.save(s);
    }

    @Transactional
    public void delete(UserVerificationToken userVerificationToken){
        userVerificationTokenDao.delete(userVerificationToken);
    }
    @Transactional
    public UserVerificationToken check(String token, Date date){
        UserVerificationToken userVerificationToken=userVerificationTokenDao.findByToken(token);
        if (userVerificationToken != null && userVerificationToken.getExpiryDate()<date.getTime()){
            return null;
        }

        return userVerificationToken;
    }
    @Transactional
    public  UserVerificationToken findByUser(User user){
        return userVerificationTokenDao.findByUser(user);
    }
}
