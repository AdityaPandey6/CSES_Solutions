import java.util.*;
public class TrailingZeroes {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long x = 5;
        long ans = 0;
        while(n / x > 0){
            ans += n /x;
            x = x * 5;
        }
        System.out.println(ans);
        sc.close();
    }
}
