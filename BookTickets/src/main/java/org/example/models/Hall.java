package org.example.models;

import java.util.List;
import java.util.UUID;

public class Hall {
    String id;
    List<Seat> seats;

    public Hall(List<Seat> seats) {
        this.seats = seats;
        this.id = UUID.randomUUID().toString();
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }


}
