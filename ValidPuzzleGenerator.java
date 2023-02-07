import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ValidPuzzleGenerator {
    public static void main(String[] args) {

        int maxNumberLength = 5;

        int[] x = generate(maxNumberLength);

        try(FileWriter fw = new FileWriter("puzzle.txt", false);
            BufferedWriter writer = new BufferedWriter(fw)) {
            writer.write(toLetters(x));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static int[] generate(int maxNumberLength){     //0 1 2  3 4 5  6 7 8
        int[] x = new int[9];
        x[0]=generateRandomDigits(maxNumberLength-1);
        x[1]=generateRandomDigits(maxNumberLength-1);
        x[3]=generateRandomDigits(maxNumberLength-1);
        x[4]=generateRandomDigits(maxNumberLength-1);

        x[2]=x[0] + x[1];
        x[5]=x[3] + x[4];
        x[6]=x[0] + x[3];
        x[7]=x[1] + x[4];
        x[8]=x[6] + x[7];
        if(maxNumberLength==1){
            if(x[8]<=9 && x[2] + x[5]==x[8]){
                return x;
            }
            else{
                return generate(maxNumberLength);
            }
        }

        if (x[2] + x[5]==x[8]){
            return x;
        }
        return generate(maxNumberLength);
    }

    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n);
        if(n>1){
            m = (int) Math.pow(10, n - 1);
        }
        return m + new Random().nextInt(9 * m);
    }

    public static String toLetters(int[] x){
        StringBuilder s = new StringBuilder();
        for (int i : x) {
            s.append(i).append(" ");
        }
        String d = s.toString();
        return d.replace("0","A")
                .replace("1","B")
                .replace("2","C")
                .replace("3","D")
                .replace("4","E")
                .replace("5","F")
                .replace("6","G")
                .replace("7","H")
                .replace("8","I")
                .replace("9","J");
    }
}
