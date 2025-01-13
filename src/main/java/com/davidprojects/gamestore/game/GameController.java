package com.davidprojects.gamestore.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("game/{id}")
    List<Game> findByGenreId(@PathVariable long id) {
        List<Game> films = gameService.findByPlatformId(id);
        if (films.isEmpty()) {
            throw new GameNietGevondenException(id);
        }
        return films;
    }
}
