package com.github.sergeydoronovych.vpnclient.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.sergeydoronovych.vpnclient.core.Client;
import com.github.sergeydoronovych.vpnclient.core.ServerInfo;
import com.github.sergeydoronovych.vpnclient.core.VpnProvider;

import java.util.List;

import static com.github.sergeydoronovych.vpnclient.provider.NordApiConstants.SERVER_INFO_ENDPOINT;
import static java.util.stream.Collectors.toList;

public class NordVpnProvider implements VpnProvider {
    private final Client httpClient;

    public NordVpnProvider() {
        this.httpClient = new Client();
    }

    public List<ServerInfo> getServers() {
        return httpClient.get(new TypeReference<List<NordServerInfo>>() {}, SERVER_INFO_ENDPOINT)
                .stream().map(NordServerInfo::toServerInfo).collect(toList());
    }
}
