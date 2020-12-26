package services;

import model.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getAll();

    void create(Product product);

    Product getById(Long productId);

    void update(Product product);

    void delete(Long productId);
}
