package com.example.deploymentdemo.controller;

import com.example.deploymentdemo.config.ApplicationProperties;
import com.example.deploymentdemo.dto.BuildInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeploymentController {

    private final ApplicationProperties applicationProperties;
    private final String applicationName;

    public DeploymentController(
            ApplicationProperties applicationProperties,
            @Value("${spring.application.name}") String applicationName) {

        this.applicationProperties = applicationProperties;
        this.applicationName = applicationName;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {

        return ResponseEntity.ok(
                Map.of(
                        "status", "UP",
                        "application", applicationName
                )
        );
    }

    @GetMapping("/build-info")
    public ResponseEntity<BuildInfoResponse> buildInfo() {

        BuildInfoResponse response = new BuildInfoResponse(
                applicationName,
                applicationProperties.getVersion(),
                applicationProperties.getBranch(),
                applicationProperties.getBuildNumber(),
                applicationProperties.getEnvironment(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> welcome() {

        return ResponseEntity.ok(
                Map.of(
                        "message", "Welcome to the deployment demo application",
                        "status", "running"
                )
        );
    }
}