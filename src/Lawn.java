public class Lawn {

    public static final int UNCUT_CELL = 0;
    public static final int CUT_CELL = 1;
    public static final int OBSTACLE_CELL = 2;
    public static final int GUIDE_WIRE_CELL = 3;
    //public static final int Elevation = 4;

    private int height;
    private int width;
   // private int gridScaler; //original idea was to increase resolution by multiplying by scale, caused complications
    private int[][] grid; //lawn
    private int gridWidth;
    private int gridHeight;
    private int numberOfUncutcells;

    public Lawn() {
        this.width = 50;
        this.height = 50;
        //this.gridScaler =10;
        this.gridWidth = width; //*gridScaler; //2000
        this.gridHeight = height; //*gridScaler;
        this.grid = new int[gridWidth][gridHeight]; // Initialize grid with default values (false) 2000,2000

        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                grid[i][j] = UNCUT_CELL; //0
            }
        }



//uncomment to add obstacle
//        addWire(9,19,12,1);//top of obstacle
//       addObstacle(10,20,10,10);//ob 1 big
//        addWire(9,20,1,11);//left side obstacle
//        addWire(10,30,11,1);//bottom of obstacle
//        addWire(20,20,1,10); //right side
//

        // guide-wire on perimeter of lawn
        addWire(0,0,2,height);
        addWire(48,0,2,height);
        addWire(0,48,width,2);
        addWire(0,0,width,2);
//        System.out.println(grid[1][5]);
//        System.out.println(grid[width-1][5]);
//        System.out.println(grid[5][height-1]);
//        System.out.println(grid[0][1]);
//        System.out.println(grid[28][1]);
//        System.out.println(grid[40][1]);
//        System.out.println(grid[11][1]); //used for debugging

        //retireves number of uncut cells, essentially the actual area of the lawn  =2116
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if(grid[i][j] == UNCUT_CELL)
                numberOfUncutcells++;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getGrid(){
        return grid;
    }
    public int getGridWidth() {
        return gridWidth;
    }

    public int getNumberOfUncutcells() {
        return numberOfUncutcells;
    }

    public int getNumberOfCutcells() {
    int count=0;
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                if(grid[i][j] == CUT_CELL)
                    count++;
            }
        }
        return count;
    }

    public int getGridHeight() {
        return gridHeight;
    }


    public void markMowed(int x, int y) {


        if(grid[x][y] == GUIDE_WIRE_CELL)
        {
            return;
        }
        else{
            grid[x][y] = CUT_CELL;
        }

    }


    public void addObstacle(int x, int y, int width, int height) {
        int arrayX = x;
        int arrayY = y;
        int arrayWidth = width;
        int arrayHeight = height;
        // Mark the corresponding cells in the grid array as obstacles
        for (int i = arrayX; i < arrayX + arrayWidth; i++) {
            for (int j = arrayY; j < arrayY + arrayHeight; j++) {
                grid[i][j] = OBSTACLE_CELL; //2
            }
        }
    }
    public void addWire(int x, int y, int width, int height) {
        int arrayX = x;
        int arrayY = y;
        int arrayWidth = width;
        int arrayHeight = height;
        // Mark the corresponding cells in the grid array as guide wire
        for (int i = arrayX; i < arrayX + arrayWidth; i++) {
            for (int j = arrayY; j < arrayY + arrayHeight; j++) {

                grid[i][j] = GUIDE_WIRE_CELL; //2
            }
        }
    }
//    public void addElevation(int x, int y, int width, int height) {  // was planning on using elevation , didn't get to finish
//        int arrayX = x; //* gridScaler;
//        int arrayY = y; //* gridScaler;
//        int arrayWidth = width; //* gridScaler;
//        int arrayHeight = height; //* gridScaler;
//        // Mark the corresponding cells in the grid array as obstacles
//        for (int i = arrayX; i < arrayX + arrayWidth; i++) {
//            for (int j = arrayY; j < arrayY + arrayHeight; j++) {
//                grid[i][j] = Elevation; //2
//            }
//        }
//    }


}
