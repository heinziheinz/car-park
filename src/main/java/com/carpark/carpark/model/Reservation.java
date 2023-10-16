package com.carpark.carpark.model;


import org.springframework.stereotype.Component;

import java.time.LocalDate;


public record Reservation(User user, LocalDate start, LocalDate end) {
}
