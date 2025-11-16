import java.util.*;

public class MissingNumber {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int xOr = 0;
        for (int i = 0; i < n - 1; i++) {
            xOr ^= sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            xOr ^= i;
        }

        System.out.println(xOr);
        sc.close();
    }
}
