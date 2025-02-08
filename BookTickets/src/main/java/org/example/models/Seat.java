package org.example.models;

import org.example.enums.SeatStatus;
import org.example.enums.SeatType;

public class Seat {
    int row;
    int col;
    SeatType type;
    SeatStatus status;

    public Seat(int col, SeatType type, int row) {
        this.col = col;
        this.type = type;
        this.row = row;
        this.status = SeatStatus.AVAILABLE;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString(){
        StringBuilder seat = new StringBuilder("Seat:-");
        seat.append(row)
                .append(" ")
                .append(col)
                .append(" ")
                .append(status).append(" ")
                .append(type);
        return seat.toString();
    }
}
