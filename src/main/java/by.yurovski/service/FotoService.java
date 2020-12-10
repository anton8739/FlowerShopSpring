package by.yurovski.service;

import by.yurovski.dao.FotoDao;
import by.yurovski.entity.Foto;
import by.yurovski.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoService {
    @Autowired
    FotoDao fotoDao;

    @Transactional
    public <S extends Foto> S save(S s){
        return fotoDao.save(s);
    }

    @Transactional
    public Foto findById(long id){
        return fotoDao.findById(id);
    }
    @Transactional
    public void delete(Foto foto){
        fotoDao.delete(foto);
    }
}
