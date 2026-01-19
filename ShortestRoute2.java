import java.io.*;
import java.util.*;

public class ShortestRoute2 {

    static final long INF = (long)1e18;

    public static void main(String[] args) throws Exception {

        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        long[][] graph = new long[n][n];

        // Initialization
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        // Read edges
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            long w = sc.nextLong();

            graph[u][v] = Math.min(graph[u][v], w);
            graph[v][u] = Math.min(graph[v][u], w);

        }

        // Floyd Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {

                if (graph[i][k] == INF) continue;

                for (int j = 0; j < n; j++) {

                    if (graph[k][j] == INF) continue;

                    long newDist = graph[i][k] + graph[k][j];

                    if (newDist < graph[i][j]) {
                        graph[i][j] = newDist;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // Answer queries
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            if (graph[u][v] == INF)
                sb.append(-1).append('\n');
            else
                sb.append(graph[u][v]).append('\n');
        }

        System.out.print(sb);
    }

    // FAST INPUT
    static class FastScanner {

        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buffer = new byte[1 << 16];
        int ptr = 0, len = 0;

        int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            long val = 0;
            int c;
            do {
                c = read();
            } while (c <= ' ');

            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
