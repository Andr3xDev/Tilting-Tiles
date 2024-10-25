import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleContest {
    //* Attributes
    private ArrayList<Character> finalMove;

    //* Methods
    public boolean solve(char[][] start, char[][] ending) {
        this.finalMove = new ArrayList<>();
        ArrayList<String> memory = new ArrayList<>();
        ArrayList<Character> moves = new ArrayList<>();
        solutions(start, ending, memory, moves);
        System.out.println("Movimientos mínimos: " + this.finalMove);
        return !this.finalMove.isEmpty();
    }

    //* Private Methods
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
