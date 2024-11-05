package puzzle;

import shapes.Circle;

import java.util.ArrayList;

public class Glues {
    //* Attributes
    private int row;
    private int column;
    private Puzzle board;
    private Circle glue;
    private String color;
    private char type;


    /**
     * Constructor of the Glues class
     * @param row row position of the glue
     * @param column column position of the glue
     * @param board board where the glue is
     * @param glued true if the glue is glued, false otherwise
     */
    public Glues(int row, int column, Puzzle board, boolean glued) {
        this.row = row;
        this.column = column;
        this.color = "yellow";
        this.board = board;
        this.glue = new Circle();
        setGlued(glued);
        makeVisibleX();
    }


    /**
     * Function to set the glue as glued or not glued, and define the type of the glue
     * @param glued true if the glue is glued and type g, false and type p otherwise
     */
    private void setGlued(boolean glued) {
        if (glued) {
            this.type = 'g';
        } else {
            this.type = 'p';
        }
    }


    /**
     * Function to make the glue visible in the board on the creation
     */
    public void makeVisibleX() {
        this.glue.makeVisible();
        this.glue.changeSize(15);
        this.glue.changeColor(this.color);
        this.glue.moveHorizontal(row * 23 + 2);
        this.glue.moveVertical(column * 23 + 2);
    }


    /**
     * Function to make the glue invisible
     */
    public void makeInvisible() {
        this.glue.makeInvisible();
    }


    /**
     * Function to make the glue visible
     */
    public void makeVisible() {
        this.glue.makeVisible();
    }


    /**
     * Function to add a neighbour glue to the current glue
     * @param row row position of the glue
     * @param column column position of the glue
     */
    public void addNeighbour(int row, int column) {
        if (valid(row, column)) {
            int rowSize = this.board.getHeight();
            int columnSize = this.board.getWidth();
            int[][] positions = {
                    {row - 1, column},     // Up
                    {row + 1, column},     // Down
                    {row, column - 1},     // Left
                    {row, column + 1}      // Right
            };

            for (int[] position : positions) {
                int r = position[0];
                int c = position[1];
                if (r >= 0 && r < rowSize && c >= 0 && c < columnSize &&
                        this.board.getTile(r, c) != null && !(board.getTile(r,c) instanceof Freelance)) {
                    if (board.gluesBoard[r][c] != null && board.gluesBoard[r][c].getType() == 'g') {
                        board.gluesBoard[r][c] = new Glues(r, c, board, true);
                    } else{
                        board.gluesBoard[r][c] = new Glues(r, c, board, false);
                    }
                }
            }
        }
    }


    /**
     * Function to eliminate a neighbour glue after a glue is deleted
     * @param row row position of the glue
     * @param column column position of the glue
     */
    public void eliminateNeighbour(int row, int column) {
        if (valid(row, column)) {
            this.board.gluesBoard[row][column] = null;
        }
        reset();
        rePaint();
    }

    //! Pending to implement
    public int maxMoveGlued(Tiles tile, char d) {
        if (this.type == 'g') {
            return maxMoveAllGlued(this.getTile(tile.getPosX(), tile.getPosY()), d);
        } else {
            return 0;
        }
    }


    /**
     * Function to recognize the glued tiles and add the neighbours
     */
    public void recognizeGlue() {
        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                if (this.board.gluesBoard[i][j] != null && this.board.gluesBoard[i][j].getType() == 'g') {
                    this.board.gluesBoard[i][j].addNeighbour(i,j);
                }
            }

        }
    }


    /**
     * Eliminates all the glues in the board of type p
     */
    private void reset(){
        System.out.println("Reset");
        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                if (this.board.gluesBoard[i][j] != null && this.board.gluesBoard[i][j].getType() != 'g') {
                    this.board.gluesBoard[i][j] = null;
                }
            }
        }
    }


    /**
     * Function to repaint the board after a glue is deleted
     */
    private void rePaint(){
        for (int i = 0; i < this.board.getHeight(); i++) {
            for (int j = 0; j < this.board.getWidth(); j++) {
                if (this.board.gluesBoard[i][j] != null && this.board.gluesBoard[i][j].getType() == 'g') {
                    this.board.gluesBoard[i][j].addNeighbour(i,j);
                }
            }
        }
    }


    /**
     * Function to get the maximum movement of the glued tiles as a group
     * @param tile tile to move
     * @param direction direction to move
     * @return the maximum movement of the glued tiles
     */
    public int maxMoveAllGlued(Tiles tile, char direction) {
        int min = 999999999;
        for (int i = 0; i < 4; i++) {
            Tiles tileO = getTile(tile.getPosX(), tile.getPosY());
            ArrayList<Tiles> tiles = new ArrayList<>();
            if (tileO != null) {
                tiles.add(tileO);
            }
            for (Tiles tl : tiles) {
                min = switch (direction) {
                    case 'R' -> Math.min(tl.maxMoveRight(), min);
                    case 'L' -> Math.min(tl.maxMoveLeft(), min);
                    case 'U' -> Math.min(tl.maxMoveUp(), min);
                    case 'D' -> Math.min(tl.maxMoveDown(), min);
                    default -> min;
                };
            }
        }
        return min;
    }


    /**
     * Validate if the position is valid
     * @param row objective row
     * @param column objective column
     * @return true if the position is valid, false otherwise
     */
    private boolean valid(int row, int column) {
        return row > 0 &&
                row < this.board.getHeight() &&
                column > 0 &&
                column < this.board.getWidth();
    }


    /**
     * Function to get the type of the glue
     * @return g if the glue is glued, p if the glue is not glued
     */
    public char getType() {
        return this.type;
    }


    /**
     * Function to get the tile in the board
     * @param row row position of the tile
     * @param column column position of the tile
     * @return The tile object
     */
    public Tiles getTile(int row, int column) {
        return this.board.getTile(row, column);
    }
}