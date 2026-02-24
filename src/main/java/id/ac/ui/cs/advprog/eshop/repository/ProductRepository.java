package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> productData;

    public ProductRepository() {
        this.productData = new ArrayList<>();
    }

    public Product create(final Product product) {
        if (product.getProductId() == null || product.getProductId().isEmpty()) {
            product.setProductId(UUID.randomUUID().toString());
        }

        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(final String productId) {
        Product result = null;

        for (final Product product : productData) {
            if (product.getProductId().equals(productId)) {
                result = product;
                break;
            }
        }

        return result;
    }

    public Product update(final Product updatedProduct) {
        Product result = null;

        for (int index = 0; index < productData.size(); index++) {
            final Product currentProduct = productData.get(index);

            if (currentProduct.getProductId()
                    .equals(updatedProduct.getProductId())) {

                productData.set(index, updatedProduct);
                result = updatedProduct;
                break;
            }
        }

        return result;
    }

    public boolean delete(final String productId) {
        boolean isDeleted = false;

        for (int index = 0; index < productData.size(); index++) {
            final Product currentProduct = productData.get(index);

            if (currentProduct.getProductId().equals(productId)) {
                productData.remove(index);
                isDeleted = true;
                break;
            }
        }

        return isDeleted;
    }
}