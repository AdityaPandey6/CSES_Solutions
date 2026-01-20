import java.io.*;
import java.util.*;

class Edge {
    int to;
    long w;

    Edge(int to, long w) {
        this.to = to;
        this.w = w;
    }
}

class State {
    int node, used;
    long cost;

    State(int node, int used, long cost) {
        this.node = node;
        this.used = used;
        this.cost = cost;
    }
}

public class FlightDiscount {

    static final long INF = (long) 1e18;

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            long w = fs.nextLong();

            graph[u].add(new Edge(v, w));
        }

        long[][] dist = new long[n + 1][2];

        for (int i = 1; i <= n; i++) {
            dist[i][0] = INF;
            dist[i][1] = INF;
        }

        PriorityQueue<State> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        dist[1][0] = 0;
        pq.add(new State(1, 0, 0));

        while (!pq.isEmpty()) {

            State cur = pq.poll();

            int u = cur.node;
            int used = cur.used;
            long d = cur.cost;

            if (d > dist[u][used]) continue;

            for (Edge e : graph[u]) {

                int v = e.to;
                long w = e.w;

                // Normal edge
                long nd = d + w;
                if (nd < dist[v][used]) {
                    dist[v][used] = nd;
                    pq.add(new State(v, used, nd));
                }

                // Discount edge
                if (used == 0) {
                    long nd2 = d + (w >> 1); // faster than w/2
                    if (nd2 < dist[v][1]) {
                        dist[v][1] = nd2;
                        pq.add(new State(v, 1, nd2));
                    }
                }
            }
        }

        System.out.println(dist[n][1]);
    }

    // -------- FAST INPUT ----------
    static class FastScanner {

        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

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
