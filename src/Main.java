public class Main {
    public static final int WIDTH = 720, HEIGHT = 720;
    public static final int GRIDSIZE = 10;
    public static final int MINECOUNT = (int) Math.round(GRIDSIZE * GRIDSIZE * 0.1);

    private Handler handler = new Handler();
    public Main() {
        new Window(WIDTH, HEIGHT, GRIDSIZE, "Minesweeper",this, handler);
        Window.update(0); // set early flagged cells condition = 0
    }

    public static void main(String[] args) {
        new Main();
    }
}
