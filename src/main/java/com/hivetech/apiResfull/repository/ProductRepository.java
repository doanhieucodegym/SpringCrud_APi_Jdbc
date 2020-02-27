package com.hivetech.apiResfull.repository;

import com.hivetech.apiResfull.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository {
    public void addProduct(Product product);

    public void editProduct(Product product, int id);

    public void deleteProduct(int id);
    public Product find(int id);
    public List<Product> findAll();
}
