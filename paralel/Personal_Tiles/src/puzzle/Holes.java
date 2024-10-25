//package puzzle;
//
//import shapes.Triangle;
//
//public class Holes extends Tiles {
//    private String color;
//    private Triangle holeTile;
//
//    public Holes(int posX, int posY,String color,Puzzle board) {
//        super(posX,posY,color,board);
//        this.color = "Black";
//        this.setName("X");
//        this.holeTile = new Triangle();
//        makeVisibleX();
//    }
//    public void makeVisibleX() {
//        holeTile.makeVisible();
//        holeTile.changeColor(this.color);
//        holeTile.changeSize(20,20);
//        holeTile.moveHorizontal(posX * 23);
//        holeTile.moveVertical(posY * 23);
//    }
//}
