package com.github.sergeydoronovych.vpnclient.core;

import lombok.Builder;
import lombok.Data;

import java.util.function.Predicate;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Data
@Builder
public class SearchParams {
    private String country;
    private Boolean socks;
    private Boolean proxy;
    private Boolean pptp;
    private Boolean proxyCyberSec;
    private Boolean proxySsl;
    private Boolean wireGuardUdp;
    private Boolean skylark;

    public Predicate<ServerInfo> getPredicate() {
        return serverInfo -> {
            if (!isBlank(country) && !country.equals(serverInfo.getLocation().getCountry())) {
                return false;
            }
            if (nonNull(socks) && !socks.equals(serverInfo.getFeatures().isSocks())) {
                return false;
            }
            if (nonNull(proxy) && !proxy.equals(serverInfo.getFeatures().isProxy())) {
                return false;
            }
            if (nonNull(pptp) && !pptp.equals(serverInfo.getFeatures().isPptp())) {
                return false;
            }
            if (nonNull(proxyCyberSec) && !proxyCyberSec.equals(serverInfo.getFeatures().isProxyCyberSec())) {
                return false;
            }
            if (nonNull(proxySsl) && !proxySsl.equals(serverInfo.getFeatures().isProxySsl())) {
                return false;
            }
            if (nonNull(wireGuardUdp) && !wireGuardUdp.equals(serverInfo.getFeatures().isWireGuardUdp())) {
                return false;
            }
            if (nonNull(skylark) && !skylark.equals(serverInfo.getFeatures().isSkylark())) {
                return false;
            }
            return true;
        };
    }
}
