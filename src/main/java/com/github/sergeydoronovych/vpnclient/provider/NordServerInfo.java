package com.github.sergeydoronovych.vpnclient.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sergeydoronovych.vpnclient.core.ServerInfo;
import lombok.Data;

import java.net.InetSocketAddress;
import java.util.UUID;

@Data
public class NordServerInfo {
    public static final int NORD_PROXY_PORT = 80;

    private long id;
    private String ip_address;
    private String name;
    private String domain;
    private String flag;
    private String country;
    private Location location;
    private int load;
    private Features features;

    @Data
    public static class Location {
        @JsonProperty("lat")
        private double x;
        @JsonProperty("long")
        private double y;
    }

    @Data
    public static class Features {
        private boolean ikev2;
        private boolean openvpn_udp;
        private boolean openvpn_tcp;
        private boolean socks;
        private boolean proxy;
        private boolean pptp;
        private boolean l2tp;
        private boolean openvpn_xor_udp;
        private boolean openvpn_xor_tcp;
        private boolean proxy_cybersec;
        private boolean proxy_ssl;
        private boolean proxy_ssl_cybersec;
        private boolean ikev2_v6;
        private boolean openvpn_udp_v6;
        private boolean openvpn_tcp_v6;
        private boolean wireguard_udp;
        private boolean openvpn_udp_tls_crypt;
        private boolean openvpn_tcp_tls_crypt;
        private boolean openvpn_dedicated_udp;
        private boolean openvpn_dedicated_tcp;
        private boolean skylark;
    }

    public ServerInfo toServerInfo() {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setId(UUID.randomUUID());
        serverInfo.setName(this.getName());
        serverInfo.setAddress(InetSocketAddress.createUnresolved(this.getIp_address(), NORD_PROXY_PORT));
        serverInfo.setHost(this.getDomain());
        serverInfo.setLoad(this.getLoad());
        serverInfo.setLoad(this.getLoad());

        ServerInfo.Location location = new ServerInfo.Location();
        location.setCountry(this.getCountry());
        location.setX(this.getLocation().getX());
        location.setX(this.getLocation().getY());

        ServerInfo.Features features = new ServerInfo.Features();
        features.setSocks(this.getFeatures().isSocks());
        features.setProxy(this.getFeatures().isProxy());
        features.setPptp(this.getFeatures().isPptp());
        features.setProxyCyberSec(this.getFeatures().isProxy_ssl_cybersec());
        features.setProxySsl(this.getFeatures().isProxy_ssl());
        features.setWireGuardUdp(this.getFeatures().isWireguard_udp());
        features.setSkylark(this.getFeatures().isSkylark());
        serverInfo.setFeatures(features);

        return serverInfo;
    }
}
