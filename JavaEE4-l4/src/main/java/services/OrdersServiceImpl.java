package services;

import dao.OrdersDao;
import model.TableUpdate;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    private OrdersDao ordersDao;
    private JmsTemplate jmsTemplate;

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    @Autowired
    @Qualifier(value = "jmsTemplate")
    public void setJmsTemplate(JmsTemplate template) {
        this.jmsTemplate = template;
    }

    @Override
    @Transactional
    public List<Order> getAll() {
        return ordersDao.getAll();
    }

    @Override
    @Transactional
    public void create(Order order) {
        ordersDao.create(order);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("INSERT", order.getId(), "orders", order.toString())
        );
    }

    @Override
    @Transactional
    public Order getById(Long orderId) {
        return ordersDao.getById(orderId);
    }

    @Override
    @Transactional
    public void update(Order order) {
        ordersDao.update(order);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("UPDATE", order.getId(), "orders", order.toString())
        );
    }

    @Override
    @Transactional
    public void delete(Long orderId) {
        Order order = getById(orderId);
        ordersDao.delete(order);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("DELETE", order.getId(), "orders", order.toString())
        );
    }
}
