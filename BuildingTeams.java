import java.util.*;
public class BuildingTeams {
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

    static boolean flag = false;
    static void bfs(int i , ArrayList<ArrayList<Integer>>adj , int[] teams , boolean[] visited){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);
        visited[i] = true;
        teams[i] = 1;

        while(!q.isEmpty()){
            int u = q.poll();
            for(int x : adj.get(u)){
                if(!visited[x] && teams[x] == 0){
                    q.add(x);
                    visited[x] = true;
                    if(teams[u] == 1) teams[x] = 2;
                    if(teams[u] == 2) teams[x] = 1;
                }
                else if(teams[x] == teams[u]){
                    flag = true;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
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

        boolean[]visited = new boolean[n];
        int[] teams = new int[n];

        for(int i = 0; i < n ; i++){
            if(!visited[i]){
                bfs(i , adj , teams , visited);
            }
            if(flag){
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n ; i++){
            sb.append(teams[i]).append(" ");
        }
        System.out.println(sb);
    }
}
