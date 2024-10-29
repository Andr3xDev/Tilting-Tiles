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

    public Glues(int row, int column, Puzzle board, boolean glued) {
        this.row = row;
        this.column = column;
        this.color = "yellow";
        this.board = board;
        this.glue = new Circle();
        setGlued(glued);
        makeVisibleX();
    }

    private void setGlued(boolean glued) {
        if (glued) {
            this.type = 'g';
        } else {
            this.type = 'p';
        }
    }

    public void makeVisibleX() {
        this.glue.makeVisible();
        this.glue.changeSize(15);
        this.glue.changeColor(this.color);
        this.glue.moveHorizontal(row * 23 + 2);
        this.glue.moveVertical(column * 23 + 2);
    }

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
                        this.board.getTile(r, c) != null) {
                    board.gluesBoard[r][c] = new Glues(r, c, board, false);
                }
            }
        }
    }

    public int maxMoveGlued(Tiles tile, char d) {
        if (this.type == 'g') {
            return maxMoveAllGLued(this.getTile(tile.getPosX(), tile.getPosY()), d);
        } else {
            return 0;
        }
    }

    public int maxMoveAllGLued(Tiles tile, char direction) {
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


    private boolean valid(int row, int column) {
        return row > 0 &&
                row < this.board.getHeight() &&
                column > 0 &&
                column < this.board.getWidth();
    }


    public char getType() {
        return this.type;
    }


    public Tiles getTile(int row, int column) {
        {
            return this.board.getTile(row, column);
        }
    }
}