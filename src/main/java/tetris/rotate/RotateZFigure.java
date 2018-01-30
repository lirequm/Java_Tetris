package tetris.rotate;

import tetris.panels.PlayField;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

class RotateZFigure {

    RotateZFigure(PlayField playField) {
        rotate(playField);
    }

    private void rotate(PlayField playField) {
        Area figure = playField.getFigure();
        Area figureClone = (Area) figure.clone();
        if (playField.getBottomArea().isEmpty()) {
            switch (playField.figurePos) {
                case (0):
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2)));
                    if((figureClone.getBounds().x >= 0) &&
                            (figureClone.getBounds().x <= playField.getPlayFieldWidth() - figureClone.getBounds().width) &&
                            (figure.getBounds().y < playField.getPlayFieldHeight() - figureClone.getBounds().height)) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                figure.getBounds().y + (playField.getFigureYSize() / 2)));
                        playField.figurePos = 1;
                    }
                    break;
                case (1):
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            -1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    if((figureClone.getBounds().x >= 0) &&
                            (figureClone.getBounds().x <= playField.getPlayFieldWidth() - figureClone.getBounds().width) &&
                            (figure.getBounds().y < playField.getPlayFieldHeight() - figureClone.getBounds().height)) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                -1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                        playField.figurePos = 0;
                    }
                    break;
            }
        } else {
            switch (playField.figurePos) {
                case (0):
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2)));
                    if((figureClone.getBounds().x >= 0) &&
                            (figureClone.getBounds().x <= playField.getPlayFieldWidth() - figureClone.getBounds().width) &&
                            (figure.getBounds().y < playField.getPlayFieldHeight() - figureClone.getBounds().height)) {
                        figureClone.intersect(playField.getBottomArea());
                        if (figureClone.isEmpty()) {
                            figure.transform(AffineTransform.getQuadrantRotateInstance(
                                    1,
                                    figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                    figure.getBounds().y + (playField.getFigureYSize() / 2)));
                            playField.figurePos = 1;
                        }
                    }
                    break;
                case (1):
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            -1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    if((figureClone.getBounds().x >= 0) &&
                            (figureClone.getBounds().x <= playField.getPlayFieldWidth() - figureClone.getBounds().width) &&
                            (figure.getBounds().y < playField.getPlayFieldHeight() - figureClone.getBounds().height)) {
                        figureClone.intersect(playField.getBottomArea());
                        if (figureClone.isEmpty()) {
                            figure.transform(AffineTransform.getQuadrantRotateInstance(
                                    -1,
                                    figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                    figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                            playField.figurePos = 0;
                        }
                    }
                    break;
            }
        }
    }
}
