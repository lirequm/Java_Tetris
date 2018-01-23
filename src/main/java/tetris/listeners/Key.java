package tetris.listeners;

import tetris.MainWindow;
import tetris.PlayField;
import tetris.move.MoveDown;
import tetris.move.MoveLeft;
import tetris.move.MoveRight;
import tetris.rotate.Rotate;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;

public class Key extends KeyAdapter {
    private PlayField playField;
    private MainWindow mainWindow;

    public Key(MainWindow mainWindow, PlayField playField) {
        this.mainWindow = mainWindow;
        this.playField = playField;
    }

    private String getCoordinates(PlayField playField) {
        Area figure = playField.getFigure();
        return figure.getBounds().x + " " + figure.getBounds().y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (mainWindow.getGameTimer().isRunning()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    new MoveLeft(playField);
                    System.out.println("Left pressed");
                    System.out.println(getCoordinates(playField));
                    playField.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    new MoveRight(playField);
                    System.out.println("Right pressed");
                    System.out.println(getCoordinates(playField));
                    playField.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    new MoveDown(playField);
                    System.out.println("Down pressed");
                    System.out.println(getCoordinates(playField));
                    playField.repaint();
                    break;
                case KeyEvent.VK_UP:
                    new Rotate(playField);
                    System.out.println("Up pressed");
                    System.out.println(getCoordinates(playField));
                    playField.repaint();
                    break;
            }
        }
    }
}
