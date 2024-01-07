package code;

import java.util.Scanner;

class Node {
    public int[] dist;
    public int[] from;

    public Node(int size) {
        dist = new int[size];
        from = new int[size];
    }
}

public class Five {
    public static void main(String[] args) {
        System.out.println("\n\n PROGRAM TO IMPLEMENT DISTANCE VECTOR ROUTING ALGORITHM ");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\n Enter the number of nodes : ");
        int nodes = scanner.nextInt();

        int[][] costMatrix = new int[nodes][nodes];
        Node[] DVR = new Node[nodes];

        System.out.println("\n Enter the cost matrix : ");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                costMatrix[i][j] = scanner.nextInt();
                costMatrix[i][i] = 0;
                DVR[i] = new Node(nodes);
                DVR[i].dist[j] = costMatrix[i][j];
                DVR[i].from[j] = j;
            }
        }

        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    if (DVR[i].dist[j] > costMatrix[i][k] + DVR[k].dist[j]) {
                        DVR[i].dist[j] = DVR[i].dist[k] + DVR[k].dist[j];
                        DVR[j].dist[i] = DVR[i].dist[j];
                        DVR[i].from[j] = k;
                        DVR[j].from[i] = k;
                    }
                }
            }
        }

        for (int i = 0; i < nodes; i++) {
            System.out.println("\n\n For router: " + (i + 1));
            for (int j = 0; j < nodes; j++) {
                System.out.println("\t\n node " + (j + 1) + " via " + (DVR[i].from[j] + 1) + " Distance " + DVR[i].dist[j]);
            }
        }

        scanner.close();
    }
}
