package co.edu.uptc.view.races;

import java.awt.event.KeyListener;

public interface ViewInterface {

    void fillBoard(int[][] board);

    void refreshBoard();

    void updateScore(int puntos);

    void addListeners(KeyListener listener);

    void setVisible(boolean visible);

}