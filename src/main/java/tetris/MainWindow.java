package tetris;

import tetris.listeners.Action;
import tetris.listeners.Key;
import tetris.timers.GameTimer;
import tetris.timers.SystemTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame {
    private SystemSide systemSide;
    private PlayField playField;
    private Timer gameTimer, systemTimer;

    public static MainWindow mainWindow;


    public MainWindow() throws HeadlessException {

        setSize(520, 520);
        setResizable(false);
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu fileMenu = new Menu("File");
        menuBar.add(fileMenu);
//        MenuItem resetBtn = new MenuItem("Reset");
//        fileMenu.add(resetBtn);
//        resetBtn.setActionCommand("reset");
        MenuItem closeBtn = new MenuItem("Close");
        fileMenu.add(closeBtn);
        closeBtn.setActionCommand("exit");
        Menu prop_menu = new Menu("Properties");
        menuBar.add(prop_menu);
        setFocusable(true);

        setLayout(new GridLayout(1, 2));
        playField = new PlayField(this);
        JPanel playFieldPanel = playField;
        add(playFieldPanel);
        systemSide = new SystemSide(this);
        JPanel systemSidePanel = systemSide;
        add(systemSidePanel);

        ActionListener actionListener = new Action(this, playField);
        closeBtn.addActionListener(actionListener);

        gameTimer = new GameTimer(1000, actionListener);
        systemTimer = new SystemTimer(100, actionListener);

        KeyListener keyListener = new Key(this, playField);
        addKeyListener(keyListener);

        systemSide.getStart_btn().addActionListener(actionListener);
        systemSide.getPause_btn().addActionListener(actionListener);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();
    }

    public static void main(String[] args) {
        mainWindow = new MainWindow();
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
}
