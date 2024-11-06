package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a puzzle contest. It is used to solve a puzzle game and find the minimum number of moves to
 * solve it. represents and simulate a puzzle contest.
 */
public class PuzzleContest {

    //* Attributes
    private ArrayList<Character> finalMove;

    //* Methods
    /**
     * This method solves a puzzle game and find the minimum number of moves to solve it.
     * @param start the initial state of the puzzle game.
     * @param ending the final state of the puzzle game.
     * @return true if the puzzle game was solved, false otherwise.
     */
    public boolean solve(char[][] start, char[][] ending) {
        this.finalMove = new ArrayList<>();
        ArrayList<String> memory = new ArrayList<>();
        ArrayList<Character> moves = new ArrayList<>();
        solutions(start, ending, memory, moves);
        return !this.finalMove.isEmpty();
    }

    /**
     * This method simulates a puzzle game whit GUI.
     * @param start the initial state of the puzzle game.
     * @param ending the final state of the puzzle game.
     */
    public void simulate(char[][] start, char[][] ending) {
        this.finalMove = new ArrayList<>();
        ArrayList<String> memory = new ArrayList<>();
        ArrayList<Character> moves = new ArrayList<>();
        solutions(start, ending, memory, moves);
        System.out.println("Movimientos mínimos: " + this.finalMove);
    }


    //* Private Methods

    /**
     * This method finds the minimum number of moves to solve a puzzle game.
     * @param start the initial state of the puzzle game.
     * @param ending the final state of the puzzle game.
     * @param memory the memory of the states of the puzzle game.
     * @param moves the moves to solve the puzzle game.
     */
    private void solutions(char[][] start, char[][] ending, ArrayList<String> memory, ArrayList<Character> moves) {
        Puzzle puzzle = new Puzzle(start, ending);

        // Comprobamos si ya se alcanzó el estado objetivo
        if (sameBoard(start, ending)) {
            if (this.finalMove.isEmpty() || moves.size() < this.finalMove.size()) {
                this.finalMove = new ArrayList<>(moves);
            }
            return;
        }

        // Representación del estado actual del tablero como cadena para evitar ciclos
        String currentBoardState = Arrays.deepToString(puzzle.getActualBoard());
        if (memory.contains(currentBoardState)) {
            return;
        }

        memory.add(currentBoardState);  // Añadir el estado actual a la memoria
        char[][] originalBoard = cloneBoard(puzzle.getActualBoard());  // Clonar el estado original del tablero

        // Intentar cada dirección de movimiento
        char[] directions = {'U', 'D', 'L', 'R'};
        for (char direction : directions) {
            puzzle.tilt(direction);
            moves.add(direction);
            solutions(puzzle.getActualBoard(), ending, memory, moves);
            moves.remove(moves.size() - 1);  // Retrocedemos el movimiento
            puzzle.setBoard(originalBoard);  // Restaurar el tablero original
        }

        memory.remove(memory.size() - 1);  // Eliminar el estado actual para otros caminos
    }

    private boolean sameBoard(char[][] board1, char[][] board2) {
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[0].length; j++) {
                if (board1[i][j] != board2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Clonar un tablero 2D
    private char[][] cloneBoard(char[][] board) {
        char[][] clonedBoard = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            clonedBoard[i] = board[i].clone();
        }
        return clonedBoard;
    }
}
