import java.util.*;

public class Anagram {
    private static Map<String,List<String>> anagramsMap = new HashMap<>(100);
    public static void main(String[] args) {

        String[] input = {
            "akka", "akak", "baab", "baba", "bbaa"
        };

        for (String s: input) {
            char[] word = s.toCharArray();
            Arrays.sort(word);
            String key = String.valueOf(word);
            if (!anagramsMap.containsKey(key)) {
                anagramsMap.put(key, new ArrayList < String > ());
            }

            anagramsMap.get(key).add(s);
        }

        System.out.println("anagramsMap = " + anagramsMap);
    }
}