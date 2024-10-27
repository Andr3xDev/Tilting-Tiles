package puzzle;

import shapes.Circle;
import java.awt.*;

public class Glues {
    //* Attributes
    private int row;
    private int column;
    private Puzzle board;
    private Circle glue;
    private String color;

    public Glues(int row,int column,Puzzle board){
        this.row = row;
        this.column = column;
        this.color = "yellow";
        this.board = board;
        makeVisibleX();
    }
    public void makeVisibleX() {
        this.glue = new Circle();
        this.glue.makeVisible();
        this.glue.changeSize(15);
        this.glue.changeColor(this.color);
        this.glue.moveHorizontal(row * 23 + 2);
        this.glue.moveVertical(column * 23 + 2);
    }
}