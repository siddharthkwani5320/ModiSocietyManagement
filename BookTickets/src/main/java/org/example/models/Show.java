package org.example.models;

import org.example.enums.SeatStatus;
import org.example.exception.AlreadyBookedException;
import org.example.exception.SeatNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Show {
    String id;
    String cityId;
    String theatreId;
    String hallId;
    String movieId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<Seat> seatList;

    public Show(String cityId, String theatreId, String hallId, String movieId, LocalDateTime startTime, LocalDateTime endTime,
                List<Seat> seats) {
        this.id = UUID.randomUUID().toString();
        this.cityId = cityId;
        this.theatreId = theatreId;
        this.hallId = hallId;
        this.movieId = movieId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatList = seats;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(String theatreId) {
        this.theatreId = theatreId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public synchronized void bookTicket(int row,int col){
        try {
            for (Seat seat : this.seatList) {
                if (seat.getRow() == row && seat.getCol() == col) {
                    if (seat.status == SeatStatus.BOOKED) throw new AlreadyBookedException("Seat is Already Booked");
                    seat.setStatus(SeatStatus.BOOKED);
                    return;
                }
            }
            throw new SeatNotFoundException("Seat not Found");
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void showSeats(){
        System.out.println("Show \nSeats are:-");
        for(Seat seat : this.seatList){
            System.out.println(seat);
        }
    }
    @Override
    public String toString(){
        StringBuilder show=new StringBuilder("Show:-");
        show.append(movieId).append(" ").append(startTime)
                .append(" ").append(endTime);
        return show.toString();
    }

}
