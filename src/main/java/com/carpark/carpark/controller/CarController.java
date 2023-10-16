package com.carpark.carpark.controller;


import com.carpark.carpark.model.Car;
import com.carpark.carpark.model.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cars")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    List<Car> findAll() {
        return carRepository.findAll();
    }

    @PostMapping
    Car save(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id) {
        carRepository.deleteById(id);
    }

    @GetMapping("/id/{id}")
    Car findById(@PathVariable long id) throws RescourceNotFoundException {
        return carRepository.findById(id).orElseThrow(RescourceNotFoundException::new);
    }

    //    Why do I have to do it that way with throw
    @PutMapping("/{id}")
    Car update(@PathVariable long id, @RequestBody Car updatedCar) throws RescourceNotFoundException {
        Optional<Car> existingCar = carRepository.findById(id);

        if (existingCar.isPresent()) {
            Car car = existingCar.get();
            car.setTypeName(updatedCar.getTypeName());
            car.setPrice(updatedCar.getPrice());
            // Update other properties as needed

            return carRepository.save(car);
        } else {
            throw new RescourceNotFoundException();
        }
    }

}
