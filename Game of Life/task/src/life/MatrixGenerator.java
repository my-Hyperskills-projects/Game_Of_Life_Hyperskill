package life;

import javafx.util.Pair;
import java.util.ArrayList;

public class MatrixGenerator {
    boolean[][] nextGen;
    boolean[][] currentGen;
    int generation;

    public MatrixGenerator(Matrix matrix) {
        this.currentGen = matrix.getMatrix();
        nextGen = new boolean[currentGen.length][currentGen.length];
        generation = matrix.getGeneration() + 1;
    }

    public Matrix nextGeneration() {
        for (int i = 0; i < currentGen.length; i++) {
            for (int j = 0; j < currentGen[i].length; j++) {
                nextGen[i][j] = controller(i, j);
            }
        }

        Matrix nextGenMatrix = new Matrix(nextGen);
        nextGenMatrix.setGeneration(generation);
        return nextGenMatrix;
    }

    private boolean controller(int i, int j) {
        int count = neighborCount(i, j);

        if (currentGen[i][j]) {
            if (count == 2 || count == 3) return true;
        } else {
            if (count == 3) return true;
        }

        return false;
    }

    private int neighborCount(int i, int j) {
        int count = 0;
        ArrayList<Pair<Integer, Integer>> neighborsCoordinates = new ArrayList<>();

        if (i == 0) {
            neighborsCoordinates.add(new Pair<>(currentGen.length - 1, j));
            if (j == 0) {
                neighborsCoordinates.add(new Pair<>(currentGen.length - 1, currentGen[0].length - 1));
            } else {
                neighborsCoordinates.add(new Pair<>(currentGen.length - 1, j - 1));
            }
            if (j == currentGen[0].length - 1) {
                neighborsCoordinates.add(new Pair<>(currentGen.length - 1, 0));
            } else {
                neighborsCoordinates.add(new Pair<>(currentGen.length - 1, j + 1));
            }
        } else {
            neighborsCoordinates.add(new Pair<>(i - 1, j));
            if (j == 0) {
                neighborsCoordinates.add(new Pair<>(i - 1, currentGen[0].length - 1));
            } else {
                neighborsCoordinates.add(new Pair<>(i - 1, j - 1));
            }
            if (j == currentGen[0].length - 1) {
                neighborsCoordinates.add(new Pair<>(i - 1, 0));
            } else {
                neighborsCoordinates.add(new Pair<>(i - 1, j + 1));
            }
        }

        if (i == currentGen.length - 1) {
            neighborsCoordinates.add(new Pair<>(0, j));
            if (j == 0) {
                neighborsCoordinates.add(new Pair<>(0, currentGen[0].length - 1));
            } else {
                neighborsCoordinates.add(new Pair<>(0, j - 1));
            }
            if (j == currentGen[0].length - 1) {
                neighborsCoordinates.add(new Pair<>(0, 0));
            } else {
                neighborsCoordinates.add(new Pair<>(0, j + 1));
            }
        } else {
            neighborsCoordinates.add(new Pair<>(i + 1, j));
            if (j == 0) {
                neighborsCoordinates.add(new Pair<>(i + 1, currentGen[0].length - 1));
            } else {
                neighborsCoordinates.add(new Pair<>(i + 1, j - 1));
            }
            if (j == currentGen[0].length - 1) {
                neighborsCoordinates.add(new Pair<>(i + 1, 0));
            } else {
                neighborsCoordinates.add(new Pair<>(i + 1, j + 1));
            }
        }

        if (j == 0) {
            neighborsCoordinates.add(new Pair<>(i, currentGen[0].length - 1));
        } else {
            neighborsCoordinates.add(new Pair<>(i, j - 1));
        }

        if (j == currentGen[0].length - 1) {
            neighborsCoordinates.add(new Pair<>(i, 0));
        } else {
            neighborsCoordinates.add(new Pair<>(i, j + 1));
        }

        for (Pair<Integer, Integer> pair : neighborsCoordinates) {
            if (currentGen[pair.getKey()][pair.getValue()]) {
                count++;
            }
        }

        return count;
    }

}
