import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class Main {
    private static JCheckBox selectedCheckbox = null;
    private static Lawnmower lawnmower;
    private static  Lawn lawn = new Lawn();
    public static void main(String[] args) {

       // opening frame for mower selection
        JFrame mowerSelectionFrame = new JFrame("Select Mowers");
        mowerSelectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mowerSelectionFrame.setSize(400, 200);
        mowerSelectionFrame.setLayout(new BorderLayout());

        String[] mowerNames = {"Random", "Systematic"}; // Example mower names
        List<JCheckBox> mowerCheckboxes = new ArrayList<>();
        JPanel checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new GridLayout(mowerNames.length, 1));
        for (String name : mowerNames) {
            JCheckBox checkBox = new JCheckBox(name);
            // listen to which check box is currently selected
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox selected = (JCheckBox) e.getSource();
                    if (selectedCheckbox != null && selectedCheckbox != selected) {
                        selectedCheckbox.setSelected(false);
                    }
                    selectedCheckbox = selected;
                }
            });
            mowerCheckboxes.add(checkBox);
            checkboxesPanel.add(checkBox);
        }
        mowerSelectionFrame.add(checkboxesPanel, BorderLayout.CENTER);


         // button to start simulation
        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                simController controller;

                if(selectedCheckbox.getText() == "Husqvarna Mower"){
                    lawnmower = new RandomLawnmower(lawn);
                     controller = new simController(lawn,lawnmower);
                }
                else{
                    lawnmower = new SytematicLawnmower(lawn);
                    controller = new simController(lawn,lawnmower);
                }
                // Create an instance of the SimulationController
               // simController controller = new simController(); // Example lawn size (20x20)



                // Create a JFrame to hold the simulationPanel
                JFrame simulationFrame = new JFrame("Robotic Lawnmower Simulation");
                simulationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                simulationFrame.setSize(1000, 600); // Set the size according to your preference


                // Add the simulation,metrics from the SimulationController to the frame
                simulationFrame.add(controller.getLawnPanel(), BorderLayout.CENTER);
                simulationFrame.add(controller.getMetricsPanel(),BorderLayout.EAST);
                simulationFrame.add(controller.getControlPanel(),BorderLayout.SOUTH);

                // Display the frame
                simulationFrame.setVisible(true);

                // Start the simulation
                try {
                    controller.startSimulation();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }


                // Close the mower selection frame
                mowerSelectionFrame.dispose();
            }
        });
        mowerSelectionFrame.add(startButton, BorderLayout.SOUTH);

        // Display the mower selection frame
        mowerSelectionFrame.setVisible(true);
    }

}
