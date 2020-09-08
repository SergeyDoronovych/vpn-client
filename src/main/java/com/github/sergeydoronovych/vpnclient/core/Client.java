package com.github.sergeydoronovych.vpnclient.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private final ObjectMapper objectMapper;

    public Client() {
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T get(TypeReference<T> typeRef, String url) {
        return get(typeRef, url, HttpClient.newHttpClient());
    }

    public <T> T get(TypeReference<T> typeRef, String url, InetSocketAddress proxyAddress, String login, String password) {
        HttpClient httpClient = HttpClient.newBuilder()
                .proxy(ProxySelector.of(proxyAddress))
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                login,
                                password.toCharArray());
                    }
                })
                .build();
        return get(typeRef, url, httpClient);
    }

    public <T> T get(TypeReference<T> typeRef, String url, HttpClient httpClient) {
        HttpRequest request = buildRequest(url);
        return get(typeRef, request, httpClient);
    }

    @SneakyThrows
    public <T> T get(TypeReference<T> typeRef,HttpRequest request, HttpClient httpClient) {
        String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(body, typeRef);
    }


    private HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }
}
