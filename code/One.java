package code;

import java.util.Scanner;

public class One {

    private static String performCRCDetection(String data, String divisor) {
        int dataLength = data.length();
        int divisorLength = divisor.length();

        data = data + "0".repeat(divisorLength - 1);

        char[] dataArray = data.toCharArray();
        char[] divisorArray = divisor.toCharArray();

        for (int i = 0; i < dataLength; i++) {
            if (dataArray[i] == '1') {
                for (int j = 0; j < divisorLength; j++) {
                    dataArray[i + j] = (dataArray[i + j] == divisorArray[j]) ? '0' : '1';
                }
            }
        }

        // The CRC code is the original data followed by the remainder
        String crcCode = data.substring(0, dataLength) + new String(dataArray, dataLength, divisorLength - 1);
        return crcCode;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter data in binary: ");
        String data = scanner.nextLine();

        System.out.print("Enter divisor in binary: ");
        String divisor = scanner.nextLine();

        String crcCode = performCRCDetection(data, divisor);
        System.out.println("CRC Code: " + crcCode);

        System.out.print("Enter data to be sent (with possible errors): ");
        String receivedData = scanner.nextLine();

        String receivedCRC = performCRCDetection(receivedData, divisor);
        if (receivedCRC.equals("0")) {
            System.out.println("No errors detected. Data received successfully.");
        } else {
            System.out.println("Errors detected in the received data.");
        }

        scanner.close();
    }
}

