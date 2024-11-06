import static org.junit.jupiter.api.Assertions.*;

import puzzle.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 Test class for the domain.Puzzle class methods, these check the correct functioning of the program internally.
 Cases are verified that should be fulfilled and others that should not. *
 * @author Andres Felipe Chavarro Plazas
 * @author David Santiago Espinosa Rojas
 */
public class PuzzleTest {

    /**
     * Default constructor for test class PuzzleTest
     */
    public PuzzleTest() {}

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {}


    //* constructors
    @Test
    public void shouldCreatePuzzle1(){
        Puzzle puzzle = new Puzzle(4, 4);
        assertEquals(puzzle.getHeight(),4);
    }
    @Test
    public void shouldNotCreatePuzzle1(){
        assertThrows(java.lang.NegativeArraySizeException.class, () -> new Puzzle(-4, 4));
    }
    @Test
    public void shouldCreatePuzzle2(){
        char[][] finish = new char[][]{{'r','r','r'},{'.','r','.'},{'.','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test
    public void shouldCreatePuzzle2E(){
        char[][] finish = new char[][]{{'r',99,'r'},{'.','r','.'},{'.','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test
    public void shouldCreatePuzzle3(){
        char[][] finish = new char[][]{{'r','r','r'},{'.','r','.'},{'.','.','.'}};
        char[][] start = new char[][]{{'r','.','.'},{'.','b','.'},{'w','.','.'}};
        Puzzle puzzle = new Puzzle(start, finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test
    public void shouldNotCreatePuzzle3(){
        char[][] finish = new char[][]{{2,'r','r'},{'.','r','.'},{'.','.','.'}};
        char[][] start = new char[][]{{1,'.','.'},{'.','b','.'},{'w','.','.'}};
        Puzzle puzzle = new Puzzle(start,finish);
        assertNotEquals(puzzle.getTile(0,0).getName(),'r');
    }

    //* Inserting tiles
    @Test
    public void shouldInsertTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
    }
    @Test
    public void shouldNotInsertTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(10, 1, "red");
        });
    }

    //* Deleting tiles
    @Test
    public void shouldDeleteTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.deleteTile(1, 1);
    }
    @Test
    public void shouldNotDeleteTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(1, 1, "red");
            puzzle.deleteTile(0, 0);
        });
    }
    @Test
    public void shouldNotDeleteTile2() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(1, 1, "red");
            puzzle.deleteTile(-1, 110);
        });
    }

    //* Relocating tiles
    @Test
    public void shouldRelocateTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        Tiles tileInitial = puzzle.getTile(1, 1);
        tileInitial.relocateTile(new int[]{1,1}, new int[]{0,0});
    }
    @Test
    public void shouldNotRelocateTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(4, 3);
            puzzle.addTile(1, 1, "green");
            Tiles tileStart = puzzle.getTile(1, 1);
            tileStart.relocateTile(new int[]{1,1}, new int[]{1000,0});
        });
    }
    @Test
    public void shouldNotRelocateTile2() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(4, 3);
            puzzle.addTile(1, 1, "green");
            Tiles tileStart = puzzle.getTile(1, 1);
            tileStart.relocateTile(new int[]{1,0}, new int[]{1,-1});
        });
    }

    //* Add glue
    @Test
    public void shouldAddGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            assertEquals(puzzle.getGlue(0, 0).getType(), 'g');
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldNotAddGlue(){
        assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(-1, 1000);
        });
    }

    //* Get glue
    @Test
    public void shouldGetGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            assertNotNull(puzzle.getGlue(0, 0));
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldNotGetGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            assertNull(puzzle.getGlue(2, 2));
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldActualGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            puzzle.actualGlue();
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldNotActualGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.actualGlue();
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    //* Delete glue
    @Test
    public void shouldDeleteGlue(){
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            puzzle.deleteGlue(0, 0);
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldNotDeleteGlue(){
        assertThrows(java.lang.NullPointerException.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addGlue(0, 0);
            puzzle.deleteGlue(0,1);
        });
    }

    //* Make hole
    @Test
    public void shouldMakeHole() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.makeHole(1, 1);
            puzzle.addTile(1, 1, "blue");
        });
    }
    @Test
    public void shouldNotMakeHole() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(1, 1, "blue");
            puzzle.makeHole(1, 1);
        });
    }

    //* Make visible
    @Test
    public void shouldMakeVisible() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeInvisible();
        puzzle.makeVisible();
        Tiles tile = puzzle.getTile(1, 1);
        assertTrue(tile.getVisible());
    }

    //* Make invisible
    @Test
    public void shouldMakeInvisible() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeInvisible();
        Tiles tile = puzzle.getTile(1, 1);
        assertFalse(tile.getVisible());
    }

    //* Misplaced tiles
    @Test
    public void shouldMisplacedTiles() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.deleteTile(1, 0);
        assertEquals(8, puzzle.getMissingSpace());
    }
    @Test
    public void shouldNotMisplacedTiles() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.addTile(0, 0, "blue");
        assertNotEquals(puzzle.getMissingSpace(), 2);
    }

    //* Tilt puzzle
    @Test
    public void shouldTiltPuzzle() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.tilt('R');
        puzzle.tilt('D');
        puzzle.tilt('L');
        puzzle.tilt('U');
        Tiles tile = puzzle.getTile(0, 0);
        assertNotNull(tile);
    }

    //* is goal
    @Test
    public void shouldIsGoal() throws puzzleExceptions {
        char[][] start = new char[][]{{'r','.','.'},{'.','.','.'},{'.','.','.'}};
        char[][] ending = new char[][]{{'r','.','.'},{'.','.','.'},{'.','.','r'}};
        Puzzle puzzle = new Puzzle(start,ending);
        puzzle.addTile(2, 2, "red");
        assertTrue(puzzle.isGoal());
    }
    @Test
    public void shouldIsNotGoal() throws puzzleExceptions {
        char[][] ending = new char[][]{{'r','.','r'},{'.','.','.'},{'.','.','r'}};
        char[][] start = new char[][]{{'r','.','r'},{'.','r','.'},{'.','.','.'}};
        Puzzle puzzle = new Puzzle(start,ending);
        puzzle.addTile(2, 2, "red");
        assertFalse(puzzle.isGoal());
    }


    //* Fixed tiles
    @Test
    public void shouldWorkFixedTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addFixed(1, 1, "red");
        puzzle.tilt('U');
    }
    @Test
    public void shouldNotWorkFixedTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFixed(10, 1, "red");
            puzzle.deleteTile(10, 1);
            puzzle.getTile(1,1).relocateTile(new int[]{1,1}, new int[]{0,0});
        });
    }


    //* Rough tiles
    @Test
    public void shouldWorkRoughTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addRough(1, 1, "red");
        Tiles tile = puzzle.getTile(1, 1);
        tile.relocateTile(new int[]{1,1}, new int[]{0,0});
        puzzle.deleteTile(0, 0);
    }
    @Test
    public void shouldNotWorkRoughTile() {
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addRough(1, 1, "red");
            puzzle.deleteTile(1, 1);
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void shouldNotWorkRoughTile2() {
        try {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addRough(-1, 100, "red");
        } catch (puzzleExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    //* Freelance tiles
    @Test
    public void shouldWorkFreelanceTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFreelance(1, 1, "red");
            Tiles tile = puzzle.getTile(1, 1);
            tile.relocateTile(new int[]{1,1}, new int[]{0,0});
            puzzle.addGlue(0, 0);
        });
    }
    @Test
    public void shouldNotWorkFreelanceTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFreelance(10, 0, "red");
        });
    }

    //* Flying tiles
    @Test
    public void shouldWorkFlyingTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addFlying(0, 0, "red");
        puzzle.makeHole(0, 2);
        puzzle.tilt('R');
        puzzle.tilt('D');
        puzzle.tilt('L');
        puzzle.tilt('U');
    }
    @Test
    public void shouldNotWorkFlyingTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFlying(10, 1, "red");
        });
    }

    //* Temporal tiles
    @Test
    public void shouldWorkTemporalTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTemporal(0, 0, "red");
        Tiles tile = puzzle.getTile(0, 0);
        tile.relocateTile(new int[]{0,0}, new int[]{1,1});
        puzzle.tilt('R');
        puzzle.tilt('L');
        puzzle.tilt('R');
        assertEquals(9, puzzle.getMissingSpace());
    }
    @Test
    public void shouldWorkTemporalTile2() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTemporal(0, 0, "red");
        Tiles tile = puzzle.getTile(0, 0);
        tile.relocateTile(new int[]{0,0}, new int[]{1,1});
        puzzle.tilt('U');
        puzzle.tilt('D');
        puzzle.tilt('U');
        assertEquals(9, puzzle.getMissingSpace());
    }
    @Test
    public void shouldWorkTemporalTile3() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTemporal(0, 0, "red");
        Tiles tile = puzzle.getTile(0, 0);
        tile.relocateTile(new int[]{0,0}, new int[]{1,1});
        puzzle.tilt('D');
        puzzle.tilt('L');
        puzzle.tilt('R');
        assertEquals(9, puzzle.getMissingSpace());
    }
    @Test
    public void shouldWorkTemporalTile4() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTemporal(0, 0, "red");
        Tiles tile = puzzle.getTile(0, 0);
        tile.relocateTile(new int[]{0,0}, new int[]{1,1});
        puzzle.tilt('L');
        puzzle.tilt('D');
        puzzle.tilt('U');
        assertEquals(9, puzzle.getMissingSpace());
    }
    @Test
    public void shouldNotWorkTemporalTile() {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTemporal(10, 1, "red");
        });
    }

    //* Puzzle contest
    @Test
    public void shouldWorkPuzzleContest() {
        char[][] start = new char[][]{{'r','.','.'},{'.','.','.'},{'.','.','.'}};
        char[][] ending = new char[][]{{'.','.','.'},{'.','.','.'},{'.','.','r'}};
        PuzzleContest contest = new PuzzleContest();
        assertTrue(contest.solve(start, ending));
    }
    @Test
    public void shouldNotWorkPuzzleContest() {
        char[][] start = new char[][]{{'r','.','.','.','.','.'}};
        char[][] ending = new char[][]{{'r','.','r','.','.','.'}};
        PuzzleContest contest = new PuzzleContest();
        assertFalse(contest.solve(start, ending));
    }

    @Test
    public void shouldWorkPuzzleContestS(){
        char[][] start = new char[][]{{'r','.','.'},{'.','.','.'},{'.','.','.'}};
        char[][] ending = new char[][]{{'.','.','.'},{'.','.','.'},{'.','.','r'}};
        PuzzleContest contest = new PuzzleContest();
        contest.simulate(start, ending);
    }
    @Test
    public void shouldNotWorkPuzzleContestS() {
        char[][] start = new char[][]{{'r','.','.','.','.','.'}};
        char[][] ending = new char[][]{{'r','.','r','.','.','.'}};
        PuzzleContest contest = new PuzzleContest();
        contest.simulate(start, ending);
    }


    //* ActualArrangement
    @Test
    public void shouldWorkActualArrangement() {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.actualArrangemment();
    }


    //* Add fragile
//    @Test
//    public void shouldAddFragile(){
//        try {
//            Puzzle puzzle = new Puzzle(3, 3);
//            puzzle.addTile(0, 0, "red");
//            puzzle.addTile(0, 1, "red");
//            puzzle.addFragile(0, 0);
//            assertEquals(puzzle.getGlue(0, 0).getType(), 'f');
//        } catch (puzzleExceptions e) {
//            System.out.println(e.getMessage());
//        }
//    }
    @Test
    public void shouldNotAddFragile(){
        assertThrows(java.lang.ArrayIndexOutOfBoundsException.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(0, 0, "red");
            puzzle.addTile(0, 1, "red");
            puzzle.addFragile(-1, 1000);
        });
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {}
}
