package com.example.base64operator.component;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class ActuatorInfoContributor implements InfoContributor {
    private final AtomicLong requestCount = new AtomicLong(0);

    @Override
    public void contribute(Info.Builder builder) {
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        builder.withDetail("currentTime", Instant.now().toString())
                .withDetail("uptime", formatUptime(uptimeMs));
    }

    private String formatUptime(long ms) {
        long s = ms / 1000;
        long d = s / 86400;
        long h = (s % 86400) / 3600;
        long m = (s % 3600) / 60;
        long sec = s % 60;
        return String.format("%dd %dh %dm %ds", d, h, m, sec);
    }

}
