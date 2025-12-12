import java.util.*;
import java.math.*;
public class BitStrings {
    public static void main(String[] args){
        int mod = 1_000_000_007;
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        BigInteger two = BigInteger.valueOf(2);
        BigInteger ans = two.modPow(BigInteger.valueOf(n), BigInteger.valueOf(mod)); 
        System.out.println(ans);
        sc.close();
    }
}
