package org.global.ecp.hackathon.app.product;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.product.model.Product;
import org.global.ecp.hackathon.app.product.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {

        return productRepository.getAll();
    }

    public Product getById(final Long id) {

        return productRepository.getById(id);
    }

    public Product create(final ProductDto productDto) {

        // what happens if product name already exists
        return null;
    }

    public void deleteById(final Long id) {

        productRepository.deleteById(id);
    }
    public Product create(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("Product with the same ID already exists.");
        }

        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException("Product with the same name already exists.");
        }

        Product createdProduct = new Product();
        createdProduct.setId(product.getId());
        createdProduct.setName(product.getName());
        createdProduct.setDescription(product.getDescription());
        createdProduct.setPrice(product.getPrice());

        productRepository.addProduct(createdProduct);

        return createdProduct;
    }

}
