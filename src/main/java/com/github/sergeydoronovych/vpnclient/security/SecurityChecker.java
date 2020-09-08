package com.github.sergeydoronovych.vpnclient.security;


import com.fasterxml.jackson.core.type.TypeReference;
import com.github.sergeydoronovych.vpnclient.core.Client;

import java.net.InetSocketAddress;

import static com.github.sergeydoronovych.vpnclient.security.ApiConstants.GET_IP_INFO_ENDPOINT;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class SecurityChecker {
    private final Client httpClient;

    public SecurityChecker() {
        this.httpClient = new Client();
    }

    public boolean isIpHidden(InetSocketAddress proxyAddress, String login, String password) {
        String realIp;
        String proxiedIp;
        try {
            realIp = httpClient.get(new TypeReference<IpInfo>() {
            }, GET_IP_INFO_ENDPOINT).getIp();

            proxiedIp = httpClient.get(new TypeReference<IpInfo>() {
            }, GET_IP_INFO_ENDPOINT, proxyAddress, login, password).getIp();
        } catch (Exception e) {
            return false;
        }
        return (!isBlank(realIp) || !isBlank(proxiedIp)) && !realIp.equals(proxiedIp);
    }
}
