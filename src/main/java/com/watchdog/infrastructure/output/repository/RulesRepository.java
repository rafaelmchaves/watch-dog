package com.watchdog.infrastructure.output.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watchdog.domain.DefaultRules;
import com.watchdog.domain.Rules;
import com.watchdog.domain.ports.RulesPort;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RulesRepository implements RulesPort {

    @Override
    public Rules findAllRules() {
        ObjectMapper mapper = new ObjectMapper();
        DefaultRules defaultRules = null;
        InputStream inputStream = DefaultRules.class.getResourceAsStream("/rules/default-rules.json");
        try {
            defaultRules = mapper.readValue(inputStream, DefaultRules.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Rules.builder().defaultRules(defaultRules).build();
    }
}
