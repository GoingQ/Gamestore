package com.davidprojects.gamestore.platform;

import com.davidprojects.gamestore.game.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlatformController {
    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("game/platformen")
    List<Platform> findAll() {
        return platformService.findAll();
    }
}
