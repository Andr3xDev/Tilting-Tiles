import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        //test6();
        //test7();
        //test8();
        //test9();
        //test10();
        //test11();
        //test12();
        //test13();
        test14();
        //test15();
        //test16();
    }

    public static void test1(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(3, 3, "blue");

    }
    public static void test2(){
        Puzzle puzzle = new Puzzle(5, 5);
        puzzle.addTile(0, 2, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.actualArrangemment();
        puzzle.deleteTile(0,2);
        puzzle.deleteTile(1,2);
        puzzle.missPlacedTiles();
    }
    public static void test3(){
        Puzzle puzzle = new Puzzle(3, 3);
        puzzle.addTile(0, 2, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.addTile(2, 2, "green");
        puzzle.makeInvisible();
        puzzle.makeVisible();
        puzzle.actualArrangemment();
        puzzle.missPlacedTiles();
    }

    public static void test4(){
        Puzzle puzzle = new Puzzle(5, 3);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.addTile(0, 1, "green");
        puzzle.addGlue(1, 1);
        Tiles tile = puzzle.board[0][1];
        System.out.println(tile.getGlued());
        System.out.println(Arrays.toString(tile.getTilesGlued()));
        puzzle.deleteGlue(1, 1);
        System.out.println(tile.getGlued());
    }
    public static void test5(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(1, 1, "red");
        puzzle.actualArrangemment();
        System.out.println("----------------------");
        int[] to;
        int[] from;
        to = new int[]{1,0};
        from = new int[]{1,1};
        puzzle.relocateTile(to,from);
        puzzle.relocateTile(from,to);
        puzzle.actualArrangemment();
    }
    public static void test6(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.actualArrangemment();
        puzzle.makeHole(0,0);
        puzzle.actualArrangemment();
        puzzle.addTile(0,0,"green");
    }
    public static void test7(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.addTile(1, 3, "white");
        puzzle.addTile(3, 0, "orange");
        puzzle.addTile(3, 3, "green");
        puzzle.actualArrangemment();
        //puzzle.makeHole(2,3);
        puzzle.tilt('D');
        System.out.println("----------------------");
        puzzle.actualArrangemment();
    }
    public static void test8(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.addTile(1, 3, "white");
        puzzle.addTile(3, 3, "green");
        puzzle.actualArrangemment();
        //puzzle.makeHole(2,3);
        puzzle.tilt('D');
        System.out.println("----------------------");
        puzzle.actualArrangemment();
    }
    public static void test9(){
        char[][] ending = new char[][]{{'r','r'},{'0','r'},{'0','0'}};
        char[][] starting = new char[][]{{'r','0'},{'0','0',},{'0','r'}};
        Puzzle puzzle = new Puzzle(starting, ending);
        puzzle.actualArrangemment();
        System.out.println("----------------------");
        System.out.println(puzzle.getHeight());
        System.out.println("----------------------");
        System.out.println(Arrays.deepToString(puzzle.getEndingBoard()));
        puzzle.missPlacedTiles();
        //puzzle.tilt('U');
        puzzle.actualArrangemment();
    }
    public static void test10(){
        Puzzle puzzle = new Puzzle(3,3);
        puzzle.addTile(0,2,"red");
        puzzle.addTile(0,1,"blue");
        puzzle.addTile(1,1,"green");
        puzzle.addGlue(0,1);
        Tiles tile = puzzle.board[0][1];
        System.out.println(tile.getGlued());
        //System.out.println(Arrays.toString(tile.getTilesGlued()));
        puzzle.deleteGlue(0,1);
        System.out.println(tile.getGlued());
        //System.out.println(Arrays.toString(tile.getTilesGlued()));
        //puzzle.tilt('D');
    }
    public static void test11(){
        Puzzle puzzle = new Puzzle(4,4);
        puzzle.addTile(0,0,"red");
        puzzle.addTile(1,0,"blue");
        puzzle.addTile(2,0,"purple");
        puzzle.addTile(3,0,"green");
        puzzle.addTile(2,2,"green");
        puzzle.addTile(1,2,"green");
        puzzle.addTile(3,3,"blue");
        puzzle.addTile(2,3,"blue");
        //puzzle.addGlue(3,0);
        Tiles tile = puzzle.board[3][3];
        puzzle.makeHole(3,1);
        System.out.println(puzzle.maxMoveUp(tile));
        System.out.println(tile.getGlued());
        System.out.println(Arrays.toString(tile.getTilesGlued()));
        //puzzle.tilt('D');
    }
    public static void test12(){
        Puzzle puzzle = new Puzzle(4,3);
        puzzle.addTile(0,2,"red");
        puzzle.addTile(2,2,"blue");
        puzzle.addTile(0,1,"red");
        puzzle.addTile(2,0,"orange");
        puzzle.addTile(2,3,"green");
        puzzle.makeHole(0,0);
        puzzle.actualArrangemment();
        System.out.println("----------------------");
        puzzle.tilt('R');
        puzzle.actualArrangemment();
        puzzle.tilt('L');
        puzzle.tilt('R');
        puzzle.tilt('L');
        puzzle.actualArrangemment();
    }
    public static void test13() {
        Puzzle puzzle = new Puzzle(6, 3);
        puzzle.addTile(1, 2, "red");
        puzzle.addTile(1, 1, "blue");
        puzzle.addTile(1, 5, "blue");
        puzzle.addTile(1, 0, "purple");
        puzzle.addTile(0, 1, "green");
        puzzle.addTile(2, 1, "white");
        puzzle.addTile(2, 4, "red");
        puzzle.addTile(0, 0, "pink");
        puzzle.addGlue(1, 1);
        Tiles tile = puzzle.board[1][2];
        System.out.println(puzzle.maxMoveRight(tile));
        //System.out.println(tile.getGlued());
        System.out.println(Arrays.toString(tile.getTilesGlued()));
        System.out.println(puzzle.maxMoveGlued(tile, 'R'));
        System.out.println(Arrays.toString(puzzle.getNeighbors(puzzle.board[1][1])));
        //puzzle.tilt('R');
    }
    public static void test14(){
        PuzzleContest puzzle = new PuzzleContest();
        boolean a = puzzle.solve(new char[][]{{'0','r','0','0'},{'r','g','y','b'},{'0','b','0','0'},{'0','y','r','0'}},
                new char[][]{{'y','r','b','r'},{'0','0','y','r'},{'0','0','0','g'},{'0','0','0','b'}});
        //boolean b = puzzle.solve(new char[][]{{'0','0','0','0','b','0','0'}},
        //        new char[][]{{'0','0','b','0','0','0','0'}});
        System.out.println(a);
    }
    public static void test15(){
        PuzzleContest puzzle = new PuzzleContest();
        boolean a = puzzle.solve(new char[][]{{'r','r','0','0'},{'r','q','y','b'},{'0','0','0','0'},{'0','0','0','0'}},
                new char[][]{{'0','0','0','0'},{'0','0','0','0'},{'0','0','r','r'},{'r','q','y','b'}});
        System.out.println(a);
    }
    public static void test16(){
        PuzzleContest puzzle = new PuzzleContest();
        puzzle.simulate(new char[][]{{'y','r','0'},{'0','0','b'},{'r','y','0'},{'b','0','0'}},
                new char[][]{{'0','0','0'},{'0','0','b'},{'0','r','y'},{'b','y','b'}});
    }
}