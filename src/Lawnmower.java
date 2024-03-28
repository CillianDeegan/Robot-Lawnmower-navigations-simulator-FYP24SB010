public class Lawnmower {
    private String name;
    private double x; // Current x-coordinate
    private double y; // Current y-coordinate
    private double angle; // Angle in radians, for rotation
//    private int chargingTime; // charging time in minutes
//    private int chargeDuration; //how long mower last on a charge

    private double speed;
    private int orientation; // Current orientation (0 - North, 1 - East, 2 - South, 3 - West)

    private Lawn lawn;
    private simulationPanel lawnpanel;
    private NavigationStrategy navigationStrategy;


    public Lawnmower(Lawn lawn) {
        this.name = "N/A";
        this.lawn = lawn;
        this.x = 40;
        this.y = 40;
        this.navigationStrategy = null;
        //this.mowerpanel = mowerPanel;
        this.speed = 1.0;
        //this.orientation = 1; // Assume facing North initially

        this.angle = 0; // Initial angle (facing north)
        //lawn.markMowed(x,y);
    }

    public String getName(){return name;}

    public void setName(String name){
        this.name = name;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public  void setX(double x) {
        this.x =x;
    }

    public  void setY(double y) {
        this.y =y;
    }
    public void setAngle(double angle){this.angle = angle;}

    public double getAngle() {
        return angle;
    }

    public void setSpeed(double speed){this.speed = speed;}

//    public void setChargingTime(int chargeTime){
//        this.chargingTime = chargeTime;
//    }
//    public int getChargingTime(){
//        return this.chargingTime ;
//    }
//    public void setChargeDuration(int chargeDuration){
//        this.chargeDuration = chargeDuration;
//    }
//    public int getChargeDuration(){
//        return this.chargeDuration ;
//    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT
    }

    public MovementDirection getMovementDirection(double oldX, double oldY, double newX, double newY) {
        if (newX > oldX && newY > oldY) {
            return MovementDirection.DOWN_RIGHT;
        } else if (newX > oldX && newY < oldY) {
            return MovementDirection.UP_RIGHT;
        } else if (newX < oldX && newY > oldY) {
            return MovementDirection.DOWN_LEFT;
        } else if (newX < oldX && newY < oldY) {
            return MovementDirection.UP_LEFT;
        } else if (newX > oldX) {
            return MovementDirection.RIGHT;
        } else if (newX < oldX) {
            return MovementDirection.LEFT;
        } else if (newY > oldY) {
            return MovementDirection.DOWN;
        } else if (newY < oldY) {
            return MovementDirection.UP;
        } else {
            // Handle cases where there's no clear direction, e.g., no movement or staying in place
            return null;
        }
    }


    public void moveForward(double deltaTime) {

        // Calculate the distance to move based on velocity and time elapsed
        double distance = speed * deltaTime;
        double newX = distance * (Math.cos(angle));
        double newY =  distance * (Math.sin(angle));

        x += newX;
        y += newY;


    }
    public void moveBackwards(double deltaTime) {
        // Calculate the distance to move based on velocity and time elapsed
        double distance = speed * deltaTime;
        double newX = distance * (Math.cos(angle));
        double newY = distance * (Math.sin(angle));

            x -= newX;
            y -= newY;


    }


        public void turn(double degrees) {
        // Turn the lawnmower
        angle += Math.toRadians(degrees);
    }
//
//    public void turnRight(double degrees) {
//        // Turn the lawnmower 90 degrees to the right
//        //orientation = (orientation + 1) % 4;
//        angle += Math.toRadians(degrees);
//    }
    public boolean isAtBoundary() {

        int row = (int)Math.round(x);
        int col = (int)Math.round(y);
        // Check if the lawnmower is at the boundary ie hit a guid wire

        //System.out.println(row+" "+col);


        try {
            return lawn.getGrid()[row][col] == 3;
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Lawn mower stuck - resetting to original position.");
            this.x = 40;
            this.y = 40;
            row = (int)x;
            col = (int)y;

        }
        return lawn.getGrid()[row][col] == 3;


    }
    public boolean outOfBounds(){
        if(x < 0 || y < 0 || x  >= lawn.getGridWidth() || y >= lawn.getGridHeight()){
            return true;
        }
        return false;
    }
    public boolean hasMowedEntireLawn(){
        for (int i = 0; i < lawn.getGridWidth(); i++) {
            for (int j = 0; j < lawn.getGridHeight(); j++) {
                //if cell is obstacle or wire , ignore
                if(lawn.getGrid()[i][j] == 3 || lawn.getGrid()[i][j] == 4){
                    continue;
                }
                // If any cell contains uncut grass, return false
                if (lawn.getGrid()[i][j] == 0) {
                    return false;
                }
                try{
                    if(lawn.getGrid()[(int)x][(int)y] == 3){
                        continue;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    return false;
                }

            }
        }
        return true;
    }

    public void moveTo(int newX, int newY) {
        // Move the lawnmower to a specific position
//        if (!lawn.isObstacle(newX, newY)) {
//            x = newX;
//            y = newY;
//        }
    }
    public void setNavigationStrategy(NavigationStrategy navigationStrategy) {
        this.navigationStrategy = navigationStrategy;
    }
    public  NavigationStrategy getNavigationStrategy() {
        return navigationStrategy;
    }
    public  String getNavigationStrategyName() {
        return navigationStrategy.getName();
    }
//    public void startNavigation() {
//        if (navigationStrategy != null) {
//            navigationStrategy.navigate();
//        }
//    }

}
