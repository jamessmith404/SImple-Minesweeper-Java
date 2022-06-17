import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel  {

    private int bound = Main.GRIDSIZE * Main.GRIDSIZE; // sum of cells

    private ArrayList<Integer> mines = new ArrayList<Integer>();
    public static ArrayList<Cell> cellGrid = new ArrayList<Cell>(); // holds all of cells inside of it and accessible

    public Grid(GridLayout layout, Handler handler) {
        super(layout);
        generateCells(handler);
        addCells();
    }
    public void generateCells(Handler handler) {
        for(int i = 0; i < Main.GRIDSIZE; i++) {
            int minePosition = (int) (Math.random() * bound); // from index 0-99

            if (!mines.contains(minePosition)) { // if the mine position hasn't picked yet
                mines.add(minePosition);
            } else {
                i--;
            }
        }

        for(int currPosition = 0; currPosition < bound; currPosition++) {
            if (mines.contains(currPosition)) {
                cellGrid.add(new Cell(Cell.MINECELL, currPosition,false, false, handler));
            } else if (currPosition % Main.GRIDSIZE != 0 && currPosition % Main.GRIDSIZE != 9){
                if (mines.contains(currPosition - Main.GRIDSIZE - 1) ||
                        mines.contains(currPosition - Main.GRIDSIZE) ||
                        mines.contains(currPosition - Main.GRIDSIZE + 1) ||
                        mines.contains(currPosition - 1) ||
                        mines.contains(currPosition + 1) ||
                        mines.contains(currPosition + Main.GRIDSIZE - 1) ||
                        mines.contains(currPosition + Main.GRIDSIZE) ||
                        mines.contains(currPosition + Main.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(Cell.NUMBERCELL, currPosition, false, false, handler));
                } else {
                    cellGrid.add(new Cell(Cell.EMPTYCELL, currPosition, false, false, handler));
                }
            } else if (currPosition % Main.GRIDSIZE == 0) { // if the cell is located at the left-end
                if (mines.contains(currPosition - Main.GRIDSIZE) ||
                        mines.contains(currPosition - Main.GRIDSIZE + 1) ||
                        mines.contains(currPosition + 1) ||
                        mines.contains(currPosition + Main.GRIDSIZE) ||
                        mines.contains(currPosition + Main.GRIDSIZE + 1)) {
                    cellGrid.add(new Cell(Cell.NUMBERCELL, currPosition, false, false, handler));
                } else {
                    cellGrid.add(new Cell(Cell.EMPTYCELL, currPosition, false, false, handler));
                }
            } else { // if the cell is located at the right-end
                if (mines.contains(currPosition - Main.GRIDSIZE) ||
                        mines.contains(currPosition - Main.GRIDSIZE - 1) ||
                        mines.contains(currPosition - 1) ||
                        mines.contains(currPosition + Main.GRIDSIZE) ||
                        mines.contains(currPosition + Main.GRIDSIZE - 1)) {
                    cellGrid.add(new Cell(Cell.NUMBERCELL, currPosition, false, false, handler));
                } else {
                    cellGrid.add(new Cell(Cell.EMPTYCELL, currPosition, false, false, handler));
                }
            }
        }
    }

    /**
     * Method for adding cells to Grid Panel
     */
    public void addCells() {
        for (int i = 0; i < cellGrid.size(); i++) {
            add(cellGrid.get(i));
        }
    }
}
