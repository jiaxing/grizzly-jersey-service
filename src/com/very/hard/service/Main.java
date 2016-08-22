package com.very.hard.service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import com.very.hard.service.config.AppConfig;

import java.io.IOException;
import java.net.URI;


public class Main {
    private static final URI BASE_URI = URI.create("http://0.0.0.0:8080/");

    public static void main(String[] args) throws IOException {
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, new AppConfig());
        server.start();
        System.in.read();
        server.shutdownNow();
        // Thread.currentThread().join();
    }
}
