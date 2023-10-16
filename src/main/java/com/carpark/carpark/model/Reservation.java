package com.carpark.carpark.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private  User user;
    private  LocalDate start;
    private LocalDate end;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
