import java.util.*;
public class TwoKnights {
    public static long ways(int i){
        long totalWays = ((long) i * i * (i * i - 1)) / 2;
        long attackWays = 4 * (i-1)*(i-2);
        return totalWays - attackWays;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 1 ; i <= n ; i++){
            System.out.println(ways(i));
        }
        sc.close();
    }
}
