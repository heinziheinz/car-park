package com.carpark.carpark.controller;


import com.carpark.carpark.model.Car;
import com.carpark.carpark.model.Reservation;
import com.carpark.carpark.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationRepository reservationRepository;
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id) {
        reservationRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    Reservation update(@PathVariable long id, @RequestBody Reservation updatedReservation) throws RescourceNotFoundException {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);

        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            reservation.setStartDate(updatedReservation.getStartDate());
            reservation.setEndDate(updatedReservation.getEndDate());

            return reservationRepository.save(reservation);
        } else {
            throw new RescourceNotFoundException();
        }
    }

}
