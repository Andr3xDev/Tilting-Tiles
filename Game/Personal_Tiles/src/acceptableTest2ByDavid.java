import puzzle.Fixed;
import puzzle.Puzzle;
import puzzle.Tiles;

public class acceptableTest2ByDavid {
    static int time = 1000;
    public static void main(String[] args) {
        System.out.println("This is an acceptable test");
        System.out.println("Juan updated the puzzle to get the new features");
        System.out.println("Juan created a puzzle with 5 rows and 5 columns");
        Puzzle puzzle = new Puzzle(5, 5);
        try {
            Thread.sleep(time);
            System.out.println("Juan's daughter placed a red tile in the bottom left corner");
            puzzle.addTile(4, 0, "red");
            Thread.sleep(time);
            System.out.println("Juan's daughter placed a fixed tile in the top right corner");
            puzzle.addFixed(3, 3, "blue");
            Thread.sleep(time);
            System.out.println("juan's daughter tried to relocate the fixed tile");
            Thread.sleep(time);
            Tiles fixed = puzzle.getTile(3, 3);
            int [] to = new int[]{3,3};
            int[] from = new int[]{1,1};
            fixed.relocateTile(from,to);
        } catch (Exception e) {
            System.out.println("It trows this error: " + e.getMessage());
        }
        System.out.println("Juan's daughter tried to eliminate the fixed tile, because she didn't like it");
        try {
            puzzle.deleteTile(3, 3);
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter made a hole and tilt the board to eliminate de fixed tile");
        try{
            puzzle.makeHole(0, 3);
            Thread.sleep(time);
            puzzle.tilt('U');
            Thread.sleep(time);
            puzzle.actualArrangemment();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter put a rough tile");
        try{
            Thread.sleep(time);
            puzzle.makeInvisible();
            System.out.println("Juan reset the puzzle and select the option to add a board custom with goal");
            Thread.sleep(time);
            char[][] starting = new char[][]{{'r','r','.'},{'.','r','.'},{'.','.','.'},{'.','.','.'}};
            char[][] ending = new char[][]{{'.','.','.'},{'.','.','.'},{'.','r','.'},{'r','r','.'}};
            Puzzle puzzle2 = new Puzzle(starting, ending);
            System.out.println("Juan's daughter wanted to see, if the board was in the final state");
            System.out.println(puzzle2.isGoal());
            Thread.sleep(time);
            System.out.println("Juan's daughter wanted to see, the objective board");
            //! missing print of the objective board
            System.out.println("She tried to tilt the board down and then see if she completed the puzzle");
            puzzle2.tilt('D');
            System.out.println(puzzle2.isGoal());
            Thread.sleep(time);

            //? NO se que mas poner de momento

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
