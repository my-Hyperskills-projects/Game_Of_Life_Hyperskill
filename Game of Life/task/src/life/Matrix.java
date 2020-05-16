package life;

import java.util.Random;

public class Matrix {
    private Random random = new Random();
    private boolean[][] matrix;
    int generation;

    public Matrix() {
        matrix = new boolean[20][20];
        fillMatrix();
    }

    public Matrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    private void fillMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextBoolean();
            }
        }
    }

    public int getAliveCount() {
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) count++;
            }
        }

        return count;
    }

    public int getGeneration() {
        return generation;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
