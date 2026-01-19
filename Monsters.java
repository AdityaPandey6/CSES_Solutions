import java.util.*;

public class Monsters {

    static int n, m;
    static char[][] grid;
    static int[][] monsterTime;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[] dirChar = {'U', 'R', 'D', 'L'};

    static int[][] parentDir;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        grid = new char[n][m];
        monsterTime = new int[n][m];
        visited = new boolean[n][m];
        parentDir = new int[n][m];

        for (int[] row : monsterTime)
            Arrays.fill(row, -1);

        Queue<int[]> mq = new ArrayDeque<>();

        int sx = 0, sy = 0;

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = s.charAt(j);

                if (grid[i][j] == 'M') {
                    mq.add(new int[]{i, j});
                    monsterTime[i][j] = 0;
                }

                if (grid[i][j] == 'A') {
                    sx = i;
                    sy = j;
                }
            }
        }


        while (!mq.isEmpty()) {

            int[] cur = mq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        grid[nx][ny] != '#' &&
                        monsterTime[nx][ny] == -1) {

                    monsterTime[nx][ny] = monsterTime[x][y] + 1;
                    mq.add(new int[]{nx, ny});
                }
            }
        }
        sc.close();


        Queue<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        if (isBoundary(sx, sy)) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }

        int[][] playerTime = new int[n][m];

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m &&
                        !visited[nx][ny] &&
                        grid[nx][ny] != '#') {

                    int nextTime = playerTime[x][y] + 1;

                    if (monsterTime[nx][ny] != -1 &&
                            monsterTime[nx][ny] <= nextTime)
                        continue;

                    visited[nx][ny] = true;
                    parentDir[nx][ny] = d;
                    playerTime[nx][ny] = nextTime;

                    if (isBoundary(nx, ny)) {

                        System.out.println("YES");
                        System.out.println(nextTime);
                        printPath(nx, ny, sx, sy);
                        return;
                    }

                    pq.add(new int[]{nx, ny});
                }
            }
        }

        System.out.println("NO");
    }

    static boolean isBoundary(int x, int y) {
        return x == 0 || y == 0 || x == n - 1 || y == m - 1;
    }

    static void printPath(int ex, int ey, int sx, int sy) {

        StringBuilder sb = new StringBuilder();

        while (ex != sx || ey != sy) {

            int d = parentDir[ex][ey];
            sb.append(dirChar[d]);

            ex -= dx[d];
            ey -= dy[d];
        }

        System.out.println(sb.reverse());
    }
}
