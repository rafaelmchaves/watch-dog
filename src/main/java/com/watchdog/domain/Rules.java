package com.watchdog.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Rules {

    private DefaultRules defaultRules;
    private EndpointRules endpointRules;
}
