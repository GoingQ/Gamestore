package com.davidprojects.gamestore.reservatie;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservatieController {
    private final ReservatieService reservatieService;

    public ReservatieController(ReservatieService reservatieService) {
        this.reservatieService = reservatieService;
    }

    @PostMapping("reservaties")
    void create(@RequestBody List<NieuweReservatie> nieuweReservatie) {
        reservatieService.create(nieuweReservatie);
    }
}
