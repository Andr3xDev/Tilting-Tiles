import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PuzzleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PuzzleTest
{
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

    @Test
    public void shouldCreatePuzzle1(){
        Puzzle puzzle = new Puzzle(4, 4);
        assertEquals(puzzle.getHeight(),4);
    }

    @Test
    public void shouldCreatePuzzle1E(){
        Puzzle puzzle = new Puzzle(4,-1);
        assertEquals(puzzle.getHeight(),4);
    }
    @Test
    public void shouldCreatePuzzle1F(){
        Puzzle puzzle = new Puzzle(3, 3);
        assertEquals(puzzle.getHeight(),4);
    }

    @Test
    public void shouldCreatePuzzle2(){
        char[][] finish = new char[][]{{'r','r','r'},{'0','r','0'},{'0','0','0'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }
    @Test
    public void shouldCreatePuzzle2E(){
        char[][] finish = new char[][]{{'r',99,'r'},{'0','r','0'},{'0','0','0'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }

    @Test
    public void shouldCreatePuzzle3(){
        char[][] finish = new char[][]{{'r','r','r'},{'0','r','0'},{'0','0','0'}};
        char[][] start = new char[][]{{'r','0','0'},{'0','b','0'},{'w','0','0'}};
        Puzzle puzzle = new Puzzle(finish);
        assertEquals(puzzle.getHeight(),3);
    }


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
        puzzle.addTile(-1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        Tiles tile = puzzle.board[1][1];
        assertNotNull(tile);
    }
    public void shouldNotInsertTile1(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(-1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        Tiles tile = puzzle.board[1][1];
        assertNotNull(tile);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {}
}
