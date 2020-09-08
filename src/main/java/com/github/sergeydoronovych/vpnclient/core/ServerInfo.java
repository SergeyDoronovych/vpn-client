package com.github.sergeydoronovych.vpnclient.core;

import lombok.Data;

import java.net.InetSocketAddress;
import java.util.UUID;

@Data
public class ServerInfo {
    private UUID id;
    private String name;
    private InetSocketAddress address;
    private String host;
    private int load;
    private Location location;
    private Features features;

    @Data
    public static class Location{
        private String country;
        private double x;
        private double y;
    }

    @Data
    public static class Features{
        private boolean socks;
        private boolean proxy;
        private boolean pptp;
        private boolean proxyCyberSec;
        private boolean proxySsl;
        private boolean wireGuardUdp;
        private boolean skylark;
    }
}
