package com.davidprojects.gamestore.reservatie;

import java.time.LocalDateTime;

public class Reservatie {
    private final long klantId;
    private final long gameId;
    private final LocalDateTime tijdstip;

    public Reservatie(long klantId, long gameId, LocalDateTime tijdstip) {
        this.klantId = klantId;
        this.gameId = gameId;
        this.tijdstip = tijdstip;
    }

    public Reservatie(long klantId, long gameId) {
        this(klantId, gameId, LocalDateTime.now());
    }

    public long getKlantId() {
        return klantId;
    }

    public long getGameId() {
        return gameId;
    }

    public LocalDateTime getTijdstip() {
        return tijdstip;
    }
}
