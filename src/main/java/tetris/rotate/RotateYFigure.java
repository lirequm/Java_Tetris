package tetris.rotate;

import tetris.PlayField;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

class RotateYFigure {

    RotateYFigure(PlayField playField) {
        rotate(playField);
    }

    private void rotate(PlayField playField) {
        Area figure = playField.getFigure();
        if (playField.getBottomArea().isEmpty()) {
            switch (playField.figurePos) {
                case (0):
                    figure.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2)));
                    playField.figurePos = 1;
                    break;
                case (1):
                    figure.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    playField.figurePos = 2;
                    break;
                case (2):
                    figure.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    playField.figurePos = 3;
                    break;
                case (3):
                    figure.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    playField.figurePos = 0;
                    break;
            }
        } else {
            Area figureClone;
            switch (playField.figurePos) {
                case (0):
                    figureClone = (Area) figure.clone();
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2)));
                    figureClone.intersect(playField.getBottomArea());
                    if (figureClone.isEmpty()) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                figure.getBounds().y + (playField.getFigureYSize() / 2)));
                        playField.figurePos = 1;
                    }
                    break;
                case (1):
                    figureClone = (Area) figure.clone();
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    figureClone.intersect(playField.getBottomArea());
                    if (figureClone.isEmpty()) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                        playField.figurePos = 2;
                    }
                    break;
                case (2):
                    figureClone = (Area) figure.clone();
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    figureClone.intersect(playField.getBottomArea());
                    if (figureClone.isEmpty()) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2 * 3),
                                figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                        playField.figurePos = 3;
                    }
                    break;
                case (3):
                    figureClone = (Area) figure.clone();
                    figureClone.transform(AffineTransform.getQuadrantRotateInstance(
                            1,
                            figure.getBounds().x + (playField.getFigureXSize() / 2),
                            figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                    figureClone.intersect(playField.getBottomArea());
                    if (figureClone.isEmpty()) {
                        figure.transform(AffineTransform.getQuadrantRotateInstance(
                                1,
                                figure.getBounds().x + (playField.getFigureXSize() / 2),
                                figure.getBounds().y + (playField.getFigureYSize() / 2 * 3)));
                        playField.figurePos = 0;
                    }
                    break;
            }
        }
    }
}
