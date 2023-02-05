package service;

import io.grpc.stub.StreamObserver;
import org.example.SendPuzzleRequest;
import org.example.SendSolutionResponse;
import org.example.SolvingPuzzleGrpc;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PuzzleSolver extends SolvingPuzzleGrpc.SolvingPuzzleImplBase {
    @Override
    public void solvePuzzle(SendPuzzleRequest request, StreamObserver<SendSolutionResponse> responseObserver) {
        long startTime = System.nanoTime();

        String[] puzzleData = request.getPuzzle().split(" ");
        String[] puzzle = puzzleData[1].split(",");

        String solution = puzzleData[0] + " " + startSolving(puzzle) + " ";

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        solution += totalTime;

        SendSolutionResponse.Builder response = SendSolutionResponse.newBuilder();
        response.setSolution(solution);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    public static String startSolving(String[] puzzle) {
        int[] map = new int[26];
        Arrays.fill(map, -2);
        boolean[] usedNum = new boolean[10];

        StringBuilder uniq = new StringBuilder();
        for (String value : puzzle) {

            for (int i = 0; i < value.length(); i++) {
                char ch = value.charAt(i);
                if (map[ch - 'A'] == -2) {
                    map[ch - 'A'] = -1;
                    uniq.append(ch);
                }
            }
        }



        SolutionBoolean run = posSolution(uniq.toString(), 0, map, usedNum, puzzle);

        if(run.getSecond()){
            int[] map2 = run.getFirst();
            StringBuilder validSolution= new StringBuilder();

            for(int i = 0;i<puzzle.length;i++){
                for (char c : puzzle[i].toCharArray()){
                    validSolution.append(getNum(map2, String.valueOf(c)));
                }
                if ((i+1) % 3 == 0){
                    validSolution.append(" ");
                }
                else{
                    validSolution.append(",");
                }
            }
            return validSolution.toString();
        }

        return "0,0,0 0,0,0 0,0,0";
    }

    public static SolutionBoolean posSolution(String unique, int idx, int[] charIntMap, boolean[] usedNumbers, String[] puzzle) {
        if(idx == unique.length()) {
            if(isValid(charIntMap, puzzle)) {
                return new SolutionBoolean(charIntMap,true);
            }
            return new SolutionBoolean(charIntMap,false);
        }


        for(int i=0; i < usedNumbers.length; i++) {
            if(!usedNumbers[i]) {

                charIntMap[unique.charAt(idx) - 'A'] = i;
                usedNumbers[i] = true;

                if(posSolution(unique, idx + 1, charIntMap, usedNumbers, puzzle).getSecond()){
                    return new SolutionBoolean(charIntMap,true);
                }

                usedNumbers[i] = false;
                charIntMap[unique.charAt(idx) - 'A'] = -1;
            }
        }
        return new SolutionBoolean(charIntMap,false);
    }

    public static boolean isValid(int[] map, String[] puzzle){
        int p1 = getNum(map, puzzle[0]);
        int p2 = getNum(map, puzzle[1]);
        int p3 = getNum(map, puzzle[2]);
        int p4 = getNum(map, puzzle[3]);
        int p5 = getNum(map, puzzle[4]);
        int p6 = getNum(map, puzzle[5]);
        int p7 = getNum(map, puzzle[6]);
        int p8 = getNum(map, puzzle[7]);
        int p9 = getNum(map, puzzle[8]);

        boolean b = p1 + p2 == p3
                && p4 + p5 == p6
                && p7 + p8 == p9
                && p1 + p4 == p7
                && p2 + p5 == p8
                && p3 + p6 == p9;

        String msg = puzzle[0]+"="+p1+" "+
                puzzle[1]+"="+p2+" "+
                puzzle[2]+"="+p3+" "+
                puzzle[3]+"="+p4+" "+
                puzzle[4]+"="+p5+" "+
                puzzle[5]+"="+p6+" "+
                puzzle[6]+"="+p7+" "+
                puzzle[7]+"="+p8+" "+
                puzzle[8]+"="+p9+" ";
        if(b){
            System.out.println(msg + " = Valid solution!");
        }
        else{
            System.out.println(msg + " = No valid solution!");
        }

        try {
            TimeUnit.MILLISECONDS.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return b;
    }

    public static int getNum(int[] map, String s1) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < s1.length(); i++) {
            sb.append(map[s1.charAt(i)-'A']);
        }
        return Integer.parseInt(sb.toString());
    }

}
