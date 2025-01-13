package com.davidprojects.gamestore.klant;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class KlantController {
    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping(value = "klanten", params = "naamBevat")
    Stream<Klant> findByNaamBevat(@NotEmpty String naamBevat) {
        return klantService.findByNaamBevat(naamBevat)
                .stream();
    }
}
