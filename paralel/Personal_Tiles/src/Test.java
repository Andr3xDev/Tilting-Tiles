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
        test9();
    }

    public static void test1(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(3, 3, "blue");
        puzzle.deleteTile(0,0);
        puzzle.deleteTile(1,3);
    }
    public static void test2(){
        Puzzle puzzle = new Puzzle(5, 5);
        puzzle.addTile(0, 2, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.actualArrangemment();
    }
    public static void test3(){
        Puzzle puzzle = new Puzzle(5, 5);
        puzzle.addTile(0, 2, "red");
        puzzle.addTile(1, 2, "blue");
        puzzle.addTile(2, 2, "green");
        puzzle.addTile(3, 2, "yellow");
        puzzle.addTile(4, 2, "magenta");
        puzzle.makeInvisible();
    }

    public static void test4(){
        Puzzle puzzle = new Puzzle(5, 5);
        puzzle.addTile(1, 1, "red");
        puzzle.addTile(1, 0, "blue");
        puzzle.addTile(0, 1, "green");
        puzzle.addGlue(1, 1);
        Tiles tile = puzzle.board[1][1];
        System.out.println(tile.getGlued());
        System.out.println(tile.tilesGlued[1].getName());
        puzzle.actualArrangemment();
        puzzle.deleteGlue(1,1);
        System.out.println(tile.tilesGlued[3].getName());
    }
    public static void test5(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(1, 1, "red");
        puzzle.actualArrangemment();
        System.out.println("----------------------");
        int[] to;
        int[] from;
        to = new int[]{1,1};
        from = new int[]{0,2};
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
        puzzle.directionHorizontal(3);
        System.out.println("----------------------");
        puzzle.actualArrangemment();
    }
    public static void test8(){
        Puzzle puzzle = new Puzzle(4, 4);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.addTile(1, 3, "white");
        puzzle.addTile(3, 0, "orange");
        puzzle.addTile(3, 3, "green");
        puzzle.actualArrangemment();
        puzzle.directionVertical(3);
        System.out.println("----------------------");
        puzzle.actualArrangemment();
    }
    public static void test9(){
        char[][] ending = new char[][]{{'r','r','r'},{'0','r','0'},{'0','0','0'}};
        Puzzle puzzle = new Puzzle(ending);
        puzzle.addTile(0, 0, "red");
        puzzle.addTile(2, 2, "blue");
        puzzle.actualArrangemment();
        puzzle.deleteTile(0,0);
        puzzle.actualArrangemment();
        puzzle.isGoal();
        puzzle.relocateTile(new int[]{2,2},new int[]{1,1});
        puzzle.deleteTile(1,1);
        puzzle.misPlacedTiles();

    }
}