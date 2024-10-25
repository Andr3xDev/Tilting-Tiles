//package puzzle;
//
//import shapes.Rectangle;
//
//public class Fixed extends Tiles{
//    private int posX;
//    private int posY;
//    private String name;
//    private String color;
//    private Rectangle tile;
//    private boolean glued;
//    public Tiles[] tilesGlued;
//    private boolean visible;
//
//    public Fixeds(int posX, int posY, String color) {
//        super(posX, posY, color);
//        makeVisibleCreate();
//    }
//    public void makeVisibleCreate() {
//        tile.changeColor(this.color);
//        tile.changeSize(20, 20);
//        tile.moveHorizontal(posX * 23);
//        tile.moveVertical(posY * 23);
//        tile.makeVisible();
//    }
//    public void makeVisible() {
//        tile.makeVisible();
//        this.visible = true;
//    }
//    public void makeInvisible() {
//        tile.makeInvisible();
//        this.visible = false;
//    }
//}