package code.Four;

import java.net.*;
import java.util.*;
import java.io.*;

public class Client {
    static Socket connection;

    public static void main(String[] args) {
        try {
            int v[] = new int[10];
            int n = 0;

            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr);

            connection = new Socket(addr, 8011);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            DataInputStream in = new DataInputStream(connection.getInputStream());

            int p = in.read();
            System.out.println("No. of Frames is: " + p);

            for (int i = 0; i < p; i++) {
                v[i] = in.read();
                System.out.println(v[i]);
            }

            int rand = new Random().nextInt(p);
            v[rand] = -1;

            for (int i = 0; i < p; i++) {
                System.out.println("Received frame is: " + v[i]);
            }

            for (int i = 0; i < p; i++) {
                if (v[i] == -1) {
                    System.out.println("Requesting to retransmit from packet no: " + (i + 1) + " again...");
                    n = i;
                    out.write(n);
                    out.flush();
                }
            }

            System.out.println();
            v[n] = in.read();
            System.out.println("Received frame is: " + v[n]);
            System.out.println("Quitting...");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
