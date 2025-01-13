package com.davidprojects.gamestore.game;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameNietGevondenException extends RuntimeException {
    public GameNietGevondenException(long id) {
        super("Game niet gevonden. id: " + id );
    }
}
