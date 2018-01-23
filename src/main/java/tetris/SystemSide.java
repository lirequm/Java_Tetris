package tetris;

import javax.swing.*;
import java.awt.*;

public class SystemSide extends JPanel {

    public static JLabel time_label;


    private NextFigurePanel nextFigure;
    private JButton start_btn;
    private JButton pause_btn;

    SystemSide(MainWindow mainWindow) {

        setLayout(new GridLayout(4, 2));
        add(new JLabel("Next Figure: "));
        nextFigure = new NextFigurePanel(mainWindow.getPlayField());
        JPanel nextFig_panel = (JPanel) add(nextFigure);
        add(new JLabel("Score: "));
        JLabel score_label = (JLabel) add(new JLabel("")); // + score
        add(new JLabel("Time: "));
        time_label = (JLabel) add(new JLabel("0.00"));
        start_btn = (JButton) add(new JButton("Start"));
        start_btn.setActionCommand("startSystemTimer");
        pause_btn = (JButton) add(new JButton("Pause"));
        pause_btn.setActionCommand("stopSystemTimer");
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