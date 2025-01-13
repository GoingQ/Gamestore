package com.davidprojects.gamestore.klant;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KlantRepository {
    private final JdbcTemplate template;

    public KlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Klant> klantRowMapper = (result, rowNum) ->
            new Klant(result.getLong("id"), result.getString("familienaam"), result.getString("voornaam"), result.getString("straatNummer"), result.getString("postcode"), result.getString("gemeente"));

    public List<Klant> findByNaamBevat(String naamBevat) {
        var sql = """
                select id, familienaam, voornaam, straatNummer, postcode, gemeente
                from klanten
                where familienaam like ?
                order by familienaam
                """;
        return template.query(sql, klantRowMapper, "%" + naamBevat + "%");
    }
}
