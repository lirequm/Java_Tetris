package tetris.panels;

import tetris.MainWindow;

import javax.swing.*;
import java.awt.*;

public class SystemSide extends JPanel {

    public static JLabel timeLabel;
    public static JLabel scoreLabel;
    public static JLabel completeActions;
    public static JLabel completeLines;



    private NextFigurePanel nextFigure;
    private JButton start_btn;
    private JButton pause_btn;

    public SystemSide(MainWindow mainWindow) {

        setLayout(new GridLayout(6, 2));
        add(new JLabel("Next Figure: "));
        nextFigure = new NextFigurePanel(mainWindow.getPlayField());
        JPanel nextFig_panel = (JPanel) add(nextFigure);
        add(new JLabel("Complete actions: "));
        completeActions = (JLabel) add(new JLabel("0"));
        add(new JLabel("Lines copleted: "));
        completeLines = (JLabel) add(new JLabel("0"));
        add(new JLabel("Score: "));
        scoreLabel = (JLabel) add(new JLabel("0"));
        add(new JLabel("Time: "));
        timeLabel = (JLabel) add(new JLabel("0.00"));
        start_btn = (JButton) add(new JButton("Start"));
        start_btn.setActionCommand("startSystemTimer");
        start_btn.setFocusable(false);
        pause_btn = (JButton) add(new JButton("Pause"));
        pause_btn.setActionCommand("stopSystemTimer");
        pause_btn.setFocusable(false);
    }

    public NextFigurePanel getNextFigure() {
        return nextFigure;
    }

    public JButton getStart_btn() {
        return start_btn;
    }

    public JButton getPause_btn() {
        return pause_btn;
    }
}