import puzzle.Puzzle;

public class acceptableTest1ByAndres {
    static int time = 1000;
    public static void main(String[] args) {
        System.out.println("This is an acceptable test");
        System.out.println("Juan created a puzzle with 4 rows and 4 columns");
        Puzzle puzzle = new Puzzle(4, 4);

        try {
            Thread.sleep(time);
            System.out.println("Juan placed a red tile in the top left corner to test the puzzle");
            puzzle.addTile(0, 0, "red");
            Thread.sleep(time);     
            System.out.println("Juan placed a blue tile in the bottom right corner to test the puzzle");
            puzzle.addTile(3, 3, "blue");
            Thread.sleep(time);
            System.out.println("Juan gave the puzzle to his daughter to play with it");
            System.out.println("Juan's daughter decided to put a green tile over the red tile");
            Thread.sleep(time);
            puzzle.addTile(0, 0, "green");
        } catch (Exception e) {
            System.out.println("It trows this error: " + e.getMessage());
        }
        System.out.println("Juan's daughter got mad because she couldn't put the green tile over the red tile");
        System.out.println("Now wanted to get off the red tile to put the green tile");
        try {
            puzzle.deleteTile(0, 0);
            Thread.sleep(time);
            System.out.println("Juan's daughter put the green tile in the top left corner");
            puzzle.addTile(0, 0, "green");
            Thread.sleep(time);
            System.out.println("Juan's daughter saw a button that said 'tilt', so she tried to tilt the puzzle to up");
            System.out.println("But she tried in spanish, so she pressed the 'A' key");
            Thread.sleep(time);
            puzzle.tilt('A');
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        try{
            Thread.sleep(time);
            System.out.println("Now in english, Juan's daughter tried to tilt the puzzle to the right");
            puzzle.tilt('R');
            Thread.sleep(time);
            System.out.println("She did it again, but now to the left then to the bottom after she add some tiles");
            Thread.sleep(time);
            puzzle.addTile(1, 1, "black");
            puzzle.addTile(3, 2, "white");
            Thread.sleep(time);
            puzzle.tilt('L');
            Thread.sleep(time);
            puzzle.tilt('D');
            Thread.sleep(time);
            System.out.println("Juan's daughter saw a button that said 'glue', so she tried to glue the black tile");
            puzzle.addGlue(2, 0);
            Thread.sleep(time);
            System.out.println("She tried to glue the board, not a tile");
            puzzle.addGlue(4, 4);
            //! glued tile missing
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        try{
            Thread.sleep(time);
            puzzle.makeInvisible();
            System.out.println("Juan reset the puzzle and select the option to add a board custom with goal");
            Thread.sleep(time);
            char[][] ending = new char[][]{{'r','r'},{'.','r'},{'.','.'}};
            char[][] starting = new char[][]{{'r','.'},{'.','r',},{'.','r'}};
            Puzzle puzzle2 = new Puzzle(starting, ending);
            System.out.println("Juan's daughter wanted to see, if the board was in the final state");
            System.out.println(puzzle2.isGoal());
            Thread.sleep(time);
            System.out.println("Juan's daughter wanted to see, the objective board");
            //! missing print of the objective board
            System.out.println("She tried to tilt the board down and then see if she completed the puzzle");
            System.out.println(puzzle2.isGoal());
            


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}