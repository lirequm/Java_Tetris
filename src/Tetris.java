import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.text.DecimalFormat;

/**
 * It`s my first Java project - Tetris game
 * <p>
 * Created by Remar on 21.11.2017.
 */
public class Tetris extends JFrame {
    int PLAY_FIELD_WIDTH = 250;
    int PLAY_FIELD_HEIGHT = PLAY_FIELD_WIDTH * 2;
    static int figure_counter = 0;
    int figure_size = PLAY_FIELD_WIDTH / 10;
    double time = 0.00f;
    int time_update = 100;
    float speed = 1;
    int score = 100;
    PlayField playField;
    NextFigure nextFigure;
    L_Figure l_figure;
    L_Mirrored_Figure l_mirrored_figure;
    O_Figure o_figure;
    Y_Figure y_figure;
    I_Figure i_figure;
    Z_Figure z_figure;
    Z_Mirrored_Figure z_mirrored_figure;
    Timer time_t, game_t;
    Color figure_color = Color.RED;
    Area bottom_area = null;


    public Tetris() {
        setSize(500, 500);
        Component mainWindow = this;


        MenuBar menuBar = new MenuBar();
        setMenuBar(menuBar);
        Menu file_menu = new Menu("File");
        menuBar.add(file_menu);
        MenuItem close_btn = new MenuItem("Close");
        file_menu.add(close_btn);
        close_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Menu prop_menu = new Menu("Properties");
        menuBar.add(prop_menu);

        setLayout(new GridLayout(1, 2));
        JPanel sys_panel = new System_Side();
        playField = new PlayField();
        add(playField);
        add(sys_panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Tetris();
    }

    class System_Side extends JPanel {
        System_Side() {
            setLayout(new GridLayout(4, 2));
            add(new JLabel("Next Figure: "));
            nextFigure = new NextFigure();
            JPanel nextFig_panel = (JPanel) add(nextFigure);
            add(new JLabel("Score: "));
            JLabel score_label = (JLabel) add(new JLabel("" + score));
            add(new JLabel("Time: "));
            JLabel time_label = (JLabel) add(new JLabel("0.00"));
            JButton start_btn = (JButton) add(new JButton("Start"));
            start_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time_t.start();
                    game_t.start();
                }
            });
            JButton pause_btn = (JButton) add(new JButton("Pause"));
            pause_btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time_t.stop();
                    game_t.stop();
                }
            });

            ActionListener timeUpdateTask = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time_label.setText(new DecimalFormat("#0.00").format(time = time + 0.1));
                    time_label.repaint();
                }
            };
            time_t = new Timer(time_update, timeUpdateTask);
        }
    }

    class PlayField extends JPanel {
        Color PLAY_FIELD_BACKGROUND = Color.CYAN;
        int figure_switch = (int) (Math.random() * 7);
        Area figure;

        PlayField(){
            setPreferredSize(new Dimension(PLAY_FIELD_WIDTH, PLAY_FIELD_HEIGHT));
            setFocusable(true);

            game_t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (bottom_area == null){
                        if (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height)
                            figure.transform(AffineTransform.getTranslateInstance(0, figure_size));
                        else{
                            bottom_area = new Area(figure);
                            figure_counter--;
                        }
                    }
                    else {
                        if (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height) {
                            figure.transform(AffineTransform.getTranslateInstance(0, figure_size));
                            Area figure_clone = (Area) figure.clone();
                            figure_clone.intersect(bottom_area);
                            if (!figure_clone.isEmpty()) {
                                figure.transform(AffineTransform.getTranslateInstance(0, -figure_size));
                                bottom_area.add(figure);
                                figure_counter--;
                            }
                        }
                        else {
                            bottom_area.add(figure);
                            figure_counter--;
                        }
                    }
                    System.out.println("Step down");
                    System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                    playField.repaint();
                    nextFigure.repaint();
                }
            });
            game_t.setInitialDelay(100);

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (game_t.isRunning()) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_LEFT:
                                if (bottom_area == null){
                                    if (figure.getBounds().x > 0)
                                        figure.transform(AffineTransform.getTranslateInstance(-figure_size, 0));
                                }
                                else {
                                    if (figure.getBounds().x > 0) {
                                        figure.transform(AffineTransform.getTranslateInstance(-figure_size, 0));
                                        Area figure_clone = (Area) figure.clone();
                                        figure_clone.intersect(bottom_area);
                                        if (!figure_clone.isEmpty())
                                            figure.transform(AffineTransform.getTranslateInstance(figure_size, 0));
                                    }
                                }
                                System.out.println("Left pressed");
                                System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                                playField.repaint();
                                break;
                            case KeyEvent.VK_RIGHT:
                                if (bottom_area == null){
                                    if (figure.getBounds().x < PLAY_FIELD_WIDTH - figure.getBounds().width)
                                        figure.transform(AffineTransform.getTranslateInstance(figure_size, 0));
                                }
                                else {
                                    if (figure.getBounds().x < PLAY_FIELD_WIDTH - figure.getBounds().width) {
                                        figure.transform(AffineTransform.getTranslateInstance(figure_size, 0));
                                        Area figure_clone = (Area) figure.clone();
                                        figure_clone.intersect(bottom_area);
                                        if (!figure_clone.isEmpty())
                                            figure.transform(AffineTransform.getTranslateInstance(-figure_size, 0));
                                    }
                                }
                                System.out.println("Right pressed");
                                System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                                playField.repaint();
                                break;
                            case KeyEvent.VK_DOWN:
                                if (bottom_area == null){
                                    if (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height)
                                        while (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height)
                                          figure.transform(AffineTransform.getTranslateInstance(0, figure_size));
                                    else{
                                        bottom_area = new Area(figure);
                                        figure_counter--;
                                    }
                                }
                                else {
                                    if (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height)
                                        while (figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height) {
                                            figure.transform(AffineTransform.getTranslateInstance(0, figure_size));
                                            Area figure_clone = (Area) figure.clone();
                                            figure_clone.intersect(bottom_area);
                                            if (!figure_clone.isEmpty()) {
                                                figure.transform(AffineTransform.getTranslateInstance(0, -figure_size));
                                                bottom_area.add(figure);
                                                figure_counter--;
                                                break;
                                            }
                                        }
                                    else {
                                        bottom_area.add(figure);
                                        figure_counter--;
                                    }
                                }
                                System.out.println("Down pressed");
                                System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                                playField.repaint();
                                break;
                        }
                    }
                }
            });

            requestFocus();
        }

        void figure_switcher(){
            switch (figure_switch) {
                case (0):
                    l_figure = new L_Figure(figure_size * 4, 0);
                    figure = l_figure.getFigure();
                    System.out.println("Figure L created");
                    break;
                case (1):
                    o_figure = new O_Figure(figure_size * 4, 0);
                    figure = o_figure.getFigure();
                    System.out.println("Figure O created");
                    break;
                case (2):
                    y_figure = new Y_Figure(figure_size * 3, 0);
                    figure = y_figure.getFigure();
                    System.out.println("Figure Y created");
                    break;
                case (3):
                    i_figure = new I_Figure(figure_size * 4, 0);
                    figure = i_figure.getFigure();
                    System.out.println("Figure I created");
                    break;
                case (4):
                    z_figure = new Z_Figure(figure_size * 3, 0);
                    figure = z_figure.getFigure();
                    System.out.println("Figure Z created");
                    break;
                case (5):
                    l_mirrored_figure = new L_Mirrored_Figure(figure_size * 4, 0);
                    figure = l_mirrored_figure.getFigure();
                    System.out.println("Figure Mirrored_L created");
                    break;
                case (6):
                    z_mirrored_figure = new Z_Mirrored_Figure(figure_size * 3, 0);
                    figure = z_mirrored_figure.getFigure();
                    System.out.println("Figure Mirrored_Z created");
                    break;
            }
            playField.figure_switch = (int) (Math.random() * 7);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(PLAY_FIELD_BACKGROUND);
            Graphics2D g2d = (Graphics2D) g;

            if (figure_counter < 1) {
                figure_switcher();
            }

            if (bottom_area != null) {
                g2d.setColor(figure_color);
                g2d.fill(bottom_area);
            }

            g2d.setColor(figure_color);
            g2d.fill(figure);
            playField.requestFocus();
        }
    }

    class NextFigure extends JPanel {
        Area nextFigure;

        void nextFigure_switcher(){
            switch (playField.figure_switch) {
                case (0):
                    l_figure = new L_Figure();
                    nextFigure = l_figure.getFigure();
                    break;
                case (1):
                    o_figure = new O_Figure();
                    nextFigure = o_figure.getFigure();
                    break;
                case (2):
                    y_figure = new Y_Figure();
                    nextFigure = y_figure.getFigure();
                    break;
                case (3):
                    i_figure = new I_Figure();
                    nextFigure = i_figure.getFigure();
                    break;
                case (4):
                    z_figure = new Z_Figure();
                    nextFigure = z_figure.getFigure();
                    break;
                case (5):
                    l_mirrored_figure = new L_Mirrored_Figure();
                    nextFigure = l_mirrored_figure.getFigure();
                    break;
                case (6):
                    z_mirrored_figure = new Z_Mirrored_Figure();
                    nextFigure = z_mirrored_figure.getFigure();
                    break;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            nextFigure_switcher();
            g2d.setColor(figure_color);
            g2d.fill(nextFigure);
        }
    }


    class L_Figure {
        int x, y;

        public L_Figure() {
            x = 0;
            y = 0;
        }

        public L_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (2 * figure_size), figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + (2 * figure_size), figure_size, figure_size)));
            return a;
        }
    }

    class L_Mirrored_Figure {
        int x, y;

        public L_Mirrored_Figure() {
            x = 0;
            y = 0;
        }

        public L_Mirrored_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x + figure_size, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + (2 * figure_size), figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (2 * figure_size), figure_size, figure_size)));
            return a;
        }
    }

    class O_Figure {
        int x, y;

        public O_Figure() {
            x = 0;
            y = 0;
        }

        public O_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            return a;
        }
    }

    class Y_Figure {
        int x, y;

        public Y_Figure() {
            x = 0;
            y = 0;
        }

        public Y_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + (2 * figure_size), y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            return a;
        }
    }

    class I_Figure {
        int x, y;

        public I_Figure() {
            x = 0;
            y = 0;
        }

        public I_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (2 * figure_size), figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (3 * figure_size), figure_size, figure_size)));
            return a;
        }
    }

    class Z_Figure {
        int x, y;

        public Z_Figure() {
            x = 0;
            y = 0;
        }

        public Z_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + (2 * figure_size), y + figure_size, figure_size, figure_size)));
            return a;
        }
    }

    class Z_Mirrored_Figure {
        int x, y;

        public Z_Mirrored_Figure() {
            x = 0;
            y = 0;
        }

        public Z_Mirrored_Figure(int x_pos, int y_pos) {
            figure_counter++;
            x = x_pos;
            y = y_pos;
        }

        Area getFigure() {
            Area a = new Area(new Rectangle(x, y + figure_size, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + (2 * figure_size), y, figure_size, figure_size)));
            return a;
        }
    }
}
