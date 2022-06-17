import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Handler {
    private ArrayList<Cell> current = new ArrayList<Cell>();
    private ArrayList<Cell> queue = new ArrayList<Cell>();


    private static int flaggedCells = 0;


    public void click(Cell cell){
        int revealedCells = 0;

        if (!cell.isFlagged()) { // if the cell is not flagged
            cell.setEnabled(false);
            cell.setRevealed(true);

            int position = cell.getPosition();
            boolean win = true;

            if (cell.getCellType() == Cell.EMPTYCELL) { // if the cell is a empty cell
                if (position < Main.GRIDSIZE) { // if the cell is located at top-end
                    if (position % Main.GRIDSIZE == 0) { // if the cell is located at top-left corner
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE + 1));
                        queue.add(Grid.cellGrid.get(position + 1));
                    } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) { // if the cell is located at top-right corner
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE - 1));
                        queue.add(Grid.cellGrid.get(position - 1));
                    } else {
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE - 1));
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE + 1));
                        queue.add(Grid.cellGrid.get(position + 1));
                        queue.add(Grid.cellGrid.get(position - 1));
                    }
                } else if (position >= (Main.GRIDSIZE * (Main.GRIDSIZE - 1))) { // if the cell is located at bottom-end
                    if (position % Main.GRIDSIZE == 0) { // if the cell is located at bottom-left corner
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE + 1));
                        queue.add(Grid.cellGrid.get(position + 1));
                    } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) { // if the cell is located at bottom-right corner
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE - 1));
                        queue.add(Grid.cellGrid.get(position - 1));
                    } else {
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE - 1));
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                        queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE + 1));
                        queue.add(Grid.cellGrid.get(position + 1));
                        queue.add(Grid.cellGrid.get(position - 1));
                    }
                } else if (position % Main.GRIDSIZE == 0) { // if the cell is located at left-end
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE + 1));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE + 1));
                    queue.add(Grid.cellGrid.get(position + 1));
                } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) { // if the cell is located at right-end
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE - 1));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE - 1));
                    queue.add(Grid.cellGrid.get(position - 1));
                } else { // if the cell is located neither at side-end nor corner
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE - 1));
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position - Main.GRIDSIZE + 1));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE - 1));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE));
                    queue.add(Grid.cellGrid.get(position + Main.GRIDSIZE + 1));
                    queue.add(Grid.cellGrid.get(position - 1));
                    queue.add(Grid.cellGrid.get(position + 1));
                }

            } else if (cell.getCellType() == Cell.NUMBERCELL) { // if the cell is a number cell
                int adjMineCount = 0;

                if (position < Main.GRIDSIZE) { // if the cell is located at top-end
                    if (position % Main.GRIDSIZE == 0) { // if the cell is located at top-left corner
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) {// if the cell is located at top-right corner
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    } else {
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    }
                } else if (position >= (Main.GRIDSIZE * (Main.GRIDSIZE - 1))) { // if the cell is located at bottom-end
                    if (position % Main.GRIDSIZE == 0) { // if the cell is located at bottom-left corner
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) {// if the cell is located at bottom-right corner
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    } else {
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                        if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    }
                } else if (position % Main.GRIDSIZE == 0) { // if the cell is located at left-end
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                } else if (position % Main.GRIDSIZE == (Main.GRIDSIZE - 1)) { // if the cell is located at right-end
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                } else { // if the cell is located neither at side-end nor corner
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + Main.GRIDSIZE + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position - 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                    if (Grid.cellGrid.get(position + 1).getCellType() == Cell.MINECELL) {adjMineCount++;}
                }
                cell.setText(String.valueOf(adjMineCount)); // set the text to the number cell (button)
                cell.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

            } else if (cell.getCellType() == Cell.MINECELL){ // if the cell is a mine cell
                win = false;

                for (int i = 0; i < Grid.cellGrid.size(); i ++) {
                    Grid.cellGrid.get(i).setEnabled(false); // disabling all button
                    Grid.cellGrid.get(i).setText("");
                    if (Grid.cellGrid.get(i).getCellType() == Cell.MINECELL) { // showing all the mines
                        try {
                            Image img = ImageIO.read(getClass().getResource("resources/bomb.png")); // relative path
                            Grid.cellGrid.get(i).setIcon(new ImageIcon(img.getScaledInstance(25,25, Image.SCALE_SMOOTH)));
                            // to remote the spacing between the image and button's borders
                            Grid.cellGrid.get(i).setMargin(new Insets(50, 50, 50, 50));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
                cell.setBackground(Color.orange); // the cell that clicked on (is the mine cell)
                cell.setFont(new Font("Helvetica", Font.BOLD, 18));
                Window.updateLose(flaggedCells);
            }

            // Iterate through queue ArrayList for checking the adjacent clicked cell
            for (int i = 0; i < queue.size(); i++) {
                if(!queue.get(i).isRevealed()) { // if the cell is not yet revealed
                    current.add(queue.get(i));
                    queue.get(i).setRevealed(true);
                }
            }
            queue.clear(); // reset the queue content

            // Checking the actual cells in panel
            while (!current.isEmpty()) {
                Cell tempCell = current.get(0);
                current.remove(0);
                tempCell.clickButton();
            }

            // counting revealed cells
            for(Cell eachCell : Grid.cellGrid) {
                if (eachCell.isRevealed()) {
                    revealedCells++;
                }
            }

            // Checking if the user win the game
            if (win && (revealedCells == (Grid.cellGrid.size() - Main.MINECOUNT))) {
                for (int i = 0; i < Grid.cellGrid.size(); i++) {
                    if (Grid.cellGrid.get(i).getCellType() == Cell.MINECELL) {
                        try {
                            Image img = ImageIO.read(getClass().getResource("resources/bomb.png")); // relative path
                            Grid.cellGrid.get(i).setIcon(new ImageIcon(img.getScaledInstance(25,25, Image.SCALE_SMOOTH)));
                            // to remote the spacing between the image and button's borders
                            Grid.cellGrid.get(i).setMargin(new Insets(50, 50, 50, 50));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        Grid.cellGrid.get(i).setEnabled(false); // disable the cell
                    } else {
                        Grid.cellGrid.get(i).setEnabled(false); // disable the cell
                        Grid.cellGrid.get(i).setText(":D");
                        Grid.cellGrid.get(i).setFont(new Font("Helvetica", Font.BOLD, 20));
                    }
                }
                Window.updateWin(flaggedCells);
            }
        }
    }
    public void rightClick(Cell cell) {
        if (!cell.isRevealed()){
            if (!cell.isFlagged()) { // to flag
                cell.setFlagged(true);
                try {
                    Image img = ImageIO.read(getClass().getResource("resources/flag.png")); // relative path
                    cell.setIcon(new ImageIcon(img.getScaledInstance(25,25, Image.SCALE_SMOOTH)));
                    // to remote the spacing between the image and button's borders
                    cell.setMargin(new Insets(50, 50, 50, 50));
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                Window.update(++flaggedCells);
            } else { // to un-flag
                cell.setFlagged(false);
                cell.setIcon(null);
                cell.setText("");

                Window.update(--flaggedCells);
            }
        }
    }
}
