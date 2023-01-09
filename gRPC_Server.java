package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.PuzzleSolver;
import java.io.IOException;

public class gRPC_Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9091).addService(new PuzzleSolver()).build();
        server.start();
        System.out.println("Server started on Port: " + server.getPort());
        server.awaitTermination();
    }
}
