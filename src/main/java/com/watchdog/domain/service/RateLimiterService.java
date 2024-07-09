package com.watchdog.domain.service;

import com.watchdog.domain.RateLimiter;
import com.watchdog.infrastructure.output.repository.RulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RateLimiterService {

    private final RulesRepository rulesRepository;

    public void checkLimits(RateLimiter rateLimiter) {

        final var rules = rulesRepository.findAllRules();
        rules.getDefaultRules();
    }
}
