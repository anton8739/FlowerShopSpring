package by.yurovski.dao;

import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationTokenDao  extends JpaRepository<UserVerificationToken, Long> {
    @Override
    <S extends UserVerificationToken> S save(S s);

    @Override
    void delete(UserVerificationToken userVerificationToken);

    UserVerificationToken findByToken(String token);
    UserVerificationToken findByUser(User user);
}
