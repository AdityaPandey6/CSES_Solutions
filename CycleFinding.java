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

public class CycleFinding {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            long w = sc.nextLong();
            edges.add(new Edge(u, v, w));
        }
        sc.close();
        long[] dist = new long[n];
        int[] parent = new int[n];

        Arrays.fill(dist, 0);
        Arrays.fill(parent, -1);

        int x = -1;

        for(int i = 0; i < n; i++){
            x = -1;
            for(Edge e : edges){
                if(dist[e.u] + e.w < dist[e.v]){
                    dist[e.v] = dist[e.u] + e.w;
                    parent[e.v] = e.u;
                    x = e.v;
                }
            }
        }

        if(x == -1){
            System.out.println("NO");
            return;
        }

        // Jump into cycle
        for(int i = 0; i < n; i++){
            x = parent[x];
        }

        int start = x;
        ArrayList<Integer> cycle = new ArrayList<>();

        int cur = start;
        while(true){
            cycle.add(cur);
            cur = parent[cur];
            if(cur == start) break;
        }
        cycle.add(start);

        Collections.reverse(cycle);

        System.out.println("YES");
        for(int node : cycle){
            System.out.print((node + 1) + " ");
        }
    }
}
