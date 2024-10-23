import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleContest{
    //* Attributes
    private ArrayList<Character> moves;
    private ArrayList<ArrayList> solutions;
    //* Methods
    public boolean solve(char[][] start, char[][] ending) {
        this.moves = new ArrayList<>();
        solutions = new ArrayList<>();
        Puzzle puzzle = new Puzzle(start, ending);
        System.out.println(puzzle.getEndingBoard());
        solutions.add(solutions(puzzle, new ArrayList<String>(), new ArrayList<Character>()));
        System.out.println(this.solutions);
        return !this.moves.isEmpty();
    }

    public void simulate(char[][] start, char[][] ending) {
        Puzzle puzzle = new Puzzle(start, ending);
        ArrayList<Character> solution = solutions(puzzle, new ArrayList<String>(), new ArrayList<Character>());
        if (solution != null) {
            for (Object character : solution) {
                System.out.println(character);
            }
        } else {
            System.out.println("No solution found");
        }
    }



    //* Private Methods

    private ArrayList<Character> solutions(Puzzle puzzle, ArrayList<String> memory, ArrayList<Character> moves) {
        if (Arrays.deepToString(puzzle.getActualBoard()).equals(Arrays.deepToString(puzzle.getEndingBoard()))) {
            System.out.println(Arrays.deepToString(puzzle.getActualBoard()));
            return moves;
        }

        if (memory.contains(Arrays.deepToString(puzzle.getActualBoard()))) {
            return null;
        }

        memory.add(Arrays.deepToString(puzzle.getActualBoard()));

        ArrayList<Character> solutionR = solutions(rotate(puzzle, 'R'), new ArrayList<>(memory), addMove(new ArrayList<>(moves), 'R'));
        if (solutionR != null && solutionR.size() > this.moves.size()) {
            this.moves = solutionR;
        }

        ArrayList<Character> solutionL = solutions(rotate(puzzle, 'L'), new ArrayList<>(memory), addMove(new ArrayList<>(moves), 'L'));
        if (solutionL != null && solutionL.size() > this.moves.size()) {
            this.moves = solutionL;
        }

        ArrayList<Character> solutionU = solutions(rotate(puzzle, 'U'), new ArrayList<>(memory), addMove(new ArrayList<>(moves), 'U'));
        if (solutionU != null && solutionU.size() > this.moves.size()) {
            this.moves = solutionU;
        }

        ArrayList<Character> solutionD = solutions(rotate(puzzle, 'D'), new ArrayList<>(memory), addMove(new ArrayList<>(moves), 'D'));
        if (solutionD != null && solutionD.size() > this.moves.size()) {
            this.moves = solutionD;
        }

        if (!this.moves.isEmpty()) {
            return this.moves;
        } else {
            return null;
        }
    }

    private Puzzle rotate(Puzzle puzzle, char direction) {
        puzzle.tilt(direction);
        return puzzle;
    }

    private ArrayList<String> addMemory(Puzzle puzzle, ArrayList<String> memory, char direction) {
        puzzle.tilt(direction);
        memory.add(Arrays.deepToString(puzzle.getActualBoard()));
        return memory;
    }

    private ArrayList<Character> addMove(ArrayList<Character> moves, char direction) {
        moves.add(direction);
        return moves;
    }
}