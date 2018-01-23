package tetris.move;

import tetris.PlayField;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class MoveDown {

    public MoveDown(PlayField playField) {
        moveDown(playField);
    }

    private void moveDown(PlayField playField) {
        Area figure = playField.getFigure();
        if (playField.getBottomArea().isEmpty()){
            if (figure.getBounds().y < playField.getPlayFieldHeight() - figure.getBounds().height)
                while (figure.getBounds().y < playField.getPlayFieldHeight() - figure.getBounds().height)
                    figure.transform(AffineTransform.getTranslateInstance(0, playField.getFigureYSize()));
            else{
                playField.getBottomArea().add(new Area(figure));
                PlayField.figureCounter--;
            }
        }
        else {
            if (figure.getBounds().y < playField.getPlayFieldHeight() - figure.getBounds().height)
                while (figure.getBounds().y < playField.getPlayFieldHeight() - figure.getBounds().height) {
                    figure.transform(AffineTransform.getTranslateInstance(0, playField.getFigureYSize()));
                    Area figure_clone = (Area) figure.clone();
                    figure_clone.intersect(playField.getBottomArea());
                    if (!figure_clone.isEmpty()) {
                        figure.transform(AffineTransform.getTranslateInstance(0, -playField.getFigureYSize()));
                        playField.getBottomArea().add(figure);
                        playField.fillingCheck();
                        PlayField.figureCounter--;
                        break;
                    }
                }
            else {
                playField.getBottomArea().add(figure);
                playField.fillingCheck();
                PlayField.figureCounter--;
            }
        }
    }

}
