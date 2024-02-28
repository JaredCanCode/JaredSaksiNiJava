import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public boolean isValidMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return board[row][col] == '-';
        }
        return false;
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        game.printBoard();
        while (!game.isBoardFull() && !game.isWinner()) {
            System.out.println("Player " + game.currentPlayer + "'s turn:");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int col = scanner.nextInt();
            if (game.isValidMove(row, col)) {
                game.makeMove(row, col);
                game.printBoard();
                if (game.isWinner()) {
                    System.out.println("Player " + game.currentPlayer + " wins!");
                } else if (game.isBoardFull()) {
                    System.out.println("It's a draw!");
                } else {
                    game.changePlayer();
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        scanner.close();
    }
}
