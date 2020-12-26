package controllers;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.ProductsService;

@Controller
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    @Qualifier(value = "productsService")
    public void setProductsService(ProductsService service) {
        this.productsService = service;
    }

    @RequestMapping("/products")
    public String showProducts(Model m) {
        m.addAttribute("products", productsService.getAll());
        return "products";
    }

    @RequestMapping(value = "/product_add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("name") String name) {
        if (name != null) {
            Product product = new Product(name);
            productsService.create(product);
        }
        return "redirect:/products";
    }

    @RequestMapping("/product_edit")
    public String editProduct(@ModelAttribute("product_id") Long productId, Model m) {
        Product product = productsService.getById(productId);
        m.addAttribute("product", product);
        m.addAttribute("product_id", productId);
        return "product_edit";
    }

    @RequestMapping("/product_save")
    public String saveProduct(
            @ModelAttribute("product_id") Long productId,
            @ModelAttribute("name") String name
    ) {
        productsService.update(new Product(productId, name));
        return "redirect:/products";
    }

    @RequestMapping("/product_delete")
    public String deleteProduct(@ModelAttribute("product_id") Long productId) {
        productsService.delete(productId);
        return "redirect:/products";
    }
}
