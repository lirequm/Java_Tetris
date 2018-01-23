package tetris.timers;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SystemTimer extends Timer {
    public SystemTimer(int delay, ActionListener listener) {
        super(delay, listener);
        setActionCommand("systemTimerAction");
    }
}
