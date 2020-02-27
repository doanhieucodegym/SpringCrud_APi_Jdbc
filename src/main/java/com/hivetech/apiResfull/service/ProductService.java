package com.hivetech.apiResfull.service;

import com.hivetech.apiResfull.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public void addProduct(Product product);

    public void editProduct(Product product, int id);

    public void deleteProduct(int id);

    public Product find(int id);

    public List<Product> findAll();
}
