package com.hivetech.apiResfull.service.impl;

import com.hivetech.apiResfull.model.Product;
import com.hivetech.apiResfull.repository.ProductRepository;
import com.hivetech.apiResfull.repository.ProductRepositoryImpl;
import com.hivetech.apiResfull.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService  {

  @Autowired
    ProductRepositoryImpl productRepository;
    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public void editProduct(Product product, int id) {
        productRepository.editProduct(product, id);
    }

    @Override
    public void deleteProduct(int id) {
productRepository.deleteProduct(id);
    }

    @Override
    public Product find(int id) {

        return productRepository.find(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
