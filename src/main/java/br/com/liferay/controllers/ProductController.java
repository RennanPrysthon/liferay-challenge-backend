package br.com.liferay.controllers;

import br.com.liferay.dto.ResponseProducts;
import br.com.liferay.models.Product;
import br.com.liferay.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createListOfProducts(@RequestBody List<Product> products) {

        ResponseProducts response = productService
            .createProducts(products)
            .orElseThrow(() -> new RuntimeException("Não foi possivel adicionar os produtos"));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Product>> getAll(@RequestParam(value = "order", required = false) Long order) {
        List<Product> products = this.productService.returnAll(order);

        return ResponseEntity.ok(products);

    }


}
