package tetris;

import tetris.listeners.Action;
import tetris.listeners.Item;
import tetris.listeners.Key;
import tetris.panels.IconPanel;
import tetris.panels.PlayField;
import tetris.panels.SystemSide;
import tetris.timers.GameTimer;
import tetris.timers.SystemTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    private SystemSide systemSide;
    private PlayField playField;
    private Timer gameTimer, systemTimer;
    public boolean relaxSelected;
    public IconPanel fPanel = new IconPanel(Color.ORANGE);
    public IconPanel bPanel = new IconPanel(Color.GRAY);
    private int gameTimerDelay = 1000;

    private MainWindow() throws HeadlessException {

        setResizable(false);
        setFocusable(true);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
//        MenuItem resetBtn = new MenuItem("Reset");
//        fileMenu.add(resetBtn);
//        resetBtn.setActionCommand("reset");
        JMenuItem closeBtn = new JMenuItem("Close");
        fileMenu.add(closeBtn);
        closeBtn.setActionCommand("exit");
        JMenu propMenu = new JMenu("Properties");
        menuBar.add(propMenu);
        JMenuItem fColor = new JMenuItem("Figure Color", fPanel);
        propMenu.add(fColor);
        fColor.setActionCommand("fColorChooser");
        JMenuItem bColor = new JMenuItem("BackGround Color", bPanel);
        propMenu.add(bColor);
        bColor.setActionCommand("bColorChooser");
        propMenu.addSeparator();
        JCheckBoxMenuItem cbRelax = new JCheckBoxMenuItem("Relax mode");
        propMenu.add(cbRelax);
        cbRelax.setState(false);

        setLayout(new GridLayout(1, 2));
        playField = new PlayField(this);
        JPanel playFieldPanel = playField;
        add(playFieldPanel);
        systemSide = new SystemSide(this);
        JPanel systemSidePanel = systemSide;
        add(systemSidePanel);

        ActionListener actionListener = new Action(this, playField);
        closeBtn.addActionListener(actionListener);
        fColor.addActionListener(actionListener);
        bColor.addActionListener(actionListener);

        ItemListener itemListener = new Item(this);
        cbRelax.addItemListener(itemListener);

        gameTimer = new GameTimer(gameTimerDelay, actionListener);
        systemTimer = new SystemTimer(100, actionListener);

        KeyListener keyListener = new Key(this, playField);
        addKeyListener(keyListener);

        systemSide.getStart_btn().addActionListener(actionListener);
        systemSide.getPause_btn().addActionListener(actionListener);

        fPanel.setColor(playField.getFigureColor());
        bPanel.setColor(playField.getBackgroundColor());


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520, 520);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }

    public SystemSide getSystemSide() {
        return systemSide;
    }

    public PlayField getPlayField() {
        return playField;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public Timer getSystemTimer() {
        return systemTimer;
    }

    public int getGameTimerDelay() {
        return gameTimerDelay;
    }

    public void setGameTimerDelay(int gameTimerDelay) {
        if(!relaxSelected) {
            this.gameTimerDelay = gameTimerDelay;
            gameTimer.setDelay(gameTimerDelay);
        }
    }
}
