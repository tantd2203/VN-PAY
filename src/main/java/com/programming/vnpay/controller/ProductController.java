package com.programming.vnpay.controller;


import com.programming.vnpay.dto.ProductRequest;
import com.programming.vnpay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String getAllProduct(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product/product-list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "product/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String showUpdateProductForm(@PathVariable Long id, Model model) {
        ProductRequest productRequest = productService.getProductById(id);
        model.addAttribute("product", productRequest);
        return "product/update-product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute ProductRequest productRequest) {
        productService.updateProduct(productRequest);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
