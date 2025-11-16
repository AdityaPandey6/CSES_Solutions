import java.util.*;
public class Repetitions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int curr_len = 1 , max_len = Integer.MIN_VALUE;
        char curr_char = s.charAt(0);
        for(int i = 1 ; i < s.length() ; i++){
            if(s.charAt(i) == curr_char){
                curr_len++;
                max_len = Math.max(curr_len , max_len);
            }
            else{
                curr_char = s.charAt(i);
                curr_len = 1;
            }
        }
        System.out.println(max_len == Integer.MIN_VALUE ? 1 : max_len);
        sc.close();
    }
}