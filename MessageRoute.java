import java.util.*;

public class MessageRoute {

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
            while ((c = readByte()) <= ' ') ;
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

    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner();

        int n = fs.nextInt();
        int m = fs.nextInt();

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();

            adj.get(a - 1).add(b - 1);
            adj.get(b - 1).add(a - 1);
        }

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        Queue<Integer> q = new ArrayDeque<>();

        q.add(0);
        visited[0] = true;
        parent[0] = -1;

        boolean found = false;

        while (!q.isEmpty()) {

            int u = q.poll();

            if (u == n - 1) {
                found = true;
                break;
            }

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

        if (!found) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        // Reconstruct path
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> path = new ArrayList<>();

        int curr = n - 1;
        while (curr != -1) {
            path.add(curr + 1);
            curr = parent[curr];
        }

        Collections.reverse(path);

        sb.append(path.size()).append('\n');

        for (int x : path) {
            sb.append(x).append(" ");
        }

        System.out.print(sb);
    }
}
