package com.watchdog.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RateLimiter {

    private String ip;
    private String endpoint;
    private String userId;

}
