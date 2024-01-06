package code.Two;

import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        System.out.println("Receiver waiting for frames...");

        try (ServerSocket serverSocket = new ServerSocket(12345);
                Socket socket = serverSocket.accept();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            int numberOfFrames = 0;

            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    String receivedMessage = (String) inputStream.readObject();
                    if (receivedMessage == null) {
                        break;
                    }
                    numberOfFrames++;
                    System.out.println("Received: " + receivedMessage);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    String acknowledgment = "received for Frame " + numberOfFrames;
                    outputStream.writeObject(acknowledgment);
                    outputStream.flush();
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("\nAll frames received successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
