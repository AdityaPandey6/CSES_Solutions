import java.util.*;

class Edge {
    int u, v;
    long w;

    Edge(int u, int v, long w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class HighScore {

    static final long NEG_INF = -(long)1e18;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            long w = sc.nextLong();

            edges.add(new Edge(u, v, w));
            graph.get(u).add(v);  
        }
        sc.close();
        long[] dist = new long[n + 1];
        Arrays.fill(dist, NEG_INF);

        dist[1] = 0;

        // Step 1: Relax N-1 times
        for (int i = 1; i <= n - 1; i++) {
            for (Edge e : edges) {

                if (dist[e.u] != NEG_INF &&
                    dist[e.u] + e.w > dist[e.v]) {

                    dist[e.v] = dist[e.u] + e.w;
                }
            }
        }

        boolean[] bad = new boolean[n + 1];

        for (Edge e : edges) {

            if (dist[e.u] != NEG_INF &&
                dist[e.u] + e.w > dist[e.v]) {

                bad[e.v] = true;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (bad[i]) q.add(i);
        }

        boolean[] visited = new boolean[n + 1];

        while (!q.isEmpty()) {

            int u = q.poll();

            if (u == n) {
                System.out.println(-1);
                return;
            }

            for (int v : graph.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }

        System.out.println(dist[n]);
    }
}
