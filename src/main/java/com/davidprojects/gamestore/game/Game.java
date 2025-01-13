package com.davidprojects.gamestore.game;

import java.math.BigDecimal;

public class Game {
    private final long id;
    private final long genreId;
    private final String titel;
    private final int voorraad;
    private int gereserveerd;
    private final BigDecimal prijs;

    public Game(long id, long genreId, String titel, int voorraad, int gereserveerd, BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
    }

    public void bestelt() {
        if (voorraad < gereserveerd) {
            throw new OnvoldoendeVoorraadException();
        }
        gereserveerd++;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}
