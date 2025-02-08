package org.example.services;

import org.example.enums.SeatType;
import org.example.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    Map<String,Hall> halls;
    Map<String,Show> shows;
    List<Movie> movies;
    public BookingService(){
        this.shows = new HashMap<>();
        City city=new City("Mumbai");
        Hall hall=new Hall(new ArrayList<>());
        hall.addSeat(new Seat(1, SeatType.PREMIUM,1));
        hall.addSeat(new Seat(2, SeatType.NORMAL,2));
        this.halls = new HashMap<>();
        this.halls.put(hall.getId(), hall);
        Theatre theatre =new Theatre(new ArrayList<>());
        theatre.addHall(hall);
        this.movies = new ArrayList<>();
        movies.add(new Movie("Intersteller"));
        movies.add(new Movie("Jab We Met"));
        Show show1=new Show(city.getId(), theatre.getId(), hall.getId(),movies.get(0).getId(), LocalDateTime.now(),LocalDateTime.now().plusHours(3),hall.getSeats());
        Show show2=new Show(city.getId(), theatre.getId(), hall.getId(),movies.get(1).getId(), LocalDateTime.now().plusHours(4),LocalDateTime.now().plusHours(7),hall.getSeats());
        this.shows.put(show1.getId(),show1);
        this.shows.put(show2.getId(),show2);
    }

    public void printShows(String showid){
        System.out.println("Shows");
        Show show=shows.get(showid);
        System.out.println(show);
        show.showSeats();
    }

    public void bookTickets(int row,int col,String showid){
        Show show=shows.get(showid);
        show.bookTicket(row,col);
        show.showSeats();
    }

    public Show getShow(){
        Show sh=null;
        for(Map.Entry<String,Show> show : this.shows.entrySet()){
            sh = show.getValue();
            System.out.println(sh);
        }
        return sh;
    }

    public Movie printMovies(){
        System.out.println("Movies");
        Movie movieS=null;
        for(Movie movie : this.movies){
            movieS = movie;
            System.out.println(movie);
        }
        return movieS;
    }
}
