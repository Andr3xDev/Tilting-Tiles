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
            System.out.println("Juan's daughter placed a fixed tile in the top right corner");
            puzzle.addFixed(3, 3, "blue");
            Thread.sleep(time);
            System.out.println("juan's daughter tried to relocate the fixed tile");
            Thread.sleep(time);
            Tiles fixed = puzzle.getTile(3, 3);
            int [] from = new int[]{3,3};
            int[] to = new int[]{1,1};
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
        System.out.println("Juan's daughter saw a button that said 'rough', so she tried to put a rough tile");
        try {
            puzzle.addRough(2, 3, "green");
            Thread.sleep(time);
            System.out.println("Juan's daughter tried to tilt the board to eliminate the rough tile with the hole");
            puzzle.tilt('U');
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter now wanted to eliminate the rough tile");
        try {
            puzzle.deleteTile(2, 3);
            Thread.sleep(time);
            System.out.println("Now, Juan's daughter saw a button that said 'flying', so she tried to put a flying tile");
            Thread.sleep(time);
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
