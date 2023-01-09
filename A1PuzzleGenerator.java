import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class A1PuzzleGenerator {
    public static void main(String[] args) {

        int maxNumberLength = 5;

        String alphabet = "ABCDEFGHIJ";                     //KLMNOPQRSTUVWXYZ no limiter for different Letters
        StringBuilder puzzle = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < 9; i++) {
            StringBuilder sb = new StringBuilder();

            int length = (int) ((Math.random() * (maxNumberLength)) + 1);

            for (int g = 0; g < length; g++) {
                int idx = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(idx);
                sb.append(randomChar);
            }

            String randomString = sb.toString();
            if(puzzle.length()==0){
                puzzle.append(randomString);
            }else{
                puzzle.append(" " + randomString);
            }
        }
        try(FileWriter fw = new FileWriter("puzzle.txt", false);
            BufferedWriter writer = new BufferedWriter(fw)) {
                writer.write(String.valueOf(puzzle));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}