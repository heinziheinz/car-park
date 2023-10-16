package com.carpark.carpark.model;

import java.time.LocalDate;



public record User(long id, String name, LocalDate birthdate, String address) {
}
