package org.example.models;

import java.util.UUID;

public class Movie {
    String name;
    String id;

    public Movie(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return new StringBuilder("Name:-").append(this.name).append(" ").append(this.id).toString();
    }
}
