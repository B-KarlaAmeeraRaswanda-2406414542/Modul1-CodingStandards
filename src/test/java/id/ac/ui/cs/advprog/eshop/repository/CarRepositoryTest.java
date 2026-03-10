package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class CarRepositoryTest {

    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    void setUp(){
        carRepository=new CarRepository();
        car=new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(5);
    }

    @Test
    void testCreateCar(){
        Car created=carRepository.create(car);
        assertNotNull(created.getCarId());
        assertEquals("Toyota",created.getCarName());
    }

    @Test
    void testFindAll(){
        carRepository.create(car);
        Iterator<Car> iterator=carRepository.findAll();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testFindById(){
        Car created=carRepository.create(car);
        Car found=carRepository.findById(created.getCarId());
        assertEquals(created.getCarId(),found.getCarId());
    }

    @Test
    void testUpdate(){
        Car created=carRepository.create(car);
        Car updated=new Car();
        updated.setCarName("Honda");
        updated.setCarColor("Blue");
        updated.setCarQuantity(10);

        Car result=carRepository.update(created.getCarId(),updated);

        assertEquals("Honda",result.getCarName());
        assertEquals("Blue",result.getCarColor());
        assertEquals(10,result.getCarQuantity());
    }

    @Test
    void testDelete(){
        Car created=carRepository.create(car);
        carRepository.delete(created.getCarId());
        assertNull(carRepository.findById(created.getCarId()));
    }

    @Test
    void testUpdateCarNotFound(){
        Car updated=new Car();
        updated.setCarName("Honda");
        assertNull(carRepository.update("NOT_EXIST",updated));
    }

    @Test
    void testCreateCarWithExistingId(){
        Car carWithId=new Car();
        carWithId.setCarId("CAR-1");
        carWithId.setCarName("BMW");
        carWithId.setCarColor("Black");
        carWithId.setCarQuantity(3);

        Car created=carRepository.create(carWithId);

        assertEquals("CAR-1",created.getCarId());
        assertEquals("BMW",created.getCarName());
    }

    @Test
    void testFindByIdAfterSkippingFirstCar(){
        Car firstCar=new Car();
        firstCar.setCarName("Toyota");
        firstCar.setCarColor("Red");
        firstCar.setCarQuantity(5);

        Car secondCar=new Car();
        secondCar.setCarName("Honda");
        secondCar.setCarColor("Blue");
        secondCar.setCarQuantity(10);

        Car createdFirst=carRepository.create(firstCar);
        Car createdSecond=carRepository.create(secondCar);

        Car found=carRepository.findById(createdSecond.getCarId());

        assertNotNull(found);
        assertEquals(createdSecond.getCarId(),found.getCarId());
    }

    @Test
    void testUpdateAfterSkippingFirstCar(){
        Car firstCar=new Car();
        firstCar.setCarName("Toyota");
        firstCar.setCarColor("Red");
        firstCar.setCarQuantity(5);

        Car secondCar=new Car();
        secondCar.setCarName("Honda");
        secondCar.setCarColor("Blue");
        secondCar.setCarQuantity(10);

        Car createdFirst=carRepository.create(firstCar);
        Car createdSecond=carRepository.create(secondCar);

        Car updatedCar=new Car();
        updatedCar.setCarName("Suzuki");
        updatedCar.setCarColor("White");
        updatedCar.setCarQuantity(7);

        Car result=carRepository.update(createdSecond.getCarId(),updatedCar);

        assertNotNull(result);
        assertEquals("Suzuki",result.getCarName());
        assertEquals("White",result.getCarColor());
        assertEquals(7,result.getCarQuantity());
    }
}