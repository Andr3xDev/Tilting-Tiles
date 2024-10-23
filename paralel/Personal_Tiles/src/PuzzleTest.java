import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 Test class for the Puzzle class methods, these check the correct functioning of the program internally.
 Cases are verified that should be fulfilled and others that should not. *
 * @author Andres Felipe Chavarro Plazas
 * @author David Santiago Espinosa Rojas
 * @version 1.0
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
    public void shouldInsertTile(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        Tiles tile = puzzle.board[1][1];
        assertNotNull(tile);
    }
    @Test
    public void shouldNotInsertTile(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        if (puzzle.board[1][1] != null) {
            puzzle.addTile(1, 1, "blue");
        } else {
            fail("There is a hole or a tile in that position");
        }
    }

    //* Deleting tiles
    @Test
    public void shouldDeleteTile(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.deleteTile(1, 1);
        Tiles tile = puzzle.board[1][1];
        assertNull(tile);
    }
    public void shouldNotDeleteTile(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.deleteTile(0, 0);
        Tiles tile = puzzle.board[1][1];
        assertNotNull(tile);
    }

    //* Relocating tiles
    @Test
    public void shouldRelocateTile(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.relocateTile(new int[]{1,1}, new int[]{0,0});
        Tiles tileInicial = puzzle.board[1][1];
        Tiles tileFinal = puzzle.board[0][0];
        assertNull(tileInicial);
        assertNotNull(tileFinal);
    }
    public void shouldNotRelocateTile(){
        Puzzle puzzle = new Puzzle(4, 3);
        puzzle.addTile(1, 1, "green");
        puzzle.addTile(1, 2, "red");
        puzzle.relocateTile(new int[]{1,1}, new int[]{0,0});
        Tiles tileStart = puzzle.board[1][1];
        Tiles tileFinal = puzzle.board[0][0];
        assertNull(tileStart);
        assertNotNull(tileFinal);
    }

    //* Add glue
    public void shouldAddGlue(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.addGlue(1, 1);
        Tiles tile = puzzle.board[1][1];
        assertTrue(tile.getGlued());
    }
    public void shouldNotAddGlue(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addGlue(0, 0);
        Tiles tile = puzzle.board[1][1];
        assertFalse(tile.getGlued());
    }

    //* Delete glue
    public void shouldDeleteGlue(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.addGlue(1, 1);
        Tiles tile = puzzle.board[1][1];
        puzzle.deleteGlue(1, 1);
        assertFalse(tile.getGlued());
    }
    public void shouldNotDeleteGlue(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(0, 0, "blue");
        puzzle.deleteGlue(0, 0);
        Tiles tile = puzzle.board[0][0];
        assertFalse(tile.getGlued());
    }

    //* Make hole
    @Test
    public void shouldMakeHole(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.makeHole(1, 1);
        puzzle.addTile(1, 1, "blue");
        Tiles tile = puzzle.board[1][1];
        assertTrue(tile.getHole());
    }
    @Test
    public void shouldNotMakeHole(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeHole(1, 1);
        Tiles tile = puzzle.board[1][1];
        assertNotNull(tile);
    }

    //* Make visible
    @Test
    public void shouldMakeVisible(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeInvisible();
        puzzle.makeVisible();
        Tiles tile = puzzle.board[1][1];
        assertTrue(tile.getVisible());
    }

    //* Make invisible
    @Test
    public void shouldMakeInvisible(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "blue");
        puzzle.makeInvisible();
        Tiles tile = puzzle.board[1][1];
        assertFalse(tile.getVisible());
    }

    //* Actual arrangement
    @Test //? Do we need this test?
    public void shouldActualArrangement(){

    }

    //* Misplaced tiles
    @Test
    public void shouldMisplacedTiles(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        assertEquals(puzzle.getMissingSpace(), 7);
    }
    @Test
    public void shouldNotMisplacedTiles(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.addTile(0, 0, "blue");
        assertNotEquals(puzzle.getMissingSpace(), 2);
    }

    //* Tilt puzzle
    @Test
    public void shouldTiltPuzzle(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.tilt('R');
        Tiles tile = puzzle.board[0][2];
        assertNotNull(tile);
    }

    //* is gloal
    @Test
    public void shouldIsGoal(){
        char[][] ending = new char[][]{{'r','O','O'},{'O','O','O'},{'O','O','r'}};
        Puzzle puzzle = new Puzzle(ending);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "red");
        assertTrue(puzzle.isGoal());
    }
    @Test
    public void shouldIsNotGoal(){
        char[][] ending = new char[][]{{'r','O','r'},{'O','O','O'},{'O','O','r'}};
        Puzzle puzzle = new Puzzle(ending);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "red");
        assertFalse(puzzle.isGoal());
    }

    //* Can move
    @Test
    public void tileCanMove(){
        Puzzle puzzle = new Puzzle(3,3);
        puzzle.addTile(0,0,"red");
        puzzle.addTile(0,1,"blue");
        //assertTrue(puzzle.canNotMove());
    }
    @Test
    public void tileCanNotMove(){
        Puzzle puzzle = new Puzzle(3,3);
        puzzle.addTile(0,0,"red");
        puzzle.addTile(0,1,"blue");
        puzzle.addTile(1,0,"green");
        puzzle.addTile(2,0,"white");
        puzzle.addTile(0,2,"orange");
        //assertFalse(puzzle.canNotMove());
    }

    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {}
}
