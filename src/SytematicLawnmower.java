public class SytematicLawnmower extends Lawnmower {
    public SytematicLawnmower(Lawn lawn){
        super(lawn);
        setName("Systematic Automower");
        //set mower to systematic navigation
        setNavigationStrategy(new SystematicNavigationStrategy());
        //set mower to top left of grid
        setX(2);
        setY(2);
    }
}
