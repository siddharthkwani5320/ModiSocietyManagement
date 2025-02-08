package org.example.models;

import java.util.List;
import java.util.UUID;

public class Theatre {
    String id;
    List<Hall> halls;

    public Theatre(List<Hall> halls) {
        this.halls = halls;
        this.id = UUID.randomUUID().toString();
    }

    public void addHall(Hall hall){
        this.halls.add(hall);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }
}
