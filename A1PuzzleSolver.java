import java.io.*;
import java.util.*;

public class A1PuzzleSolver {

    public static void main(String[] args) {

        String line;
        try {
            FileReader fileReader = new FileReader("puzzle.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            bufferedReader.close();
            } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> puzzle = Arrays.asList(line.split("\\s+"));

        try(FileWriter fw = new FileWriter("output.txt", false);
            BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write("Puzzle: ");
            for (String s : puzzle) {
                writer.write(s + " ");
            }
            writer.newLine();
            writer.write("Solutions: ");
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String s1 = puzzle.get(0);
        String s2 = puzzle.get(1);
        String s3 = puzzle.get(2);
        String s4 = puzzle.get(3);
        String s5 = puzzle.get(4);
        String s6 = puzzle.get(5);
        String s7 = puzzle.get(6);
        String s8 = puzzle.get(7);
        String s9 = puzzle.get(8);

        HashMap<Character, Integer> charIntMap = new HashMap<>();
        uniqueChars(s1, charIntMap);
        uniqueChars(s2, charIntMap);
        uniqueChars(s3, charIntMap);
        uniqueChars(s4, charIntMap);
        uniqueChars(s5, charIntMap);
        uniqueChars(s6, charIntMap);
        uniqueChars(s7, charIntMap);
        uniqueChars(s8, charIntMap);
        uniqueChars(s9, charIntMap);

        StringBuilder unique = new StringBuilder();
        charIntMap.forEach((key, value) -> unique.append(key));

        boolean[] usedNumbers = new boolean[10];
        solution(unique.toString(), 0, charIntMap, usedNumbers, s1, s2, s3, s4, s5, s6, s7, s8, s9);
    }
    public static void uniqueChars(String s, HashMap<Character, Integer> charIntMap){
        for (int i = 0; i < s.length(); i++) {
            if (!charIntMap.containsKey(s.charAt(i))) {
                charIntMap.put(s.charAt(i), -1);
            }
        }
    }
    public static int getNum(String s, HashMap<Character, Integer> charIntMap){
        StringBuilder num = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            num.append(charIntMap.get(s.charAt(i)));
        }
        return Integer.parseInt(num.toString());
    }
    public static List<String> solution(String unique, int idx, HashMap<Character, Integer> charIntMap, boolean[] usedNumbers,
                                        String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        if(idx == unique.length()){
            List<String> output = new ArrayList<>();
            int num1 = getNum(s1, charIntMap);
            int num2 = getNum(s2, charIntMap);
            int num3 = getNum(s3, charIntMap);
            int num4 = getNum(s4, charIntMap);
            int num5 = getNum(s5, charIntMap);
            int num6 = getNum(s6, charIntMap);
            int num7 = getNum(s7, charIntMap);
            int num8 = getNum(s8, charIntMap);
            int num9 = getNum(s9, charIntMap);

            if(        num1 + num2 == num3
                    && num4 + num5 == num6
                    && num7 + num8 == num9
                    && num1 + num4 == num7
                    && num2 + num5 == num8
                    && num3 + num6 == num9)
            {
                StringBuilder sol = new StringBuilder();
                for(int i = 0; i < 26; i++){
                    char ch = (char)('A' + i);
                    if(charIntMap.containsKey(ch)){
                        sol.append(ch).append("=").append(charIntMap.get(ch)).append(" ");
                    }
                }

                output.add(String.valueOf(sol));
            }
            return output;
        }

        char ch = unique.charAt(idx);
        List<String> test;
        for(int num = 0; num <= 9; num++){
            if(!usedNumbers[num]){
                charIntMap.put(ch, num);
                usedNumbers[num] = true;
                test = solution(unique, idx + 1, charIntMap, usedNumbers, s1, s2, s3, s4, s5, s6, s7, s8, s9);
                if(test!=null&&!test.isEmpty()){
                    try(FileWriter fw = new FileWriter("output.txt", true);
                        BufferedWriter writer = new BufferedWriter(fw)) {
                        writer.write(test.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                usedNumbers[num] = false;
                charIntMap.put(ch, -1);
            }
        }
        return null;
    }
}