package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.objenesis.SpringObjenesis;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb5589ef-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateProductWithNullId() {
        Product product = new Product();
        product.setProductName("No ID Product");
        product.setProductQuantity(10);

        Product result = productRepository.create(product);

        assertNotNull(result.getProductId());
        assertFalse(result.getProductId().isEmpty());
    }

    @Test
    void testCreateProductWithEmptyId() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Empty ID Product");
        product.setProductQuantity(5);

        Product result = productRepository.create(product);

        assertNotNull(result.getProductId());
        assertFalse(result.getProductId().isEmpty());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb5589ef-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("aaf9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdSecondElement() {
        Product p1 = new Product();
        p1.setProductId("1");
        productRepository.create(p1);

        Product p2 = new Product();
        p2.setProductId("2");
        productRepository.create(p2);

        Product result = productRepository.findById("2");

        assertNotNull(result);
        assertEquals("2", result.getProductId());
    }

    @Test
    void testUpdateProductIfExists() {
        Product product = new Product();
        product.setProductId("edit-id");
        product.setProductName("Before");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product updated = new Product();
        updated.setProductId("edit-id"); // id sama
        updated.setProductName("After");
        updated.setProductQuantity(99);

        Product result = productRepository.update(updated);

        assertNotNull(result);
        assertEquals("After", result.getProductName());
        assertEquals(99, result.getProductQuantity());

        Product fromRepo = productRepository.findById("edit-id");
        assertNotNull(fromRepo);
        assertEquals("After", fromRepo.getProductName());
        assertEquals(99, fromRepo.getProductQuantity());
    }

    @Test
    void testUpdateProductIfNotExists() {
        Product updated = new Product();
        updated.setProductId("missing-id");
        updated.setProductName("Whatever");
        updated.setProductQuantity(1);

        Product result = productRepository.update(updated);

        assertNull(result);
    }

    @Test
    void testUpdateSecondElement() {
        Product p1 = new Product();
        p1.setProductId("1");
        productRepository.create(p1);

        Product p2 = new Product();
        p2.setProductId("2");
        productRepository.create(p2);

        Product updated = new Product();
        updated.setProductId("2");
        updated.setProductName("UpdatedSecond");

        Product result = productRepository.update(updated);

        assertNotNull(result);
        assertEquals("UpdatedSecond", result.getProductName());
    }

    @Test
    void testDeleteProductIfExists() {
        Product product = new Product();
        product.setProductId("delete-id");
        product.setProductName("ToDelete");
        product.setProductQuantity(1);
        productRepository.create(product);

        boolean deleted = productRepository.delete("delete-id");

        assertTrue(deleted);
        assertNull(productRepository.findById("delete-id"));
    }

    @Test
    void testDeleteProductIfNotExists() {
        boolean deleted = productRepository.delete("missing-id");
        assertFalse(deleted);
    }

    @Test
    void testDeleteSecondElement() {
        Product p1 = new Product();
        p1.setProductId("1");
        productRepository.create(p1);

        Product p2 = new Product();
        p2.setProductId("2");
        productRepository.create(p2);

        boolean deleted = productRepository.delete("2");

        assertTrue(deleted);
        assertNull(productRepository.findById("2"));
    }
}