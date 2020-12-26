package controllers;

import model.Order;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.OrdersService;
import services.ProductsService;
import services.UsersService;

@Controller
public class OrdersController {
    private UsersService usersService;
    private ProductsService productsService;
    private OrdersService ordersService;

    @Autowired
    @Qualifier(value = "usersService")
    public void setUsersService(UsersService service) {
        this.usersService = service;
    }

    @Autowired
    @Qualifier(value = "productsService")
    public void setProductsService(ProductsService service) {
        this.productsService = service;
    }

    @Autowired
    @Qualifier(value = "ordersService")
    public void setOrdersService(OrdersService service) {
        this.ordersService = service;
    }

    @RequestMapping("/orders")
    public String showOrders(Model m) {
        m.addAttribute("users", usersService.getAll());
        m.addAttribute("products", productsService.getAll());
        m.addAttribute("orders", ordersService.getAll());
        return "orders";
    }

    @RequestMapping(value = "/order_add", method = RequestMethod.POST)
    public String addOrder(
            @ModelAttribute("user_id") Long userId,
            @ModelAttribute("product_id") Long productId,
            @ModelAttribute("amount") Long amount
    ) {
        if (userId != null && productId != null && amount != null) {
            User user = usersService.getById(userId);
            Product product = productsService.getById(productId);
            if (user != null && product != null) {
                ordersService.create(new Order(amount, user, product));
            }
        }
        return "redirect:/orders";
    }

    @RequestMapping("/order_edit")
    public String editOrder(@ModelAttribute("order_id") Long orderId, Model m) {
        Order order = ordersService.getById(orderId);
        m.addAttribute("users", usersService.getAll());
        m.addAttribute("products", productsService.getAll());
        m.addAttribute("order", order);
        m.addAttribute("order_id", orderId);
        return "order_edit";
    }

    @RequestMapping("/order_save")
    public String saveOrder(
            @ModelAttribute("order_id") Long orderId,
            @ModelAttribute("user_id") Long userId,
            @ModelAttribute("product_id") Long productId,
            @ModelAttribute("amount") Long amount
    ) {
        if (userId != null && productId != null && amount != null) {
            User user = usersService.getById(userId);
            Product product = productsService.getById(productId);
            if (user != null && product != null) {
                ordersService.update(new Order(orderId, amount, user, product));
            }
        }
        return "redirect:/orders";
    }

    @RequestMapping("/order_delete")
    public String deleteOrder(@ModelAttribute("order_id") Long orderId) {
        ordersService.delete(orderId);
        return "redirect:/orders";
    }
}
