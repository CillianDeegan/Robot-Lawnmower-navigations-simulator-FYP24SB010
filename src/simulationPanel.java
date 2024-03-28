import javax.swing.*;
import java.awt.*;

public class simulationPanel extends JPanel {
    private Lawn lawn;
    private Lawnmower lawnmower;
    private int[][] grid;
    private  final int cellSize = 10; // cell size, used for grid also
    private int zoom =8; //this variable enlarges the grid for easier visualization

    public simulationPanel(Lawn lawn,Lawnmower lawnmower) {
        this.lawn = lawn;
        this.lawnmower = lawnmower;
        grid = lawn.getGrid();
        setPreferredSize(new Dimension(grid.length*zoom, grid[0].length*zoom));
    }

    @Override
    protected void paintComponent(Graphics g) {
       // super.paintComponent(g);
        for (int x = 0; x < lawn.getGridWidth(); x++) {
            for (int y = 0; y < lawn.getGridHeight(); y++) {
                int cellValue = lawn.getGrid()[x][y];
                Color cellColor;
                switch (cellValue){
                    case 0:
                        cellColor = Color.GREEN; //uncut
                        break;
                    case 1:
                        cellColor = Color.yellow; //cut
                        break;
                    case 2:
                        cellColor = Color.BLACK; //obstacle
                        break;
                    case 3:
                        cellColor = Color.CYAN; //wire
                        break;
//                    case 4:
//                        cellColor = new Color(0, 200, 0); // elevation
//                        break;                             Future work , was planning on implementing elevated areas of lawn

                    default:
                        cellColor = Color.WHITE;
                }
                g.setColor(cellColor);

                g.fillRect(((x)*zoom), ((y)*zoom) , cellSize, cellSize);
            }
        }
        g.setColor(Color.gray);
        for (int x = 0; x <= ((lawn.getGridWidth()-1)*zoom) +cellSize; x += cellSize) {
            g.drawLine(x, 0, x, ((lawn.getGridHeight())*zoom)+cellSize);
        }
        for (int y = 0; y <= ((lawn.getGridHeight()-1)*zoom)+cellSize; y += cellSize) {
            g.drawLine(0, y, ((lawn.getGridWidth())*zoom)+cellSize, y);
        }
        // Draw the lawnmower
        g.setColor(Color.RED);
        g.fillOval(((int)lawnmower.getX()*zoom),((int)lawnmower.getY()*zoom),10,10);

    }
}
