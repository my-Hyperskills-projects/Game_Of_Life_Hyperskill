package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {

    private JLabel generationLabel;
    private JLabel aliveLabel;
    private GamePanel gameField;
    private JPanel settings;
    private JPanel buttonsField;
    private JToggleButton pauseOrResume;
    private JButton playAgain;

    private boolean isStopped = false;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        initDesk();
    }

    private void initDesk() {
        settings = new JPanel();
        settings.setSize(200, 600);
        settings.setLayout(new BoxLayout(settings, BoxLayout.Y_AXIS));

        buttonsField = new JPanel();
        buttonsField.setLayout(new GridLayout(1, 2, 20, 20));
        buttonsField.setBounds(10, 10, 180, 80);

        pauseOrResume = new JToggleButton("PlayToggleButton");
        playAgain = new JButton("ResetButton");
        pauseOrResume.setName("PlayToggleButton");
        playAgain.setName("ResetButton");

        pauseOrResume.addActionListener(e -> {
            isStopped = !isStopped;
        });
        playAgain.addActionListener(e -> Main.startAgain());

        buttonsField.add(pauseOrResume);
        buttonsField.add(playAgain);
        settings.add(buttonsField);

        generationLabel = new JLabel("Generation #0");
        aliveLabel = new JLabel("Alive: 0");
        generationLabel.setName("GenerationLabel");
        aliveLabel.setName("AliveLabel");
        generationLabel.setBounds(40, 20, 160, 40);
        aliveLabel.setBounds(40, 20, 160, 40);
        settings.add(generationLabel);
        settings.add(aliveLabel);

        gameField = new GamePanel(20, 600);

        add(settings);
        add(gameField);
    }

    void imagineCurrentState(boolean[][] matrix, int generation, int aliveCount) {
        generationLabel.setText("Generation #" + generation);
        aliveLabel.setText("Alive: " + aliveCount);
        gameField.imagineCurrentGameState(matrix);
    }



    class GamePanel extends JPanel {
        GamePanel(int n, int size) {
            super();
            this.setSize(size, size);

            fillGameField(n, size);

            this.setLayout(new GridLayout(n, n, 1, 1));
            this.setBackground(Color.BLACK);
        }

        public void setCellCount(int n) {
            Component[] components = this.getComponents();
            for (Component component : components) {
                this.remove(component);
            }

            fillGameField(n, this.getWidth());
        }

        private void fillGameField(int n, int size) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    JPanel cell = new JPanel();
                    cell.setBackground(Color.LIGHT_GRAY);
                    int cellSize = size - (n - 1) / n;
                    cell.setSize(cellSize, cellSize);
                    this.add(cell);
                }
            }
        }

        void imagineCurrentGameState(boolean[][] matrix) {
            Component[] components = this.getComponents();
            int i = 0;
            int j = 0;

            for (Component cell : components) {
                if (j == 20) {
                    j = 0;
                    i++;
                }

                if (!matrix[i][j]) {
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setBackground(Color.BLACK);
                }

                j++;
            }
        }
    }

    boolean isStopped() {
        return isStopped;
    }
}