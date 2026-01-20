import java.io.*;
import java.util.*;

public class FlightRoutes {

    static class FastScanner {
        byte[] buf = new byte[1 << 16];
        int idx = 0, size = 0;

        int read() throws IOException {
            if (idx >= size) {
                size = System.in.read(buf);
                idx = 0;
                if (size <= 0) return -1;
            }
            return buf[idx++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') ;
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') ;
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = read();
            }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }
    }

    static class Edge {
        int to;
        long w;
        Edge(int t, long w) {
            this.to = t;
            this.w = w;
        }
    }

    static class State {
        int v;
        long cost;
        State(int v, long c) {
            this.v = v;
            this.cost = c;
        }
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();
        int k = fs.nextInt();

        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = fs.nextInt() - 1;
            int v = fs.nextInt() - 1;
            long w = fs.nextLong();
            adj[u].add(new Edge(v, w));
        }

        // dist arrays
        long[][] dist = new long[n][k];
        int[] sz = new int[n];

        PriorityQueue<State> pq =
            new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));

        pq.add(new State(0, 0));
        dist[0][0] = 0;
        sz[0] = 1;

        while (!pq.isEmpty()) {

            State cur = pq.poll();
            int u = cur.v;
            long cost = cur.cost;

            // prune outdated
            long worst = -1;
            for (int i = 0; i < sz[u]; i++)
                worst = Math.max(worst, dist[u][i]);

            if (cost > worst) continue;

            for (Edge e : adj[u]) {

                long newCost = cost + e.w;
                int v = e.to;

                if (sz[v] < k) {
                    dist[v][sz[v]++] = newCost;
                    pq.add(new State(v, newCost));
                } else {

                    int idx = 0;
                    for (int i = 1; i < k; i++)
                        if (dist[v][i] > dist[v][idx])
                            idx = i;

                    if (dist[v][idx] > newCost) {
                        dist[v][idx] = newCost;
                        pq.add(new State(v, newCost));
                    }
                }
            }
        }

        Arrays.sort(dist[n - 1], 0, sz[n - 1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sz[n - 1]; i++)
            sb.append(dist[n - 1][i]).append(" ");

        System.out.println(sb);
    }
}
