import java.util.*;
import java.io.*;

class Pair {
    int node;
    long dist;

    Pair(int n, long d) {
        node = n;
        dist = d;
    }
}

public class ShortestRoute {

    static final long INF = (long)1e18;

    public static void main(String[] args) throws Exception {

        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            long w = sc.nextLong();

            adj.get(u).add(new Pair(v, w));
        }

        long[] distance = new long[n];
        Arrays.fill(distance, INF);

        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        distance[0] = 0;
        pq.add(new Pair(0, 0));

        while (!pq.isEmpty()) {

            Pair cur = pq.poll();

            int u = cur.node;
            long distU = cur.dist;

            if (distU > distance[u]) continue;

            for (Pair edge : adj.get(u)) {

                int v = edge.node;
                long w = edge.dist;

                if (distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    pq.add(new Pair(v, distance[v]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (long d : distance) {
            sb.append(d).append(" ");
        }

        System.out.println(sb);
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
