package com.glb.bootcamp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MessageDev implements Message {

    @Value("${app.message}")
    String message;

    @Override
    public String setup() {
        return message;
    }
}
