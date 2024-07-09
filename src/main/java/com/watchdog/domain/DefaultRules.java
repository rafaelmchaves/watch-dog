package com.watchdog.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultRules {

    private String rpsPerEndpoint;
    private String rpsPerUser;
    private String rpsPerIp;

}
