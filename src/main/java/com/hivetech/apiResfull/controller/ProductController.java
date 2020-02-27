package com.hivetech.apiResfull.controller;

import com.hivetech.apiResfull.model.Product;
import com.hivetech.apiResfull.service.ProductService;
import com.hivetech.apiResfull.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
            @Autowired
    private ProductServiceImpl productServiceimpl;

    /* ---------------- GET ALL CUSTOMER ------------------------ */
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productServiceimpl.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /*-----------------------Get product by id ---------*/
    @RequestMapping(value = "/products/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProductById(@PathVariable("id") Integer id){
        Product product = productServiceimpl.find(id);

        if (product == null) {
            return new ResponseEntity("Product [" + id + "] not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    /*----------------- Post  Creat Product -------------*/
    @RequestMapping(value = "/products",
            method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            UriComponentsBuilder builder) {
        productServiceimpl.addProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    /*-----------------  Update ------------*/

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product product) {
        Product currentProduct = productServiceimpl
                .find(id);

        if (currentProduct == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());


        productServiceimpl.editProduct(currentProduct, id);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }
    /*---------------- delete -----------*/
    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(
            @PathVariable("id") Integer id) {
        Product product = productServiceimpl.find(id);
        if (product ==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productServiceimpl.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
