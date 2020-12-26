package services;

import dao.ProductsDao;
import model.Product;
import model.TableUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private ProductsDao productsDao;
    private JmsTemplate jmsTemplate;

    public void setProductsDao(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @Autowired
    @Qualifier(value = "jmsTemplate")
    public void setJmsTemplate(JmsTemplate template) {
        this.jmsTemplate = template;
    }

    @Override
    @Transactional
    public List<Product> getAll() {
        return productsDao.getAll();
    }

    @Override
    @Transactional
    public void create(Product product) {
        productsDao.create(product);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("INSERT", product.getId(), "products", product.toString())
        );
    }

    @Override
    @Transactional
    public Product getById(Long productId) {
        return productsDao.getById(productId);
    }

    @Override
    @Transactional
    public void update(Product product) {
        productsDao.update(product);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("UPDATE", product.getId(), "products", product.toString())
        );
    }

    @Override
    @Transactional
    public void delete(Long productId) {
        Product product = getById(productId);
        productsDao.delete(product);
        jmsTemplate.convertAndSend(
                "allUpdates",
                new TableUpdate("DELETE", product.getId(), "products", product.toString())
        );
    }
}
