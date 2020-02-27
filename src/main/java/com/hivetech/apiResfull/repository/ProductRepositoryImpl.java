package com.hivetech.apiResfull.repository;

import com.hivetech.apiResfull.model.Product;
import com.hivetech.apiResfull.utiti.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update("INSERT INTO products (id, name, price) VALUES (?, ?, ?)",
                product.getId(), product.getName(), product.getPrice());
    }

    @Override
    public void editProduct(Product product, int id) {
        jdbcTemplate
                .update("UPDATE products SET name = ? , price = ?  WHERE id = ? ",
                        product.getName(), product.getPrice(),  id);
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update("DELETE from products WHERE id = ? ", id);
    }

    @Override
    public Product find(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductMapper(), id);

    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductMapper());
    }
}
