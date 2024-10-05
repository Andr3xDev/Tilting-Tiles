import java.util.ArrayList;

public class PuzzleContest{
    //* Attributes
    ArrayList<ArrayList<Integer>> solutions;
    boolean found;

    //* Methods
    public boolean solve(char[][] start, char[][] ending) {
        //ArrayList<Integer> moves = new ArrayList<>();
        return solutions(start, ending);
    }

    public void simulate(char[][] start, char[][] ending) {
        Puzzle puzzle = new Puzzle(start, ending);
    }

    //* Private Methods

    private boolean solutions (char[][]start, char[][]ending) {
        Puzzle puzzle = new Puzzle(start, ending);
        if (puzzle.isGoal()) {
            //solutions.add(moves);
            found = true;
            return true;
        }
        if (found) {
            return true;
        }
        return  solutions(rotate(puzzle,'R'), ending) ||
                solutions(rotate(puzzle,'L'), ending) ||
                solutions(rotate(puzzle,'U'), ending) ||
                solutions(rotate(puzzle,'D'), ending);
    }
    private char[][] rotate(Puzzle puzzle, char direction) {
        if (direction == 'R') {
            puzzle.tilt('R');
            return puzzle.getActualBoard();
        } else if (direction == 'L') {
            puzzle.tilt('L');
            return puzzle.getActualBoard();
        } else if (direction == 'U') {
            puzzle.tilt('U');
            return puzzle.getActualBoard();
        } else {
            puzzle.tilt('D');
            return puzzle.getActualBoard();
        }
    }
}