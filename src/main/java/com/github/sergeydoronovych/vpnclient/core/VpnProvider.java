package com.github.sergeydoronovych.vpnclient.core;

import java.util.List;
import java.util.stream.Collectors;

public interface VpnProvider {
    List<ServerInfo> getServers();

    default List<ServerInfo> getServers(String country) {
        return getServers().stream().filter(serverInfo -> serverInfo.getLocation().getCountry().equals(country)).collect(Collectors.toList());
    }

    default List<ServerInfo> getServers(String country, boolean proxy) {
        return getServers().stream().filter(serverInfo -> serverInfo.getLocation().getCountry().equals(country) && serverInfo.getFeatures().isProxy() == proxy).collect(Collectors.toList());
    }

    default List<String> getCountries() {
        return getServers().stream().map(ServerInfo::getLocation).map(ServerInfo.Location::getCountry).collect(Collectors.toList());
    }
}
