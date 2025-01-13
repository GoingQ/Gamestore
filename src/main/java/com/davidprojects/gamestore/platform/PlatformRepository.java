package com.davidprojects.gamestore.platform;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlatformRepository {
    private final JdbcTemplate template;

    public PlatformRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Platform> platformRowMapper = (result, rowNum) ->
            new Platform(result.getLong("id"), result.getString("naam"));

    public List<Platform> findAll() {
        var sql = """
                 select id, naam
                 from platformen
                 order by naam
                 """;
        return template.query(sql, platformRowMapper);
    }
}
