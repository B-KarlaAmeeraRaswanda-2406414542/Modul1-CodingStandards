package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc(addFilters = false)
@org.springframework.test.context.TestPropertySource(properties = {
        "spring.thymeleaf.enabled=false"
})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                        .param("productId", "1")
                        .param("productName", "Test")
                        .param("productQuantity", "10"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"));
    }

    @Test
    void testEditProductPageFound() throws Exception {
        Product product = new Product();
        product.setProductId("1");

        when(productService.findById("1")).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("product"));
    }

    @Test
    void testEditProductPageNotFound() throws Exception {
        when(productService.findById("999")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/999"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }

    @Test
    void testEditProductPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit")
                        .param("productId", "1")
                        .param("productName", "Updated")
                        .param("productQuantity", "20"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }
}