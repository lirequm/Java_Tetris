package tetris.move;

import tetris.panels.PlayField;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class MoveRight {

    public MoveRight(PlayField playField) {
        moveRight(playField);
    }

    private void moveRight(PlayField playField) {
        Area figure = playField.getFigure();
        if (playField.getBottomArea().isEmpty()){
            if (figure.getBounds().x < playField.getPlayFieldWidth() - figure.getBounds().width)
                figure.transform(AffineTransform.getTranslateInstance(playField.getFigureXSize(), 0));
        }
        else {
            if (figure.getBounds().x < playField.getPlayFieldWidth() - figure.getBounds().width) {
                figure.transform(AffineTransform.getTranslateInstance(playField.getFigureXSize(), 0));
                Area figure_clone = (Area) figure.clone();
                figure_clone.intersect(playField.getBottomArea());
                if (!figure_clone.isEmpty())
                    figure.transform(AffineTransform.getTranslateInstance(-playField.getFigureXSize(), 0));
            }
        }
    }
}
