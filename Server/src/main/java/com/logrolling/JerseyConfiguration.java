package com.logrolling;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.logrolling");
        property("com.sun.jersey.api.json.POJOMappingFeature", "true");

        register(new org.glassfish.jersey.jackson.JacksonFeature());
    }
}