package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carservice;

    @Test
    void testCreateCarPage() throws Exception {
        mockMvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"))
                .andExpect(view().name("createCar"));
    }

    @Test
    void testCreateCarPost() throws Exception {
        mockMvc.perform(post("/car/createCar")
                        .param("carId","1")
                        .param("carName","Toyota")
                        .param("carColor","Red")
                        .param("carQuantity","5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));
    }

    @Test
    void testCarListPage() throws Exception {
        List<Car> cars=new ArrayList<>();
        when(carservice.findAll()).thenReturn(cars);

        mockMvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cars"))
                .andExpect(view().name("carList"));
    }

    @Test
    void testEditCarPage() throws Exception {
        Car car=new Car();
        car.setCarId("1");

        when(carservice.findById("1")).thenReturn(car);

        mockMvc.perform(get("/car/editCar/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("car"))
                .andExpect(view().name("editCar"));
    }

    @Test
    void testEditCarPost() throws Exception {
        mockMvc.perform(post("/car/editCar")
                        .param("carId","1")
                        .param("carName","Honda")
                        .param("carColor","Blue")
                        .param("carQuantity","10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));
    }

    @Test
    void testDeleteCar() throws Exception {
        doNothing().when(carservice).deleteCarById("1");

        mockMvc.perform(post("/car/deleteCar")
                        .param("carId","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));
    }
}