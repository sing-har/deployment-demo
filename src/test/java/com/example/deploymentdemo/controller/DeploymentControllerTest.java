package com.example.deploymentdemo.controller;

import com.example.deploymentdemo.config.ApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeploymentControllerTest {

    private DeploymentController deploymentController;

    @BeforeEach
    void setUp() {

        ApplicationProperties properties = new ApplicationProperties();
        properties.setVersion("test-version");
        properties.setBranch("test-branch");
        properties.setBuildNumber("1");
        properties.setEnvironment("test");

        deploymentController =
                new DeploymentController(properties, "deployment-demo");
    }

    @Test
    void shouldReturnApplicationHealth() {

        ResponseEntity<Map<String, String>> response =
                deploymentController.health();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("UP", response.getBody().get("status"));
        assertEquals(
                "deployment-demo",
                response.getBody().get("application")
        );
    }

    @Test
    void shouldReturnBuildInformation() {

        var response = deploymentController.buildInfo();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(
                "deployment-demo",
                response.getBody().application()
        );
        assertEquals(
                "test-branch",
                response.getBody().branch()
        );
        assertEquals(
                "test",
                response.getBody().environment()
        );
    }

    @Test
    void shouldReturnWelcomeMessage() {

        ResponseEntity<Map<String, String>> response =
                deploymentController.welcome();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(
                "Welcome to the deployment demo application",
                response.getBody().get("message")
        );
        assertEquals(
                "running",
                response.getBody().get("status")
        );
    }
}