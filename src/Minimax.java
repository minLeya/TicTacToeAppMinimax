public class Minimax {

    // returns position in board - int row and in col
    public static int[] findBestMove(char[][] board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[] {-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int score = minimax(board, 0, false);
                    board[i][j] = ' ';
                    if(score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    public static int evaluate(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'X')
                    return -10;
                else if (board[i][0] == 'O')
                    return 10;
            }
        }
        // Проверка столбцов
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == 'X')
                    return -10;
                else if (board[0][j] == 'O')
                    return 10;
            }
        }

        // Проверка диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X')
                return -10;
            else if (board[0][0] == 'O')
                return 10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X')
                return -10;
            else if (board[0][2] == 'O')
                return 10;
        }

        // Если нет победителя, возвращаем 0
        return 0;
    }

    public static boolean isMoveLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3;j++) {
                if(board[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    public static int minimax(char[][] board, int depth, boolean isMaximizing) {
        if(isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }

            }
            return bestScore;
        }

    }
}