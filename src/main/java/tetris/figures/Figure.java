package tetris.figures;

import tetris.panels.PlayField;

import java.awt.*;
import java.awt.geom.Area;

public class Figure {
    private int x, y, sizeX, sizeY;

    public Figure(int sizeX, int sizeY) {
        x = 0;
        y = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Figure(int x_pos, int y_pos, int sizeX, int sizeY) {
        PlayField.figureCounter++;
        x = x_pos;
        y = y_pos;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Area getFigureO() {
        Area a = new Area(new Rectangle(x, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + sizeY, sizeX, sizeY)));
        return a;
    }

    public Area getFigureJ() {
        Area a = new Area(new Rectangle(x + sizeX, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x + sizeX, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + (2 * sizeY), sizeX, sizeY)));
        a.add(new Area(new Rectangle(x, y + (2 * sizeY), sizeX, sizeY)));
        return a;
    }

    public Area getFigureL() {
        Area a = new Area(new Rectangle(x, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x, y + (2 * sizeY), sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + (2 * sizeY), sizeX, sizeY)));
        return a;
    }

    public Area getFigureY() {
        Area a = new Area(new Rectangle(x, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x + sizeX, y, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + (2 * sizeX), y, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + sizeY, sizeX, sizeY)));
        return a;
    }

    public Area getFigureI() {
        Area a = new Area(new Rectangle(x, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x, y + (2 * sizeY), sizeX, sizeY)));
        a.add(new Area(new Rectangle(x, y + (3 * sizeY), sizeX, sizeY)));
        return a;
    }

    public Area getFigureZ() {
        Area a = new Area(new Rectangle(x, y, sizeX, sizeY));
        a.add(new Area(new Rectangle(x + sizeX, y, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + (2 * sizeX), y + sizeY, sizeX, sizeY)));
        return a;
    }

    public Area getFigureS() {
        Area a = new Area(new Rectangle(x, y + sizeY, sizeX, sizeY));
        a.add(new Area(new Rectangle(x + sizeX, y, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + sizeX, y + sizeY, sizeX, sizeY)));
        a.add(new Area(new Rectangle(x + (2 * sizeX), y, sizeX, sizeY)));
        return a;
    }
}
