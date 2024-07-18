package com.watchdog.domain.service;

import com.watchdog.domain.DefaultRules;
import com.watchdog.domain.RateLimiter;
import com.watchdog.infrastructure.output.repository.QuotaRepository;
import com.watchdog.infrastructure.output.repository.RulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RateLimiterService {

    private final RulesRepository rulesRepository;

    private final QuotaRepository quotaRepository;

    public void checkLimits(RateLimiter rateLimiter) {

        //TODO search the rate limiter for endpoint, ip and user
        final var rules = rulesRepository.findAllRules();
        DefaultRules defaultRules = rules.getDefaultRules();

        int quota = quotaRepository.searchQuota(rateLimiter);
        System.out.println("quota: " + quota);
        if (quota > Integer.parseInt(defaultRules.getRpsPerUser())) {
            throw new RuntimeException("Excedeed");
        }

        quotaRepository.addQuota(rateLimiter);
        //TODO validate the rules
        //TODO add to repository
    }
}
