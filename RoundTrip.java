import java.util.*;

public class RoundTrip {

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        private int readByte() throws Exception {
            if (ptr >= len) {
                len = System.in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws Exception {
            int c;
            while ((c = readByte()) <= ' ');
            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = readByte();
            }
            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }
    }

    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static int[] parent;

    static int cycleStart = -1;
    static int cycleEnd = -1;

    static boolean dfs(int u, int p) {

        visited[u] = true;

        for (int v : adj.get(u)) {

            if (v == p) continue;

            if (visited[v]) {
                cycleStart = v;
                cycleEnd = u;
                return true;
            }

            parent[v] = u;

            if (dfs(v, u)) return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a = fs.nextInt() - 1;
            int b = fs.nextInt() - 1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        visited = new boolean[n];
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) break;
            }
        }

        if (cycleStart == -1) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        ArrayList<Integer> cycle = new ArrayList<>();

        cycle.add(cycleStart);

        int cur = cycleEnd;

        while (cur != cycleStart) {
            cycle.add(cur);
            cur = parent[cur];
        }

        cycle.add(cycleStart);

        Collections.reverse(cycle);

        System.out.println(cycle.size());

        StringBuilder sb = new StringBuilder();
        for (int x : cycle) {
            sb.append(x + 1).append(" ");
        }

        System.out.println(sb);
    }
}
