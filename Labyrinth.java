import java.util.*;

public class Labyrinth {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        char[][] parent = new char[n][m];

        int sx = 0, sy = 0, ex = 0, ey = 0;

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {

                map[i][j] = s.charAt(j);

                if (map[i][j] == 'A') {
                    sx = i;
                    sy = j;
                }

                if (map[i][j] == 'B') {
                    ex = i;
                    ey = j;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        boolean found = false;

        while (!q.isEmpty()) {

            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            if (x == ex && y == ey) {
                found = true;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                        !visited[nx][ny] && map[nx][ny] != '#') {

                    visited[nx][ny] = true;
                    parent[nx][ny] = dir[i];
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        sc.close();
        if (!found) {
            System.out.println("NO");
            return;
        }

        // Path reconstruction
        StringBuilder path = new StringBuilder();
        int x = ex, y = ey;

        while (x != sx || y != sy) {

            char d = parent[x][y];
            path.append(d);

            if (d == 'U') x++;
            else if (d == 'D') x--;
            else if (d == 'L') y++;
            else if (d == 'R') y--;
        }

        path.reverse();

        System.out.println("YES");
        System.out.println(path.length());
        System.out.println(path);
    }
}
