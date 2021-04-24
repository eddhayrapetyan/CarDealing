package am.dalal.shop.controllers;

import am.dalal.shop.models.Product;
import am.dalal.shop.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class shopController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shop")
    public String shopMain(Model model){
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products" , products);
        return "shop-main";
    }

    @GetMapping("/shop/add")
    public String shopAdd(Model model){
        return "shop-add";
    }

    @PostMapping("/shop/add")
    public String shopProductAdd(@RequestParam String title, @RequestParam String shortDs , @RequestParam String description , Model model){
        Product product = new Product(title,shortDs,description);
        productRepository.save(product);
        return "redirect:/shop";
    }

    @GetMapping("/shop/{id}")
    public String productDetails(@PathVariable(value = "id") long id , Model model){
        if (!productRepository.existsById(id)){
            return "redirect:/shop";
        }
        Optional<Product> product = productRepository.findById(id);
        List<Product> result = new ArrayList<>();
        product.ifPresent(result::add);
        model.addAttribute("product",result);
        return "shop-details";
    }

    @GetMapping("/shop/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id , Model model){
        if (!productRepository.existsById(id)){
            return "redirect:/shop";
        }
        Optional<Product> product = productRepository.findById(id);
        List<Product> result = new ArrayList<>();
        product.ifPresent(result::add);
        model.addAttribute("product",result);
        return "shop-edit";
    }

    @PostMapping("/shop/{id}/edit")
    public String shopProductUpdate(@PathVariable(value = "id") long id ,@RequestParam String title, @RequestParam String shortDs , @RequestParam String description , Model model) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("file not found"));
        product.setTitle(title);
        product.setShortDs(shortDs);
        product.setDescription(description);
        productRepository.save(product);
        return "redirect:/shop";
    }

    @PostMapping("/shop/{id}/remove")
    public String shopProductDelete(@PathVariable(value = "id") long id , Model model) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("file not found"));
        productRepository.delete(product);
        return "redirect:/shop";
    }

}
