import java.util.*;

public class TwoSets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        long totalSum = (long) n * (n + 1) / 2;

        if (totalSum % 2 != 0) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");

        long target = totalSum / 2;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        int count1 = 0 , count2 = 0;
        for (int i = n; i >= 1; i--) {
            if (i <= target) {
                s1.append(i).append(" ");
                count1++;
                target -= i;
            } else {
                count2++;
                s2.append(i).append(" ");
            }
        }

        System.out.println(count1);
        System.out.println(s1);

        System.out.println(count2);
        System.out.println(s2);
    }
}
