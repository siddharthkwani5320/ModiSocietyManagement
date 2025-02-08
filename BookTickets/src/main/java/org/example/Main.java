package org.example;

import org.example.models.Movie;
import org.example.models.Show;
import org.example.services.BookingService;

public class Main {
    public static void main(String[] args) {
        BookingService service=new BookingService();
        Show show= service.getShow();
        service.printShows(show.getId());
        service.bookTickets(1,1,show.getId());
        service.bookTickets(1,1,show.getId());
        service.bookTickets(2,1,show.getId());
    }
}