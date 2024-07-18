package com.watchdog.domain.ports;

import com.watchdog.domain.RateLimiter;

public interface QuotaPort {

    int searchQuota(RateLimiter rateLimiter);

    void addQuota(RateLimiter rateLimiter);

}
