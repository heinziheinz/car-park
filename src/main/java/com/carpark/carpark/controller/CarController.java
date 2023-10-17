package com.carpark.carpark.controller;


import com.carpark.carpark.model.Car;
import com.carpark.carpark.model.Reservation;
import com.carpark.carpark.model.User;
import com.carpark.carpark.repository.CarRepository;
import com.carpark.carpark.repository.ReservationRepository;
import com.carpark.carpark.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("cars")
public class CarController {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public CarController(CarRepository carRepository, UserRepository userRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    List<Car> findAll() {
        return carRepository.findAll();
    }

    @GetMapping("/type/{name}")
    List<Car> findAllByType(@PathVariable String name) {
        return carRepository.findAllByTypeName(name);
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

            return carRepository.save(car);
        } else {
            throw new RescourceNotFoundException();
        }
    }

    @PostMapping("/{carId}/user/{userId}/{startDate}/{endDate}")
    Car rentACar(
            @PathVariable long carId,
            @PathVariable long userId,
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) throws RescourceNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(RescourceNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(RescourceNotFoundException::new);
        Reservation reservation = new Reservation(user, startDate, endDate);
        reservation.setCar(car); // Set the car for the reservation
        Reservation reservationInst = reservationRepository.save(reservation);
        Set<Reservation> reservations = car.getReservations();
        reservations.add(reservationInst);
        return car;

    }

    @GetMapping("find-available-cars-for-rent/{startDate}/{endDate}")
    List<Car> getAllAvailableCars(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return carRepository.findAvailableCars(startDate, endDate);
    }
}
