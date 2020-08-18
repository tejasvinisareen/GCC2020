package com.tictactoe;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {{' ', '|', ' ', '|', ' '},
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '},
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '}};

        printBoard(board);


        Scanner input = new Scanner(System.in);
        Random rand = new Random(); // for computer (if only one player is playing)
        Set<Integer> markedPos = new HashSet<>();

        System.out.print("How many players (1-2)? ");
        int numPlayers = input.nextInt();

        while (markedPos.size() < 9) {
            int p1Pos = getPosition("Player 1", input, markedPos);
            markedPos.add(p1Pos);
            markBoard(board, true, p1Pos);
            printBoard(board);
            if (markedPos.size() > 4 && checkWin(board)) {
                System.out.println("Player 1 wins!!");
                break;
            } else if (markedPos.size() == 9) {
                System.out.println("It's a tie!");
                break;
            }

            int p2Pos;
            if (numPlayers == 2) {
                p2Pos = getPosition("Player 2", input, markedPos);
                markedPos.add(p2Pos);
            } else {
                p2Pos = rand.nextInt(9) + 1;
                while (markedPos.contains(p2Pos)) {
                    p2Pos = rand.nextInt(9) + 1;
                }
                markedPos.add(p2Pos);
                System.out.println("Computer chose: " + p2Pos);
            }
            markBoard(board, false, p2Pos);
            printBoard(board);
            if (markedPos.size() > 4 && checkWin(board)) {
                String player2 = numPlayers == 2 ? "Player 2" : "Computer";
                System.out.println(player2 + " wins!!");
                break;
            }
        }

        System.out.println("~~ GAME OVER ~~");
    }

    /**
     * Prints the board on the console
     * @param board tic tac toe board
     */
    public static void printBoard(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Marks the slot on the board with an 'X' if it's Player 1's turn
     * and with an 'O' if it's Player 2/Computer's turn
     * @param board tic tac toe board
     * @param isPlayer1 true if it's Player 1's turn; false otherwise
     * @param pos position/slot selected by the player
     */
    public static void markBoard(char[][] board, boolean isPlayer1, int pos) {
        char symbol = isPlayer1 ? 'X' : 'O';

        if (1 <= pos && pos <= 3) {
            board[0][2 * (pos - 1)] = symbol;
        } else if (4 <= pos && pos <= 6) {
            board[2][2 * (pos - 4)] = symbol;
        } else if (7 <= pos && pos <= 9) {
            board[4][2 * (pos - 7)] = symbol;
        }
    }

    /**
     * Returns true if the board has a winning line (horizontal,
     * vertical, or on either diagonals); false otherwise.
     * @param board tic tac toe board
     * @return true if there is a 'winning line'; false otherwise
     */
    public static boolean checkWin(char[][] board) {
        // check rows and columns
        for (int i = 0; i < board.length; i+=2) {
            if (board[i][0] == board[i][2] && board[i][2] == board[i][4] && board[i][0] != ' ') { // i-th row
                return true;
            } else if (board[0][i] == board[2][i] && board[2][i] == board[4][i] && board[0][i] != ' ') { //i-th column
                return true;
            }
        }

        // check 1st diagonal || 2nd diagonal
        boolean diagOne = (board[0][0] == board[2][2] && board[2][2] == board[4][4]);
        boolean diagTwo = (board[0][4] == board[2][2] && board[2][2] == board[4][0]);
        return board[2][2] != ' ' && (diagOne || diagTwo);
    }

    /**
     * Returns the position/slot provided by the player
     * @param player player's number - Player 1 / Player 2
     * @param input scanner object to obtain user's input
     * @param markedPos set of all filled positions
     * @return position/slot provided by the user
     */
    public static int getPosition(String player, Scanner input, Set<Integer> markedPos) {
        System.out.print(player + ", input position (1-9): ");
        int pos = input.nextInt();
        while (pos < 1 || pos > 9 || markedPos.contains(pos)) {
            System.out.print(player + ", please choose another number in range (1-9): ");
            pos = input.nextInt();
        }
        return pos;
    }
}
