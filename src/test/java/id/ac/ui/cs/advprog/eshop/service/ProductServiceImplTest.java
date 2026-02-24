package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test");
        product.setProductQuantity(10);

        when(productRepository.create(product)).thenReturn(product);

        Product result = productService.create(product);

        assertEquals(product, result);
        verify(productRepository).create(product);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("1");

        Product product2 = new Product();
        product2.setProductId("2");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getProductId());
    }

    @Test
    void testFindByIdFound() {
        Product product = new Product();
        product.setProductId("1");

        when(productRepository.findById("1")).thenReturn(product);

        Product result = productService.findById("1");

        assertNotNull(result);
        assertEquals("1", result.getProductId());
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("999")).thenReturn(null);

        Product result = productService.findById("999");

        assertNull(result);
    }

    @Test
    void testUpdateProductSuccess() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated");

        when(productRepository.update(product))
                .thenReturn(product);

        Product result = productService.update(product);

        assertNotNull(result);
        assertEquals("Updated", result.getProductName());
    }

    @Test
    void testUpdateProductFail() {
        Product product = new Product();
        product.setProductId("999");

        when(productRepository.update(product))
                .thenReturn(null);

        Product result = productService.update(product);

        assertNull(result);
    }

    @Test
    void testDeleteProductSuccess() {
        when(productRepository.delete("1")).thenReturn(true);

        boolean result = productService.delete("1");

        assertTrue(result);
        verify(productRepository).delete("1");
    }

    @Test
    void testDeleteProductFail() {
        when(productRepository.delete("999"))
                .thenReturn(false);

        boolean result = productService.delete("999");

        assertFalse(result);
    }
}