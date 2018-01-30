package tetris.panels;

import javax.swing.*;
import java.awt.*;

public class IconPanel implements Icon {
    private Color color;

    public IconPanel(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRect(x,y, 10, 10);
    }

    @Override
    public int getIconWidth() {
        return 10;
    }

    @Override
    public int getIconHeight() {
        return 10;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
