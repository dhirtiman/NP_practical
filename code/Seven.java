package code;

import java.net.*;

public class Seven {
    public static void main(String[] args) {
        try {
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println("Local Host Address: " + localHostAddress.getHostAddress());

            String localHostName = localHostAddress.getHostName();
            System.out.println("Local Host Name: " + localHostName);
        } catch (UnknownHostException e) {
            System.err.println("Error finding local host information: " + e.getMessage());
        }
    }
}
