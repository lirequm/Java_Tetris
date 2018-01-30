package tetris.listeners;

import tetris.MainWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Item implements ItemListener {
    private MainWindow mainWindow;

    public Item(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        mainWindow.relaxSelected = e.getStateChange() == ItemEvent.SELECTED;
    }
}
