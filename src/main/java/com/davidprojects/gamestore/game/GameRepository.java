package com.davidprojects.gamestore.game;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {
    private final JdbcTemplate template;

    public GameRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Game> gameRowMapper = (result, rowNum) ->
            new Game(result.getLong("id"), result.getLong("platformId"),
                    result.getString("titel"), result.getInt("voorraad"),
                    result.getInt("gereserveerd"), result.getBigDecimal("prijs"));

    public List<Game> findByPlatformId(long id) {
        var sql = """
                select id, platformId, titel, voorraad, gereserveerd, prijs
                from games
                where platformId = ?
                order by titel
                """;
        return template.query(sql, gameRowMapper, id);
    }
    public Optional<Game> findAndLockById(long id) {
        try {
            var sql = """
                     select id, platformId, titel, voorraad, gereserveerd, prijs
                     from games
                     where id = ?
                     for update
                     """;
            return Optional.of(template.queryForObject(sql,gameRowMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public void updateGereserveerd(long id, int gereserveerd) {
        var sql = """
                update games
                set gereserveerd = ?
                where id  = ?
                """;
        if(template.update(sql, gereserveerd, id) == 0) {
            throw new GameNietGevondenException(id);
        }
    }

}
