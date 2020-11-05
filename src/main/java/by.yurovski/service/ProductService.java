package by.yurovski.service;

import by.yurovski.dao.ProductDao;

import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    @Transactional
    public List<Product> findAllByCategory(CategoryEnum category){
        return productDao.findAllByCategory(category);
    }
    @Transactional
    public Product findProductsByURL(String URL){
        return productDao.findProductsByURL(URL);
    }
}
