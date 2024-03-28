import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controlPanel extends JPanel {
    // Create buttons
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
   private boolean pause;
   private boolean quit;
   private double speedmultiplier;
    public controlPanel() {
        initializeComponents();
        setupLayout();
    }
    public void initializeComponents() {
        button1 = new JButton("Resume");
        button2 = new JButton("Stop");
        button3 = new JButton("Normal Speed");
        button4 = new JButton(" speed x10");
        button5 = new JButton("speed x30");
        button6 = new JButton("quit");
        pause =false;
        quit =false;
        speedmultiplier = 1.0;

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                isPaused();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                isResumed();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                speedmultiplier = 1.0;
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                speedmultiplier = 10.0;

            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                speedmultiplier = 30.0;

            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Button 1 clicked");
                isQuit();

            }
        });
    }
    public void setupLayout() {
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);

    }
    public void isPaused(){
        pause = true;
    }
    public void isResumed(){
        pause = false;
    }
    public void isQuit(){
        quit = true;
    }
    public boolean getPause(){return pause;}
    public boolean getQuit(){return quit;}

    public double getSpeedmultiplier(){return speedmultiplier;}



}