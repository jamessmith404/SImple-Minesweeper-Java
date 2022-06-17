import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Cell extends JButton {
    private int cellType; // 0 = empty cell, 1 = mine cell, 2 = number cell
    private int position;
    private boolean revealed;
    private boolean flagged; // can't be clicked if this true
    private Handler handler;

    public static final int EMPTYCELL = 0, MINECELL = 1, NUMBERCELL = 2;

    public Cell(int cellType, int position, boolean revealed, boolean flagged, Handler handler) {
        this.cellType = cellType;
        this.position = position;
        this.revealed = revealed;
        this.flagged = flagged;
        this.handler = handler;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * Method for handling the button that (left-) clicked
     */
    public void clickButton() {
        handler.click(this);
    }

    /**
     * Method for handling the button that right-clicked
     */
    public void rightClickButton() {
        handler.rightClick(this);
    }


    /**
     * 0 = empty cell, 1 = mine cell, 2 = number cell
     * @return number based on the type of cell
     */
    public int getCellType() {
        return cellType;
    }

    public int getPosition() {
        return position;
    }

    public boolean isRevealed() {
        return revealed;
    }
    public void setRevealed(boolean newRevealed) {
        this.revealed = newRevealed;
    }

    public boolean isFlagged() {
        return flagged;
    }
    public void setFlagged(boolean newFlag) {
        this.flagged = newFlag;
    }
}
