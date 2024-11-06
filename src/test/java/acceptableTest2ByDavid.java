
import puzzle.*;

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
            Thread.sleep(time);
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
            Thread.sleep(time);
            puzzle.deleteTile(3, 3);
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter made a hole and tilt the board to eliminate de fixed tile");
        try{
            Thread.sleep(time);
            puzzle.makeHole(0, 3);
            Thread.sleep(time);
            puzzle.tilt('U');
            Thread.sleep(time);
            puzzle.actualArrangemment();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter saw a button that said 'rough', " +
                "so she tried to put a rough tile after another normal tile");
        try {
            Thread.sleep(time);
            puzzle.addRough(2, 3, "green");
            puzzle.addTile(4, 3, "red");
            Thread.sleep(time);
            System.out.println("Juan's daughter tried to tilt the board to eliminate the rough tile with the hole");
            Thread.sleep(time);
            puzzle.tilt('U');
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Juan's daughter now wanted to eliminate the rough tile");
        try {
            Thread.sleep(time);
            puzzle.deleteTile(2, 3);
            Thread.sleep(time);
            System.out.println("Now, Juan's daughter saw a button that said 'flying', so she tried to put a flying tile");
            Thread.sleep(time);
            puzzle.addFlying(2, 3, "white");
            Thread.sleep(time);
            System.out.println("Juan's daughter tried to tilt the board to eliminate the flying tile");
            Thread.sleep(time);
            puzzle.tilt('U');
            Thread.sleep(time);
            System.out.println("Juan's daughter decided to eliminate the flying tile");
            Thread.sleep(time);
            puzzle.deleteTile(0, 3);
            Thread.sleep(time);
            System.out.println("Juan's daughter saw a button that said 'Freelance', so she tried to put a Freelance tile");
            Thread.sleep(time);
            puzzle.addFreelance(0, 0, "blue");
            Thread.sleep(time);
            System.out.println("Juan's daughter tried to add glue to the Freelance tile");
            Thread.sleep(time);
            puzzle.addGlue(0, 0);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}