import java.util.*;

public class BuildingRoads {

    static void bfs(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {

            int u = q.poll();

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

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
            int x = fs.nextInt();
            int y = fs.nextInt();
            adj.get(x - 1).add(y - 1);
            adj.get(y - 1).add(x - 1);
        }

        boolean[] visited = new boolean[n];
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans.add(i + 1);
                bfs(i, adj, visited);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size() - 1).append('\n');

        for (int i = 1; i < ans.size(); i++) {
            sb.append(ans.get(i - 1)).append(" ").append(ans.get(i)).append('\n');
        }

        System.out.print(sb);
    }
}
