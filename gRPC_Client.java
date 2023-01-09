package Client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.SendPuzzleRequest;
import org.example.SendSolutionResponse;
import org.example.SolvingPuzzleGrpc;

import java.util.Random;

public class gRPC_Client {

    public static void main(String[] args){

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
        SolvingPuzzleGrpc.SolvingPuzzleBlockingStub stub = SolvingPuzzleGrpc.newBlockingStub(channel);

        SendPuzzleRequest request = SendPuzzleRequest.newBuilder().setPuzzle(generatePuzzle(5)).build();
        SendSolutionResponse response = stub.solvePuzzle(request);
        System.out.println(request);
        System.out.println("response: " + response.getSolution());

    }

    public static String generatePuzzle(int maxLength){
        Integer maxNumberLength = maxLength;

        String alphabet = "ABCDEFGHIJ";                     // no limit for different Letters
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

        return String.valueOf(puzzle);
    }
}
