package tetris.panels;

import tetris.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;

public class NextFigurePanel extends JPanel {
    private Area nextFigure;
    private PlayField playField;

    NextFigurePanel(PlayField playField) {
        this.playField = playField;
    }

    private void nextFigure_switcher() {
        switch (playField.figure_switch) {
            case (0):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureL();
                System.out.println("Next Figure L created");
                break;
            case (1):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureO();
                System.out.println("Next Figure O created");
                break;
            case (2):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureY();
                System.out.println("Next Figure Y created");
                break;
            case (3):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureI();
                System.out.println("Next Figure I created");
                break;
            case (4):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureZ();
                System.out.println("Next Figure Z created");
                break;
            case (5):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureJ();
                System.out.println("Next Figure J created");
                break;
            case (6):
                nextFigure = new Figure(playField.getFigureXSize(), playField.getFigureYSize()).getFigureS();
                System.out.println("Next Figure S created");
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        nextFigure_switcher();
        g2d.setColor(playField.getFigureColor());
        g2d.fill(nextFigure);
    }
}

