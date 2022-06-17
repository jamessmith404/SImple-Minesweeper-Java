import javax.swing.*;
import java.awt.*;

public class Window {
    private static JFrame frame;
    private static String title;
    private int width, height, gridSize;
    private Main game;
    private Handler handler;

    public Window(int width, int height, int gridSize, String title, Main game, Handler handler) {
        Window.title = title;
        this.width = width;
        this.height = height;
        this.gridSize = gridSize;
        this.game = game;
        this.handler = handler;
        generateFrame();
    }
    public static void update(int flaggedCells) {
        frame.setTitle(String.format("%s Mines: %d - Flags: %d", title, Main.MINECOUNT, flaggedCells));
    }
    public static void updateWin(int totalFlaggedCells) {
        frame.setTitle(String.format("%s Mines: %d - Flags: %d || YOU WIN! ^_^", title, Main.MINECOUNT, totalFlaggedCells));
    }
    public static void updateLose(int totalFlaggedCells) {
        frame.setTitle(String.format("%s Mines: %d - Flags: %d || YOU LOSE :(", title, Main.MINECOUNT, totalFlaggedCells));
    }
    private void generateFrame() {
        Window.frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // centering JFrame on the screen

        JPanel cellPanel = new Grid(new GridLayout(gridSize, gridSize), handler);

        frame.setContentPane(cellPanel); // add button Panel to Window
        frame.pack();

        frame.setVisible(true);
    }
}

