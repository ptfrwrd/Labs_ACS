package dao;

import model.Product;

import java.util.List;

public interface ProductsDao {
    List<Product> getAll();

    void create(Product user);

    Product getById(Long userId);

    void update(Product user);

    void delete(Product user);
}
