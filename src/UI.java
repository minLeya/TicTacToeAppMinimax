import javax.swing.*;
import java.awt.*;

class UI {
    private final int SIZE = 3;
    JFrame frame = new JFrame();
    JButton[][] buttons = new JButton[SIZE][SIZE];
    boolean isHuman = true;

    public UI() {
        frame.setSize(300, 400);
        frame.setTitle("Tic Tac Toe");
        frame.setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(SIZE, SIZE));
        initializeButtons(gamePanel);

        JPanel controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> handleResetButtonClick());
        controlPanel.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void initializeButtons(JPanel gamePanel) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));

                int row = i;
                int col = j;
                buttons[i][j].addActionListener(e -> handleButtonClick(row, col));
                buttons[i][j].setFocusPainted(false);
                gamePanel.add(buttons[i][j]);
            }
        }
    }

    private void handleButtonClick(int row, int col) {
        if (buttons[row][col].getText().isEmpty()) {
            buttons[row][col].setText("X");

            if (hasWon("X")){
                winMessage("X");
            } else if (isBoardFull()) {
                winMessage(null);
            } else {
                isHuman = false;
                computerMove();
            }
        }
    }

    private void handleResetButtonClick() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        isHuman = true;
    }

    private void computerMove() {
        int[] bestMove = Minimax.findBestMove(getBoard());
        buttons[bestMove[0]][bestMove[1]].setText("O");
        if (hasWon("O")) {
            winMessage("O");
        }
        isHuman = true;
    }

    private void winMessage(String player) {
        if (player != null) {
            JOptionPane.showMessageDialog(null, player + " won!");
        }
        else if(isBoardFull()){
            JOptionPane.showMessageDialog(null, "Appears to be a tie!");
        }
    }

    private boolean hasWon(String character) {
        // горизонталь
        for (int i = 0; i < SIZE; i++) {
            if(buttons[i][0].getText().equals(buttons[i][1].getText())
                    && buttons[i][1].getText().equals(buttons[i][2].getText())
                    && !buttons[i][2].getText().isEmpty() && buttons[i][0].getText().equals(character)) {
                return true;
            }
        }

        //вертикаль
        for(int j = 0; j < SIZE; j++) {
            if(buttons[0][j].getText().equals(buttons[1][j].getText())
                    && buttons[1][j].getText().equals(buttons[2][j].getText())
                    && !buttons[2][j].getText().isEmpty() && buttons[0][j].getText().equals(character)) {
                return true;
            }
        }

        //диагонали
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[2][2].getText().isEmpty() && buttons[0][0].getText().equals(character)) {
            return true;
        }

        else if(buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())
                && !buttons[2][0].getText().isEmpty() && buttons[0][2].getText().equals(character)) {
            return true;
        }

        else {
            return false;
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private char[][] getBoard() {
        char[][] board = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                board[i][j] = buttons[i][j].getText().isEmpty() ? ' ' : buttons[i][j].getText().charAt(0);
            }
        }
        return board;
    }

    public static void main(String[] args) {
            new UI();
    }
}