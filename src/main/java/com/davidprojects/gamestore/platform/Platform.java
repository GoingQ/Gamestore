package com.davidprojects.gamestore.platform;

public class Platform {
    private final long id;
    private final String naam;

    public Platform(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
