package tetris.rotate;

import tetris.PlayField;

public class Rotate {

    public Rotate(PlayField playField) {
        rotate(playField);
    }

    private void rotate(PlayField playField) {
        switch (playField.getCurrFigureSwitch()) {
            case (0):
                new RotateLFigure(playField);
                System.out.println("Figure L rotated");
                break;
            case (1):
                System.out.println("Figure O unrotateable");
                break;
            case (2):
                new RotateYFigure(playField);
                System.out.println("Figure Y rotated");
                break;
            case (3):
                //TODO RotateIFigure HERE
                System.out.println("Figure I rotated");
                break;
            case (4):
                //TODO RotateZFigure HERE
                System.out.println("Figure Z rotated");
                break;
            case (5):
                //TODO RotateJFigure HERE
                System.out.println("Figure J rotated");
                break;
            case (6):
                //TODO RotateSFigure HERE
                System.out.println("Figure S rotated");
                break;
        }
    }
}
