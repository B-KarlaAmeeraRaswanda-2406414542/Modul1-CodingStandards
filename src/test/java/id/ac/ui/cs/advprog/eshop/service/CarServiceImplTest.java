package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    CarRepository carRepository;

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("car-1");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateCar() {
        doReturn(car).when(carRepository).create(any(Car.class));

        Car result = carService.create(car);

        verify(carRepository, times(1)).create(car);
        assertEquals("car-1", result.getCarId());
        assertEquals("Toyota", result.getCarName());
    }

    @Test
    void testFindAllCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(car);

        Iterator<Car> iterator = cars.iterator();

        doReturn(iterator).when(carRepository).findAll();

        List<Car> result = carService.findAll();

        verify(carRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals("car-1", result.get(0).getCarId());
    }

    @Test
    void testFindCarById() {
        doReturn(car).when(carRepository).findById("car-1");

        Car result = carService.findById("car-1");

        verify(carRepository, times(1)).findById("car-1");
        assertEquals("car-1", result.getCarId());
    }

    @Test
    void testUpdateCar() {
        doReturn(car).when(carRepository).update(eq("car-1"), any(Car.class));

        carService.update("car-1", car);

        verify(carRepository, times(1)).update("car-1", car);
    }

    @Test
    void testDeleteCarById() {
        carService.deleteCarById("car-1");

        verify(carRepository, times(1)).delete("car-1");
    }
}