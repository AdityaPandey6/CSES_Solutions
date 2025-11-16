import java.util.*;

public class IncreasingArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long prev = sc.nextLong();
        long moves = 0;

        for (int i = 1; i < n; i++) {
            long curr = sc.nextLong();

            if (curr < prev) {
                moves += (prev - curr);
            } else {
                prev = curr;
            }
        }

        System.out.println(moves);
        sc.close();
    }
}
