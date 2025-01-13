package com.davidprojects.gamestore.platform;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlatformService {
    private final PlatformRepository platformRepository;

    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }


    public List<Platform> findAll() {
        return platformRepository.findAll();
    }
}
