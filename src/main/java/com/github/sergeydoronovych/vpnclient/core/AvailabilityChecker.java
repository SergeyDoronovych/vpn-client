package com.github.sergeydoronovych.vpnclient.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.sergeydoronovych.vpnclient.security.IpInfo;

import java.net.InetSocketAddress;

import static com.github.sergeydoronovych.vpnclient.security.ApiConstants.GET_IP_INFO_ENDPOINT;

public class AvailabilityChecker {
    private final Client httpClient;

    public AvailabilityChecker(Client httpClient) {
        this.httpClient = httpClient;
    }

    public boolean isProxyAvailable(InetSocketAddress proxyAddress, String login, String password) {
        try {
            httpClient.get(new TypeReference<IpInfo>() {}, GET_IP_INFO_ENDPOINT, proxyAddress, login, password);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
