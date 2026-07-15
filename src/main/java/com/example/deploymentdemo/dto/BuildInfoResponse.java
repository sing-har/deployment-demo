package com.example.deploymentdemo.dto;

import java.time.LocalDateTime;

public record BuildInfoResponse(
        String application,
        String version,
        String branch,
        String buildNumber,
        String environment,
        LocalDateTime currentTime
) {
}