package by.yurovski.dao;

import by.yurovski.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findUserById(int id);
    User findUserByLogin(String login);
}
