import java.util.Random;
public class RandomNavigationStrategy implements NavigationStrategy{
    Random random = new Random();

    @Override
    public String getName() {
        return "Random";
    }

    @Override
    public void navigate(Lawnmower lawnmower,Lawn lawn, double deltaTime){


        if(!lawnmower.isAtBoundary()){
            System.out.println("Moving forward");

               int oldX = (int)lawnmower.getX(); // mark locations the lawnmower was
               int oldY = (int)lawnmower.getY();


               lawn.markMowed(oldX,oldY);
               lawnmower.moveForward(deltaTime); // mark the lawn where the mower was  and then move forward
            //dont want to mark the area where the mower is, only where it has passed


                //System.out.println(direction.toString());

        }
        else if(lawnmower.outOfBounds()){
            System.out.println("Warning : out of bounds - reversing");
            lawnmower.moveBackwards(deltaTime); //attempt to move backwards  back into lawn if out of bounds
        }
        else{
            System.out.println("Boundary detected");
            System.out.println("Turning");
            lawnmower.moveBackwards(deltaTime);
            double randomNumber = random.nextInt(270) + 90; //turn at a random degrees between 90 and 270
            lawnmower.turn(randomNumber);
        }



    }


}
