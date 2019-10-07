package br.com.blz.testjava.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostnameSourceUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HostnameSourceUtil.class);
    private static final String UNKNOWN = "UNKNOWN";

    public static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOGGER.warn("Falha ao obter hostname");
        }
        return UNKNOWN;
    }
}
