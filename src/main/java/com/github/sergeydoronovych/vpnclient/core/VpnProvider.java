package com.github.sergeydoronovych.vpnclient.core;

import java.util.List;
import java.util.stream.Collectors;

public interface VpnProvider {
    List<ServerInfo> getServers();

    default List<ServerInfo> getServers(SearchParams searchParams) {
        return getServers().stream().filter(searchParams.getPredicate()).collect(Collectors.toList());
    }

    default List<String> getCountries() {
        return getServers().stream().map(ServerInfo::getLocation).map(ServerInfo.Location::getCountry).collect(Collectors.toList());
    }
}
