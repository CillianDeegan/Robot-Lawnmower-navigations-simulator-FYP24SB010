import java.util.Random;
public class SystematicNavigationStrategy implements NavigationStrategy {
    //Random random = new Random();

    @Override
    public String getName() {
        return "Systematic";
    }

    @Override
    public void navigate(Lawnmower lawnmower, Lawn lawn, double deltaTime) {


        if (!lawnmower.isAtBoundary()) {
            System.out.println("Moving forward");

            int oldX = (int) lawnmower.getX();
            int oldY = (int) lawnmower.getY(); //marked position that mower was on and then move forward


            lawn.markMowed(oldX, oldY);
            lawnmower.moveForward(deltaTime);


        } else if (lawnmower.outOfBounds()) {
            //mower managed to escape
            System.out.println("Warning : out of bounds - reversing");
            lawnmower.moveBackwards(deltaTime);
        } else {
            System.out.println("Boundary detected");
            System.out.println("Turning");
            lawnmower.moveBackwards(deltaTime);
            if (lawnmower.getX() > 5 ) { // when mower is on right side of the lawn
                lawnmower.turn(90);
                lawnmower.moveForward(deltaTime + 1);
                lawnmower.turn(90);
            } else { //when mower is on left side of lawn
                if (lawnmower.getY() >= lawn.getHeight() - 2) { //if obstical is added try move up to position that was missed (not working)
                    lawnmower.setX(21);
                    lawnmower.setY(20);
                } else {
                    lawnmower.turn(270);
                    lawnmower.moveForward(deltaTime + 1);
                    lawnmower.turn(270);
                }
            }

        }
    }
}
