package com.example.gemfireapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

/**
 * Main Spring Boot Application class for the VMware Tanzu GemFire Client.
 *
 * This application connects to a running GemFire cluster.
 * Ensure a GemFire server is started (e.g., using gfsh) before running this application.
 * By default, it will try to connect to a server on localhost:40404.
 */
@SpringBootApplication
@ClientCacheApplication(logLevel = "info") // Configures this as a GemFire client application
@EnableEntityDefinedRegions(basePackages = "com.example.gemfireapp.model") // Scans for @Region annotated entities
@EnableGemfireRepositories(basePackages = "com.example.gemfireapp.repository") // Scans for Spring Data GemFire repositories
public class MyGemfireAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGemfireAppApplication.class, args);
    }

}
