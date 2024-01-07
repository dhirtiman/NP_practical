package code;

import java.util.Scanner;

public class Six {
    static int[][] cost;
    static int[] dist, S, path;
    static int i, j, n, k, m, v, totcost, p;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of vertices: ");
            n = scanner.nextInt();

            System.out.print("Enter number of edges: ");
            m = scanner.nextInt();

            cost = new int[10][10];
            dist = new int[20];
            S = new int[20];
            path = new int[20];

            System.out.println("\nEnter EDGE Cost:");
            for (k = 1; k <= m; k++) {
                i = scanner.nextInt();
                j = scanner.nextInt();
                int c = scanner.nextInt();
                cost[i][j] = c;
            }

            for (i = 1; i <= n; i++) {
                for (j = 1; j <= n; j++) {
                    if (cost[i][j] == 0) {
                        cost[i][j] = 31999;
                    }
                }
            }

            System.out.print("Enter initial vertex: ");
            v = scanner.nextInt();
            System.out.println(v);

            shortest(v, n);
            scanner.close();
        }
    }

    static void shortest(int v, int n) {
        int min;
        for (i = 1; i <= n; i++) {
            S[i] = 0;
            dist[i] = cost[v][i];
        }
        path[++p] = v;
        S[v] = 1;
        dist[v] = 0;

        for (i = 2; i <= n - 1; i++) {
            int k = -1;
            min = 31999;

            for (j = 1; j <= n; j++) {
                if (dist[j] < min && S[j] != 1) {
                    min = dist[j];
                    k = j;
                }
            }

            if (cost[v][k] <= dist[k]) {
                p = 1;
            }
            path[++p] = k;

            for (j = 1; j <= p; j++) {
                System.out.print(path[j] + " ");
            }
            System.out.println();

            S[k] = 1;

            for (j = 1; j <= n; j++) {
                if (cost[k][j] != 31999 && dist[j] >= dist[k] + cost[k][j] && S[j] != 1) {
                    dist[j] = dist[k] + cost[k][j];
                }
            }
        }
    }
}
