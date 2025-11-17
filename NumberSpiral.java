import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class NumberSpiral {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String[] parts = br.readLine().split(" ");
            long y = Long.parseLong(parts[0]);
            long x = Long.parseLong(parts[1]);

            long ans;

            if (x >= y) {
                if (x % 2 == 1) {
                    ans = x * x - y + 1;
                } else {
                    ans = (x - 1) * (x - 1) + y;
                }
            } else {
                if (y % 2 == 0) {
                    ans = y * y - x + 1;
                } else {
                    ans = (y - 1) * (y - 1) + x;
                }
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
