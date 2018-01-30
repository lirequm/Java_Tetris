package tetris.move;

import tetris.panels.PlayField;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class MoveLeft {

    public MoveLeft(PlayField playField) {
        moveLeft(playField);
    }

    private void moveLeft(PlayField playField) {
        Area figure = playField.getFigure();
        if (playField.getBottomArea().isEmpty()){
            if (figure.getBounds().x > 0)
                figure.transform(AffineTransform.getTranslateInstance(-playField.getFigureXSize(), 0));
        }
        else {
            if (figure.getBounds().x > 0) {
                figure.transform(AffineTransform.getTranslateInstance(-playField.getFigureXSize(), 0));
                Area figure_clone = (Area) figure.clone();
                figure_clone.intersect(playField.getBottomArea());
                if (!figure_clone.isEmpty())
                    figure.transform(AffineTransform.getTranslateInstance(playField.getFigureXSize(), 0));
            }
        }
    }
}
