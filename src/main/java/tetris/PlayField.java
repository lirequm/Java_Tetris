package tetris;

import tetris.figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class PlayField extends JPanel {
    public static int figureCounter;
    private final Color PLAY_FIELD_BACKGROUND = Color.CYAN;
    public int figurePos;
    public int figure_switch = (int) (Math.random() * 7);
    private int playFieldWidth = 260;
    private int playFieldHeight = 520;
    private int figureXSize = 26;
    private int figureYSize = 26;
    private Area figure;
    private Area bottomArea = new Area();
    private Color figureColor = Color.RED;
    private MainWindow mainWindow;
    private int currFigureSwitch;


    PlayField(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setPreferredSize(new Dimension(playFieldWidth, playFieldHeight));
    }

    public void fillingCheck() {
        int y = playFieldHeight - figureYSize;
        while (y > 0) {
            if (bottomArea.contains(0, y, playFieldWidth, figureYSize)) {
                Area down_area = new Area(new Rectangle(0, y + figureYSize, playFieldWidth, playFieldHeight));
                down_area.intersect(bottomArea);
                Area upper_area = new Area(new Rectangle(0, y - playFieldHeight, playFieldWidth, playFieldHeight));
                upper_area.intersect(bottomArea);
                if(!upper_area.isEmpty()) {
                    upper_area.transform(AffineTransform.getTranslateInstance(0, +figureYSize));
                    if(!down_area.isEmpty()) {
                        bottomArea = down_area;
                        bottomArea.add(upper_area);
                    } else {
                        bottomArea = upper_area;
                    }
                } else {
                    if(!down_area.isEmpty()) {
                        bottomArea = down_area;
                    }
                }
            }
            y -= figureYSize;
        }
    }

    private void figure_switcher(){
        switch (figure_switch) {
            case (0):
                figure = new Figure(figureXSize * 4, 0, figureXSize, figureYSize).getFigureL();
                System.out.println("Figure L created");
                break;
            case (1):
                figure = new Figure(figureXSize * 4, 0, figureXSize, figureYSize).getFigureO();
                System.out.println("Figure O created");
                break;
            case (2):
                figure = new Figure(figureXSize * 3, 0, figureXSize, figureYSize).getFigureY();
                System.out.println("Figure Y created");
                break;
            case (3):
                figure = new Figure(figureXSize * 4, 0, figureXSize, figureYSize).getFigureI();
                System.out.println("Figure I created");
                break;
            case (4):
                figure = new Figure(figureXSize * 3, 0, figureXSize, figureYSize).getFigureZ();
                System.out.println("Figure Z created");
                break;
            case (5):
                figure = new Figure(figureXSize * 4, 0, figureXSize, figureYSize).getFigureJ();
                System.out.println("Figure J created");
                break;
            case (6):
                figure = new Figure(figureXSize * 3, 0, figureXSize, figureYSize).getFigureS();
                System.out.println("Figure S created");
                break;
        }
        currFigureSwitch = figure_switch;
        figure_switch = (int) (Math.random() * 7);
    }

    public Area getFigure() {
        return figure;
    }

    public Area getBottomArea() {
        return bottomArea;
    }

    public int getFigureXSize() {
        return figureXSize;
    }

    public int getFigureYSize() {
        return figureYSize;
    }

    public int getPlayFieldWidth() {
        return playFieldWidth;
    }

    public int getPlayFieldHeight() {
        return playFieldHeight;
    }

    public Color getFigureColor() {
        return figureColor;
    }

    public int getCurrFigureSwitch() {
        return currFigureSwitch;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(PLAY_FIELD_BACKGROUND);
        Graphics2D g2d = (Graphics2D) g;

        if (figureCounter < 1) {
            figure_switcher();
            figurePos = 0;
            mainWindow.getSystemSide().getNextFigure().repaint();
        }

        if (!bottomArea.isEmpty()) {
            g2d.setColor(figureColor);
            g2d.fill(bottomArea);
        }

        g2d.setColor(figureColor);
        g2d.fill(figure);
        mainWindow.requestFocus();
    }
}
