import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class metricsPanel extends JPanel {
    private JLabel coverageLabel;
    private JLabel virtualTimeElapsedLabel;
    private JLabel lawnmowerNameLabel;
    private JLabel lawnmowersXAndY;
    private JLabel lawnmowersDirection;

    public metricsPanel() {
        initializeComponents();
        setupLayout();
        addBorder();
    }

    private void initializeComponents() {
        lawnmowerNameLabel = new JLabel("Lawnmower Name: ");
        coverageLabel = new JLabel("Coverage: ");
        virtualTimeElapsedLabel = new JLabel("Virtual Time Elapsed: ");
        lawnmowersXAndY = new JLabel("Mowers X: \nMowers Y: ");
        lawnmowersDirection= new JLabel("Direction of movement: ");
    }

    private void setupLayout() {
        setLayout(new GridLayout(5,1));
        setPreferredSize(new Dimension(500,200));
        add(lawnmowerNameLabel);
        add(coverageLabel);
        add(virtualTimeElapsedLabel);
        add(lawnmowersXAndY);
        //add(lawnmowersDirection);

    }
    private void addBorder() {
        // Create a titled border with the specified title and position
        Border border = BorderFactory.createTitledBorder("Metrics");
        // Set the border for the panel
        setBorder(border);
    }
    public void updateCoverage(double coverage) {
        coverageLabel.setText("Coverage: " + coverage +"%");
    }
    public void updateVirtualTimeElapsed(double time) {
        virtualTimeElapsedLabel.setText("Virtual Time Elapsed(seconds): " + time );
    }
    public void updateLawnmowerName(String name) {
        lawnmowerNameLabel.setText("Lawnmower Name: " + name);
    }
    //public void updateDirection(String direction){lawnmowersDirection.setText("Direction of movement: "+direction);}
    public void updateXandY(double x,double y){
        lawnmowersXAndY.setText("Mowers X Pos: "+(int)x+" Mowers Y Pos: "+(int)y);
    }
}
