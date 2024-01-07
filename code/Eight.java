package code;

import java.net.*;

public class Eight {
    public static void main(String[] args) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName("www.youtube.com");

            System.out.println("IP Addresses for www.youtube.com:");
            for (InetAddress address : addresses) {
                System.out.println(address.getHostAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Error looking up the IP addresses: " + e.getMessage());
        }
    }
}
