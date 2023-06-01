package org.global.ecp.hackathon.app.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.global.ecp.hackathon.app.product.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products;

    public ProductRepository() {
        products = new HashMap<>();

    }

    public List<Product> getAll() {

        return products.values().stream().toList();
    }

    public Product getById(final Long id) {

        return products.get(id);
    }

    public Product add(final Product product) {

        return products.put(product.getId(), product);
    }

    public void deleteById(final Long id) {

        products.remove(id);
    }
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public boolean existsById(final Long id) {
        return products.containsKey(id);
    }

    public boolean existsByName(String name) {
        return products.values().stream().anyMatch(product -> product.getName().equals(name));
    }
}
