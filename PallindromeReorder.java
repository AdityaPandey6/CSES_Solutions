import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class PallindromeReorder {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if (str == null) return;
        str = str.trim();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int oddCount = 0;
        Character oddChar = null;
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            if ((e.getValue() & 1) == 1) {
                oddCount++;
                oddChar = e.getKey();
            }
        }

        if (oddCount > 1) {
            System.out.println("NO SOLUTION");
            return;
        }

        List<Character> chars = new ArrayList<>(map.keySet());
        Collections.sort(chars);
        StringBuilder half = new StringBuilder();
        for (char c : chars) {
            int times = map.get(c) / 2; 
            for (int i = 0; i < times; i++) half.append(c);
        }

        String middle = "";
        if (oddCount == 1 && oddChar != null) {
            middle = String.valueOf(oddChar);
        }

        StringBuilder result = new StringBuilder();
        result.append(half);
        result.append(middle);
        result.append(half.reverse());

        System.out.println(result.toString());
    }
}
