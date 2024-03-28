public class RandomLawnmower extends Lawnmower{

    public RandomLawnmower(Lawn lawn){
        super(lawn);
        setName("Random Automower");

        //set lawnmower to random navigation
        setNavigationStrategy(new RandomNavigationStrategy());
    }


}
