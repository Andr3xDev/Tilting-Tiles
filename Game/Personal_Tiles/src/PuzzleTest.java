import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import puzzle.*;

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
        assertThrows(java.lang.NegativeArraySizeException.class, () -> {
            new Puzzle(-4, 4);
        });
    }
    @Test
    public void shouldCreatePuzzle2(){
        char[][] finish = new char[][]{{'r','r','r'},{'.','r','.'},{'.','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test //! This test is not working
    public void shouldCreatePuzzle2E(){
        char[][] finish = new char[][]{{'r',99,'r'},{'.','r','.'},{'.','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test
    public void shouldCreatePuzzle3(){
        char[][] finish = new char[][]{{'r','r','r'},{'.','r','.'},{'.','.','.'}};
        char[][] start = new char[][]{{'r','.','.'},{'.','b','.'},{'w','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test //! This test is not working
    public void shouldCreatePuzzle33(){
        char[][] finish = new char[][]{{'r','r','r'},{'.','r','.'},{'.','.','.'}};
        char[][] start = new char[][]{{'r','.','.'},{'.','b','.'},{'w','.','.'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }

    //* Inserting tiles
    @Test
    public void shouldInsertTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
    }
    @Test
    public void shouldNotInsertTile() throws puzzleExceptions {
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
    public void shouldNotDeleteTile() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addTile(1, 1, "red");
            puzzle.deleteTile(0, 0);
        });
    }

    //* Relocating tiles
    @Test
    public void shouldRelocateTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        Tiles tileInitial = puzzle.board[1][1];
        tileInitial.relocateTile(new int[]{1,1}, new int[]{0,0});
    }
    @Test
    public void shouldNotRelocateTile() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(4, 3);
            puzzle.addTile(1, 1, "green");
            Tiles tileStart = puzzle.board[1][1];
            tileStart.relocateTile(new int[]{1,1}, new int[]{1000,0});
        });
    }

//    //* Add glue
//    public void shouldAddGlue(){
//        Puzzle puzzle = new Puzzle(3, 3);
//        puzzle.addTile(1, 1, "blue");
//        puzzle.addGlue(1, 1);
//        Tiles tile = puzzle.board[1][1];
//        assertTrue(tile.getGlued());
//    }
//    public void shouldNotAddGlue(){
//        Puzzle puzzle = new Puzzle(3, 3);
//        puzzle.addGlue(0, 0);
//        Tiles tile = puzzle.board[1][1];
//        assertFalse(tile.getGlued());
//    }
//
//    //* Delete glue
//    public void shouldDeleteGlue(){
//        Puzzle puzzle = new Puzzle(3, 3);
//        puzzle.addTile(1, 1, "blue");
//        puzzle.addGlue(1, 1);
//        Tiles tile = puzzle.board[1][1];
//        puzzle.deleteGlue(1, 1);
//        assertFalse(tile.getGlued());
//    }
//    public void shouldNotDeleteGlue(){
//        Puzzle puzzle = new Puzzle(3, 3);
//        puzzle.addTile(0, 0, "blue");
//        puzzle.deleteGlue(0, 0);
//        Tiles tile = puzzle.board[0][0];
//        assertFalse(tile.getGlued());
//    }
//
    //* Make hole
    @Test
    public void shouldMakeHole() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.makeHole(1, 1);
            puzzle.addTile(1, 1, "blue");
        });
    }
    @Test
    public void shouldNotMakeHole() throws puzzleExceptions {
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
        Tiles tile = puzzle.board[1][1];
        assertTrue(tile.getVisible());
    }

    //* Make invisible
    @Test
    public void shouldMakeInvisible() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeInvisible();
        Tiles tile = puzzle.board[1][1];
        assertFalse(tile.getVisible());
    }

    //* Misplaced tiles
    @Test
    public void shouldMisplacedTiles() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.deleteTile(1, 0);
        assertEquals(puzzle.getMissingSpace(), 7);
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
        Tiles tile = puzzle.board[0][2];
        assertNotNull(tile);
    }

    //* is gloal
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
        char[][] ending = new char[][]{{'r','O','r'},{'O','O','O'},{'O','O','r'}};
        Puzzle puzzle = new Puzzle(ending);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "red");
        assertFalse(puzzle.isGoal());
    }

//    //* Can move
//    @Test
//    public void tileCanMove(){
//        Puzzle puzzle = new Puzzle(3,3);
//        puzzle.addTile(0,0,"red");
//        puzzle.addTile(0,1,"blue");
//        //assertTrue(puzzle.canNotMove());
//    }
//    @Test
//    public void tileCanNotMove(){
//        Puzzle puzzle = new Puzzle(3,3);
//        puzzle.addTile(0,0,"red");
//        puzzle.addTile(0,1,"blue");
//        puzzle.addTile(1,0,"green");
//        puzzle.addTile(2,0,"white");
//        puzzle.addTile(0,2,"orange");
//        //assertFalse(puzzle.canNotMove());
//    }

    //* Fixed tiles
    @Test
    public void shouldWorkFixedTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addFixed(1, 1, "red");
        puzzle.tilt('U');
    }
    @Test
    public void shouldNotWorkFixedTile() throws puzzleExceptions {
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
    public void shouldNotWorkRoughTile() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addRough(1, 1, "red");
            puzzle.tilt('U');
        });
    }

    //* Freelance tiles
    @Test
    public void shouldWorkFreelanceTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addFreelance(1, 1, "red");
    }
    @Test
    public void shouldNotWorkFreelanceTile() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFreelance(10, 1, "red");
        });
    }

    //* Flying tiles
    @Test
    public void shouldWorkFlyingTile() throws puzzleExceptions {
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addFlying(0, 0, "red");
        puzzle.makeHole(0, 2);
        puzzle.tilt('R');
    }
    @Test
    public void shouldNotWorkFlyingTile() throws puzzleExceptions {
        assertThrows(puzzle.puzzleExceptions.class, () -> {
            Puzzle puzzle = new Puzzle(3, 3);
            puzzle.addFlying(10, 1, "red");
        });
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {}
}
