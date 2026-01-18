import java.util.*;
public class CountingRooms {
static void dfs(int si, int sj, int n, int m, char[][] map, boolean[][] visited) {

    Stack<int[]> stack = new Stack<>();
    stack.push(new int[]{si, sj});

    while (!stack.isEmpty()) {

        int[] curr = stack.pop();
        int i = curr[0];
        int j = curr[1];

        if (i < 0 || j < 0 || i >= n || j >= m) continue;
        if (map[i][j] == '#' || visited[i][j]) continue;

        visited[i][j] = true;

        stack.push(new int[]{i + 1, j});
        stack.push(new int[]{i - 1, j});
        stack.push(new int[]{i, j + 1});
        stack.push(new int[]{i, j - 1});
    }
}


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        for(int i = 0 ; i< n ;i++){
            String s = sc.nextLine();
            int j = 0 ;
            for(char ch : s.toCharArray()){
                map[i][j++] = ch;
            }
        }
        int count_rooms = 0;
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(map[i][j] == '.' && !visited[i][j]){
                    count_rooms++;
                    dfs(i , j ,n , m , map , visited);
                }
            }
        }
        System.out.println(count_rooms);
        sc.close();
    }
}
