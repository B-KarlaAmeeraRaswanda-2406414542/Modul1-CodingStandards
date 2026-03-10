package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    void testCarGetterSetter(){
        Car car=new Car();
        car.setCarId("CAR1");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);

        assertEquals("CAR1",car.getCarId());
        assertEquals("Toyota",car.getCarName());
        assertEquals("Red",car.getCarColor());
        assertEquals(10,car.getCarQuantity());
    }
}