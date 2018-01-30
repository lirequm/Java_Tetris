package tetris.listeners;

import tetris.MainWindow;
import tetris.panels.PlayField;
import tetris.panels.SystemSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.text.DecimalFormat;

public class Action implements ActionListener {
    private MainWindow mainWindow;
    private PlayField playField;
    private double time = 0.00f;

    public Action(MainWindow mainWindow, PlayField playField) {
        this.mainWindow = mainWindow;
        this.playField = playField;
    }

    private void timeLabelUpdate() {
        SystemSide.timeLabel.setText(new DecimalFormat("#0.00").format(time = time + 0.1));
        SystemSide.timeLabel.repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "exit":
                System.exit(0);
                break;
            case "startSystemTimer":
                mainWindow.getSystemTimer().start();
                mainWindow.getGameTimer().start();
                break;
            case "stopSystemTimer":
                mainWindow.getSystemTimer().stop();
                mainWindow.getGameTimer().stop();
                break;
            case "systemTimerAction":
                timeLabelUpdate();
                break;
            case "gameTimerAction":
                if (playField.getBottomArea().isEmpty()){
                    if (playField.getFigure().getBounds().y < playField.getPlayFieldHeight() - playField.getFigure().getBounds().height)
                        playField.getFigure().transform(AffineTransform.getTranslateInstance(0, playField.getFigureYSize()));
                    else{
                        playField.getBottomArea().add(new Area(playField.getFigure()));
                        PlayField.figureCounter--;
                    }
                }
                else {
                    if (playField.getFigure().getBounds().y < playField.getPlayFieldHeight() - playField.getFigure().getBounds().height) {
                        playField.getFigure().transform(AffineTransform.getTranslateInstance(0, playField.getFigureYSize()));
                        Area figure_clone = (Area) playField.getFigure().clone();
                        figure_clone.intersect(playField.getBottomArea());
                        if (!figure_clone.isEmpty()) {
                            playField.getFigure().transform(AffineTransform.getTranslateInstance(0, -playField.getFigureYSize()));
                            playField.getBottomArea().add(playField.getFigure());
                            playField.fillingCheck();
                            PlayField.figureCounter--;
                        }
                    }
                    else {
                        playField.getBottomArea().add(playField.getFigure());
                        playField.fillingCheck();
                        PlayField.figureCounter--;
                    }
                }
                System.out.println("Step down");
                System.out.println(playField.getFigure().getBounds().x + " " + playField.getFigure().getBounds().y);
                playField.repaint();
                break;
            case "fColorChooser":
                Color fColor = JColorChooser.showDialog(mainWindow, "Choose figure color", Color.ORANGE);
                playField.setFigureColor(fColor);
                mainWindow.fPanel.setColor(fColor);
                playField.repaint();
                break;
            case "bColorChooser":
                Color bColor = JColorChooser.showDialog(mainWindow, "Choose background color", Color.GRAY);
                playField.setBackgroundColor(bColor);
                mainWindow.bPanel.setColor(bColor);
                playField.repaint();
                break;
        }
    }
}
