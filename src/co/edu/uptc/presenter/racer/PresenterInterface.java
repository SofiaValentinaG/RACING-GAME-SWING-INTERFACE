package co.edu.uptc.presenter.racer;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface PresenterInterface extends ActionListener, KeyListener{

	int[][] getBoard();

	void startGame();

}
