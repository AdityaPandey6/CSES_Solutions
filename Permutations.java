import java.util.*;

public class Permutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();

        if (n == 1) {
            System.out.println(1);
            return;
        }

        if (n < 4) {
            System.out.println("NO SOLUTION");
            return;
        }
        // If you not use StrigBuilder and thinking of printing using sout then you will get TLE because is is slow.
        StringBuilder sb = new StringBuilder();
        for (long i = 2; i <= n; i += 2) {
            sb.append(i).append(" ");
        }
        for (long i = 1; i <= n; i += 2) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}
