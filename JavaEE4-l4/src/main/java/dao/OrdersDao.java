package dao;

import model.Order;

import java.util.List;

public interface OrdersDao {
    List<Order> getAll();

    void create(Order order);

    Order getById(Long orderId);

    void update(Order order);

    void delete(Order order);
}
