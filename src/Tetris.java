import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It`s my first Java project - Tetris game
 *
 * Created by Remar on 21.11.2017.
 */
public class Tetris {
    public static void main(String[] args) {
        new Main_Window();
    }
}

class Main_Window extends JFrame {
    public Main_Window() {
        setSize(500,500);
        setVisible(true);

        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu file_menu = new Menu("File");
        menuBar.add(file_menu);
        MenuItem close_btn = new MenuItem("Close");
        file_menu.add(close_btn);
        close_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        Menu prop_menu = new Menu("Properties");
        menuBar.add(prop_menu);

        setLayout(new BorderLayout());
        Panel sys_panel = new System_Side();
        add(sys_panel, BorderLayout.WEST);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}

class System_Side extends Panel {
    public System_Side() {
        setLayout(new GridLayout(4, 2));
        JLabel nextFig_label = (JLabel)add(new JLabel("Next Figure: "));
        add(new JLabel("FIGURE"));
        JLabel score_label = (JLabel)add(new JLabel("Score: "));
        add(new JLabel("SCORE"));
        JLabel timer_label = (JLabel)add(new JLabel("Timer: "));
        add(new JLabel("TIMER"));
        JButton start_btn = (JButton)add(new JButton("Start"));
        JButton pause_btn = (JButton)add(new JButton("Pause"));
    }
}