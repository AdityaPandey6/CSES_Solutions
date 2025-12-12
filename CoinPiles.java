import java.io.BufferedReader;
import java.io.InputStreamReader;
public class CoinPiles {
    public static void main(String[] args) throws Exception{
        // Scanner sc = new Scanner(System.in);
        // long n = sc.nextLong();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long n = Long.parseLong(br.readLine().trim());
        while(n-- > 0){
            // long a = sc.nextLong();
            // long b = sc.nextLong();
            String[] parts = br.readLine().split(" ");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            if((a+b) % 3 == 0 && Math.min(a,b) * 2 >= Math.max(a,b)){
                sb.append("YES").append("\n");
            }
            else{
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
        // sc.close();
    }
}
