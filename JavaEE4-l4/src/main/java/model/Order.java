package model;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private long amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order() {
    }

    public Order(long amount, User user, Product product) {
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    public Order(Long id, long amount, User user, Product product) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
