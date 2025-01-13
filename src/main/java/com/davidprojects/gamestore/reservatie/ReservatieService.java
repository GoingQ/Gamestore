package com.davidprojects.gamestore.reservatie;

import com.davidprojects.gamestore.game.GameNietGevondenException;
import com.davidprojects.gamestore.game.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@Transactional(readOnly = true)
public class ReservatieService {
    private final ReservatieRepository reservatieRepository;
    private final GameRepository gameRepository;

    public ReservatieService(ReservatieRepository reservatieRepository, GameRepository gameRepository) {
        this.reservatieRepository = reservatieRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void create(List<NieuweReservatie> nieuweReservatie) {
        for (NieuweReservatie x : nieuweReservatie) {
            var game = gameRepository.findAndLockById(x.gameId())
                    .orElseThrow(() -> new GameNietGevondenException(x.gameId()));
            var reservatie = new Reservatie(x.klantId(), x.gameId(), now());
            reservatieRepository.create(reservatie);
            game.bestelt();
            gameRepository.updateGereserveerd(x.gameId(), game.getGereserveerd());

        }

    }
}
