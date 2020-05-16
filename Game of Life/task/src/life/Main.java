package life;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Matrix matrix;
    static GameOfLife game;

    public static void main(String[] args) throws InterruptedException {
        matrix = new Matrix();
        game = new GameOfLife();

        for (int i = 0; true; i++) {
            game.imagineCurrentState(matrix.getMatrix(), matrix.getGeneration(), matrix.getAliveCount());
            MatrixGenerator generator = new MatrixGenerator(matrix);
            matrix = generator.nextGeneration();
            Thread.sleep(500);
            while (game.isStopped()) {
                Thread.sleep(500);
            }
        }

    }

    public static void startAgain() {
        matrix = new Matrix();
    }
}
