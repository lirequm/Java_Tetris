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


    private MainWindow() throws HeadlessException {

        setSize(520, 520);
        setResizable(false);
        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu file_menu = new Menu("File");
        menuBar.add(file_menu);
        MenuItem close_btn = new MenuItem("Close");
        file_menu.add(close_btn);
        close_btn.setActionCommand("exit");
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
        close_btn.addActionListener(actionListener);

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
}
