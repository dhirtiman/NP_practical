package code.Two;

import java.io.*;
import java.net.*;
import java.util.*;

public class Sender {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames to send: ");
        int numberOfFrames = scanner.nextInt();

        try (Socket socket = new Socket("localhost", 12345);
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            for (int frameNumber = 1; frameNumber <= numberOfFrames; frameNumber++) {
                System.out.println("\nSending Frame " + frameNumber);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String frameMessage = "Frame " + frameNumber;
                outputStream.writeObject(frameMessage);
                outputStream.flush();

                try {
                    String acknowledgment = (String) inputStream.readObject();
                    System.out.println("Acknowledgment " + acknowledgment);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
