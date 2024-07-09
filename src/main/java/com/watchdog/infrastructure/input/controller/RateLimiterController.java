package com.watchdog.infrastructure.input.controller;

import com.watchdog.domain.RateLimiter;
import com.watchdog.domain.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RateLimiterController {
    public static final String WD_RATE_LIMITER_IP = "wd-ip";
    public static final String WD_USER_ID = "wd-user-id";

    private final RateLimiterService rateLimiterService;

    @GetMapping("/rate-limiter")
    public ResponseEntity<Void> rateLimiter(@RequestParam String endpoint, @RequestHeader(required = false) HttpHeaders headers) {

        String userId = headers.get(WD_USER_ID) != null ? headers.get(WD_USER_ID).stream().findFirst().orElse(null) : null;
        String ip = headers.get(WD_RATE_LIMITER_IP) != null ? headers.get(WD_RATE_LIMITER_IP).stream().findFirst().orElse(null) : null;

        final var rateLimiter = RateLimiter.builder().endpoint(endpoint).ip(ip).userId(userId).build();

        this.rateLimiterService.checkLimits(rateLimiter);
        return ResponseEntity.noContent().build();
    }

}
