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
    L_Figure l_figure;
    O_Figure o_figure;
    Y_Figure y_figure;
    I_Figure i_figure;
    Z_Figure z_figure;
    Timer time_t;
    Timer game_t;


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
        playField.setPreferredSize(new Dimension(PLAY_FIELD_WIDTH, PLAY_FIELD_HEIGHT));
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
            JLabel nextFig_label = (JLabel) add(new JLabel("FIGURE"));
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
        PlayField(){
            setFocusable(true);
            game_t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(figure.getBounds().y < PLAY_FIELD_HEIGHT - figure.getBounds().height)
                        figure.transform(AffineTransform.getTranslateInstance(0, figure_size));
                    System.out.println("Step down");
                    System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                    playField.repaint();
                }
            });

            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT:
                            if (figure.getBounds().x > 0)
                                playField.figure.transform(AffineTransform.getTranslateInstance(-figure_size, 0));
                            System.out.println("Left pressed");
                            System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                            playField.repaint();
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (figure.getBounds().x < PLAY_FIELD_WIDTH - figure.getBounds().width)
                                playField.figure.transform(AffineTransform.getTranslateInstance(figure_size, 0));
                            System.out.println("Right pressed");
                            System.out.println(figure.getBounds().x + " " + figure.getBounds().y);
                            playField.repaint();
                            break;
                    }
                }
            });

            requestFocus();
        }
        Color PLAY_FIELD_BACKGROUND = Color.CYAN;
        Area bottom_area = new Area(new Rectangle(0, PLAY_FIELD_HEIGHT, PLAY_FIELD_WIDTH, PLAY_FIELD_WIDTH / 10));
        int figure_switch;
        Area figure;

        void figure_switcher(){
            playField.figure_switch = (int) (Math.random() * 5);

            switch (figure_switch) {
                case (0):
                    l_figure = new L_Figure(figure_size * 4, -figure_size * 4);
                    figure = l_figure.getFigure();
                    break;
                case (1):
                    o_figure = new O_Figure(figure_size * 4, -figure_size * 3);
                    figure = o_figure.getFigure();
//                    figure = o_figure.paint(g2d);
                    break;
                case (2):
                    y_figure = new Y_Figure(figure_size * 3, -figure_size * 3);
                    figure = y_figure.getFigure();
//                    figure = y_figure.paint(g2d);
                    break;
                case (3):
                    i_figure = new I_Figure(figure_size * 4, -figure_size * 5);
                    figure = i_figure.getFigure();
//                    figure = i_figure.paint(g2d);
                    break;
                case (4):
                    z_figure = new Z_Figure(figure_size * 3, -figure_size * 3);
                    figure = z_figure.getFigure();
//                    figure = z_figure.paint(g2d);
                    break;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(PLAY_FIELD_BACKGROUND);
            Graphics2D g2d = (Graphics2D) g;

            if (figure_counter < 1) {
                figure_switcher();
            }

            g2d.setColor(Color.RED);
            g2d.fill(figure);
            playField.requestFocus();
        }
    }


    class L_Figure {
        int x, y;

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

    class O_Figure {
        int x, y;

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
}
