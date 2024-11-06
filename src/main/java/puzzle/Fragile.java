package puzzle;
import shapes.*;


public class Fragile extends Glues {
    //* Attributes
    private final int row;
    private final int column;
    private final Puzzle board;
    private final String color;
    private char type;
    private Circle glue;

    /**
     * Constructor of the Fragile class
     * @param row row position of the glue
     * @param column column position of the glue
     * @param board board where the glue is
     * @param glued true if the glue is glued, false otherwise
     */
    public Fragile(int row, int column, Puzzle board, boolean glued) {
        super(row, column, board, glued);
        this.row = row;
        this.column = column;
        this.color = "White";
        this.board = board;
        this.glue = new Circle();
        setGlued(glued);
        makeVisibleX();
        int counter = 1;
    }


    /**
     * Function to set the glue as glued or not glued, and define the type of the glue
     * @param glued true if the glue is glued and type g, false and type p otherwise
     */
    @Override
    protected void setGlued(boolean glued) {
        if (glued) {
            this.type = 'f';
        } else {
            this.type = 'p';
        }
    }


    /**
     * Function to make the glue visible in the board on the creation
     */
    public void makeVisibleX() {
        glue.makeVisible();
        this.glue.changeSize(15);
        this.glue.changeColor(this.color);
        this.glue.moveHorizontal(row * 23 + 2);
        this.glue.moveVertical(column * 23 + 2);
    }


}