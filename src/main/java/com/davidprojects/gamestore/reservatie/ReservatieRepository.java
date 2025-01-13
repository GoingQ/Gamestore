package com.davidprojects.gamestore.reservatie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservatieRepository {
    private final JdbcTemplate template;

    public ReservatieRepository(JdbcTemplate template) {
        this.template = template;
    }

    public void create(Reservatie reservaties) {
        var sql = """
                 insert into reservaties(klantId, gameId, reservatie)
                 values (?, ?, ?)
                 """;
        template.update(sql, reservaties.getKlantId(), reservaties.getGameId(), reservaties.getTijdstip());
    }
}
