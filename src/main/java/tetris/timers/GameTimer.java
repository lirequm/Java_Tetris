package tetris.timers;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GameTimer extends Timer {
    public GameTimer(int delay, ActionListener listener) {
        super(delay, listener);
        setActionCommand("gameTimerAction");
        setInitialDelay(100);
    }
}
