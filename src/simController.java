public class simController {
    private Lawnmower lawnmower;
    private Lawn lawn;
    private NavigationStrategy navigationStrategy;
    private simulationPanel simulationPanel;// Swing panel to visualize the lawn
    private metricsPanel metricsPanel;
    private controlPanel controlPanel;

    private Thread simulationThread;

    private long startTime;
   // private long currentTime;
    private double virtualTimeElapsed;
    private static  double SPEED_MULTIPLIER = 10.0;
    private  final int uncutcells;// Speed multiplier for accelerated simulation


    public simController(Lawn lawn,Lawnmower Mower) {
        this.lawn = lawn;
        this.lawnmower = Mower;
        this.simulationPanel = new simulationPanel(lawn,lawnmower); // Pass lawn to panel
        this.navigationStrategy = lawnmower.getNavigationStrategy();
        this.metricsPanel = new metricsPanel();
        this.controlPanel = new controlPanel();
        this.uncutcells = lawn.getNumberOfUncutcells();
         // Starting position at (0, 0)
        //this.mowerPanel = new mowerPanel(lawnmower, simulationPanel);

        //lawnmower.setPanel(lawnPanel);

    }
    public void setSpeed(){
       SPEED_MULTIPLIER = controlPanel.getSpeedmultiplier();
    }
    public double getCoverage(int uncut){
        double percentage;
        int cut = lawn.getNumberOfCutcells();
        System.out.println("Cut "+cut+" / uncut "+uncut);
        percentage = ((double)cut/uncut) *100;
        System.out.println(percentage);
        return percentage;
    }
    public void startSimulation() throws InterruptedException {
        simulationThread = new Thread(() -> { // new thread
            System.out.println("Simulation Started");
            metricsPanel.updateLawnmowerName(lawnmower.getName()); // display name of mower
            System.out.println(lawnmower.getName());

            startTime = System.currentTimeMillis();
            virtualTimeElapsed = 0;




            while( (getCoverage(uncutcells) <= 80)){
                long currentTime = System.currentTimeMillis();
                setSpeed();
                double deltaTime = ((currentTime - startTime) / 1000.0) * SPEED_MULTIPLIER;
                startTime = currentTime;

                metricsPanel.updateXandY(lawnmower.getX(),lawnmower.getY());
                if(!controlPanel.getPause()){

                    metricsPanel.updateCoverage(getCoverage(uncutcells));
                    simulateStep(deltaTime);

                }

                simulationPanel.repaint();
                // short delay controlling the simulation speed (optional)
                try {
                    Thread.sleep(1); // Adjust the sleep duration as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(controlPanel.getQuit()){
                    try {
                        stopSimulation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                stopSimulation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //lawnPanel.repaint();
//            if(lawnmower.getNavigationStrategy().isEntireLawnMowed()){
//                try {
//                    System.out.println("Stopping Simulation");
//
//                    stopSimulation();
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            // Pause the simulation for 2 seconds

            // Continue with the simulation logic after the pause
        });
        simulationThread.start();
    }
    public synchronized void stopSimulation() throws InterruptedException {
        if (simulationThread != null ) {

            System.out.println("Time taken to mow "+getCoverage(uncutcells)+ "% of the lawn: " + virtualTimeElapsed + " seconds");

            simulationThread.interrupt();

            System.exit(0);
        }
    }

    public void simulateStep(double deltaTime) {
        // Simulate a single step in the simulation
        virtualTimeElapsed += deltaTime;

        System.out.println("Delta Time: "+deltaTime);
        metricsPanel.updateVirtualTimeElapsed(virtualTimeElapsed);
        navigationStrategy.navigate(lawnmower,lawn,deltaTime);
    }

    public simulationPanel getLawnPanel() {
        return simulationPanel;
    }
    public metricsPanel getMetricsPanel() {
        return metricsPanel;
    }
    public controlPanel getControlPanel() {
        return controlPanel;
    }
}
