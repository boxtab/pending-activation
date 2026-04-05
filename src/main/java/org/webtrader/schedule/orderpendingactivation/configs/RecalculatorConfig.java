package org.webtrader.schedule.orderpendingactivation.configs;

public record RecalculatorConfig
        (
                String host,
                String port,
                String token
        ) {}
