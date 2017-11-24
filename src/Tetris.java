import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;

/**
 * It`s my first Java project - Tetris game
 *
 * Created by Remar on 21.11.2017.
 */
public class Tetris extends JFrame {
    public int PLAY_FIELD_WIDTH = 250;
    public int PLAY_FIELD_HEIGHT = PLAY_FIELD_WIDTH * 2;
    int figure_counter;
    int figure_size = PLAY_FIELD_WIDTH / 10;
    int x, y;
    PlayField playField;
    L_Figure l_figure;
    O_Figure o_figure;
    Y_Figure y_figure;
    I_Figure i_figure;
    Z_Figure z_figure;



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
            JLabel nextFig_label = (JLabel) add(new JLabel("Next Figure: "));
            add(new JLabel("FIGURE"));
            JLabel score_label = (JLabel) add(new JLabel("Score: "));
            add(new JLabel("SCORE"));
            JLabel timer_label = (JLabel) add(new JLabel("Timer: "));
            add(new JLabel("TIMER"));
            JButton start_btn = (JButton) add(new JButton("Start"));
            JButton pause_btn = (JButton) add(new JButton("Pause"));
        }
    }

    class PlayField extends JPanel {
        Color PLAY_FIELD_BACKGROUND = Color.CYAN;
        Area bottom_area = new Area(new Rectangle(0, PLAY_FIELD_HEIGHT, PLAY_FIELD_WIDTH, PLAY_FIELD_WIDTH / 10));


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(PLAY_FIELD_BACKGROUND);
            Graphics2D g2d = (Graphics2D) g;

            while (figure_counter < 1) {
                int random = (int) (Math.random() * 5);
                switch (random) {
                    case (0):
                        l_figure = new L_Figure(figure_size * 4, -figure_size * 3);
                        l_figure.paint(g2d);
                        break;
                    case (1):
                        o_figure = new O_Figure();
                        o_figure.paint(g2d);
                        break;
                    case (2):
                        y_figure = new Y_Figure();
                        y_figure.paint(g2d);
                        break;
                    case (3):
                        i_figure = new I_Figure();
                        i_figure.paint(g2d);
                        break;
                    case (4):
                        z_figure = new Z_Figure();
                        z_figure.paint(g2d);
                        break;
                }
            }
        }
    }

    void update(){

    }

    class L_Figure {
        Color color = Color.RED;

        public L_Figure(){
            figure_counter++;
        }

        public L_Figure(int x_pos, int y_pos) {
            this();
            x = x_pos;
            y = y_pos;
        }

        public L_Figure(int x_pos, int y_pos, Color color) {
            this(x_pos, y_pos);
            this.color = color;
        }

        public void paint(Graphics2D g2d){
            g2d.setColor(color);
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (2 * figure_size), figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + (2 * figure_size), figure_size, figure_size)));
            g2d.fill(a);
            g2d.setColor(Color.BLACK);
            g2d.draw(a);
        }
    }

    class O_Figure {
        Color color = Color.RED;

        public O_Figure(){
            figure_counter++;
        }

        public O_Figure(int x_pos, int y_pos){
            this();
            x = x_pos;
            y = y_pos;
        }

        public O_Figure(int x_pos, int y_pos, Color color){
            this(x_pos, y_pos);
            this.color = color;
        }

        public void paint(Graphics2D g2d){
            g2d.setColor(color);
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            g2d.fill(a);
            g2d.setColor(Color.BLACK);
            g2d.draw(a);
        }
    }

    class Y_Figure {
        Color color = Color.RED;

        public Y_Figure(){
            figure_counter++;
        }

        public Y_Figure(int x_pos, int y_pos){
            this();
            x = x_pos;
            y = y_pos;
        }

        public Y_Figure(int x_pos, int y_pos, Color color){
            this(x_pos, y_pos);
            this.color = color;
        }

        public void paint(Graphics2D g2d){
            g2d.setColor(color);
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + (2 * figure_size), y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            g2d.fill(a);
            g2d.setColor(Color.BLACK);
            g2d.draw(a);
        }
    }

    class I_Figure {
        Color color = Color.RED;

        public I_Figure(){
            figure_counter++;
        }

        public I_Figure(int x_pos, int y_pos){
            this();
            x = x_pos;
            y = y_pos;
        }

        public I_Figure(int x_pos, int y_pos, Color color){
            this(x_pos, y_pos);
            this.color = color;
        }

        public void paint(Graphics2D g2d){
            g2d.setColor(color);
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (2 * figure_size), figure_size, figure_size)));
            a.add(new Area(new Rectangle(x, y + (3 * figure_size), figure_size, figure_size)));
            g2d.fill(a);
            g2d.setColor(Color.BLACK);
            g2d.draw(a);
        }
    }

    class Z_Figure {
        Color color = Color.RED;

        public Z_Figure(){
            figure_counter++;
        }

        public Z_Figure(int x_pos, int y_pos){
            this();
            x = x_pos;
            y = y_pos;
        }

        public Z_Figure(int x_pos, int y_pos, Color color){
            this(x_pos, y_pos);
            this.color = color;
        }

        public void paint(Graphics2D g2d){
            g2d.setColor(color);
            Area a = new Area(new Rectangle(x, y, figure_size, figure_size));
            a.add(new Area(new Rectangle(x + figure_size, y, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + figure_size, y + figure_size, figure_size, figure_size)));
            a.add(new Area(new Rectangle(x + (2 * figure_size), y + figure_size, figure_size, figure_size)));
            g2d.fill(a);
            g2d.setColor(Color.BLACK);
            g2d.draw(a);
        }
    }
}