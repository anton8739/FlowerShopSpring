package by.yurovski.dao;

import by.yurovski.entity.Product;
import by.yurovski.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {
    List<Product> findAllByCategory(CategoryEnum category);
    Product findProductsByURL(String URL);
    Product findProductsById(int id);


    @Override
    void delete(Product product);
    List<Product> findAll();
}
