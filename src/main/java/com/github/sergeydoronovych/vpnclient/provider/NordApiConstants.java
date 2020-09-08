package com.github.sergeydoronovych.vpnclient.provider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NordApiConstants {
    public static String PROTOCOL = "https://";
    public static String NORD_VPN_HOST = PROTOCOL + "api.nordvpn.com";
    public static String AUTH_PATH = "/token/token";
    public static String AUTH_VERIFICATION_PATH = "/token/verify";
    public static String SERVER_PATH = "/server";
    public static String USER_ADDRESS_PATH = "/user/address";

    public static String SERVER_INFO_ENDPOINT = NORD_VPN_HOST + SERVER_PATH;

    public static String USER_AGENT = "NordVPN_Client_5.56.780.0";
}
