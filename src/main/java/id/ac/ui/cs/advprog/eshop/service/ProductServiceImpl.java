package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(final Product product) {
        return productRepository.create(product);
    }

    @Override
    public List<Product> findAll() {
        final Iterator<Product> productIterator = productRepository.findAll();
        final List<Product> allProducts = new ArrayList<>();

        productIterator.forEachRemaining(allProducts::add);

        return allProducts;
    }

    @Override
    public Product findById(final String productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public boolean delete(String productId) {
        return productRepository.delete(productId);
    }
}