package services;

import model.Order;

import java.util.List;

public interface OrdersService {
    List<Order> getAll();

    void create(Order order);

    Order getById(Long orderId);

    void update(Order order);

    void delete(Long orderId);
}
