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
        System.out.println("Movimientos mínimos: " + this.finalMove);
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

        if (sameBoard(start, ending)) {
            if (this.finalMove.isEmpty() || moves.size() < this.finalMove.size()) {
                System.out.println(moves);
                this.finalMove = new ArrayList<>(moves);
            }
            return;
        }

        String currentBoardState = Arrays.deepToString(puzzle.getActualBoard());
        if (memory.contains(currentBoardState)) {
            return;
        }

        memory.add(currentBoardState);

        char[][] originalBoard = puzzle.getActualBoard();

        puzzle.tilt('U');
        moves.add('U');
        solutions(puzzle.getActualBoard(), ending, memory, moves);
        moves.remove(moves.size() - 1);
        puzzle.setBoard(originalBoard);

        puzzle.tilt('D');
        moves.add('D');
        solutions(puzzle.getActualBoard(), ending, memory, moves);
        moves.remove(moves.size() - 1);
        puzzle.setBoard(originalBoard);

        puzzle.tilt('L');
        moves.add('L');
        solutions(puzzle.getActualBoard(), ending, memory, moves);
        moves.remove(moves.size() - 1);
        puzzle.setBoard(originalBoard);

        puzzle.tilt('R');
        moves.add('R');
        solutions(puzzle.getActualBoard(), ending, memory, moves);
        moves.remove(moves.size() - 1);
        puzzle.setBoard(originalBoard);
    }

    /**
     * This method compares two boards.
     * @param board1 one of the boards to compare.
     * @param board2 the other board to compare.
     * @return true if the boards are the same, false otherwise.
     */
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
}
