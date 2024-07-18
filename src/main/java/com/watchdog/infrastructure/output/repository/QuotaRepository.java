package com.watchdog.infrastructure.output.repository;

import com.watchdog.domain.RateLimiter;
import com.watchdog.domain.ports.QuotaPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Component
public class QuotaRepository implements QuotaPort {

    private final Jedis jedis;

    @Override
    public int searchQuota(RateLimiter rateLimiter) {

        final var oneMinuteAgo = LocalDateTime.now().minusSeconds(60).toInstant(ZoneOffset.UTC).toEpochMilli();
        final var now = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        jedis.zrange(rateLimiter.getUserId(), oneMinuteAgo, now).forEach(System.out::println);
        return (int) jedis.zcount(rateLimiter.getUserId(), oneMinuteAgo, now);

    }

    @Override
    public void addQuota(RateLimiter rateLimiter) {
        LocalDateTime now = LocalDateTime.now();
        final var timestamp = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        jedis.zadd(rateLimiter.getUserId(),timestamp , String.valueOf(jedis.zcard(rateLimiter.getUserId()) + 1));
    }
}
