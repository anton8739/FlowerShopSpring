package by.yurovski.dao;

import by.yurovski.entity.Foto;
import by.yurovski.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoDao extends JpaRepository<Foto, Long> {
    @Override
    <S extends Foto> S save(S s);

    @Override
    void delete(Foto foto);

    Foto findById(long id);
}
