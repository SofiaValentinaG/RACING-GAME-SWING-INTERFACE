package co.edu.uptc.presenter.racer;


import co.edu.uptc.model.races.Game;

import co.edu.uptc.view.races.*;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Presenter implements PresenterInterface{

	private Game game; // referncia a la logica
	private View view; // refernecia a la vista
	private Timer timer; // es un reloj
	private BoardPanel boardPanel;

	public Presenter() {
		game = new Game();
		view = new View(game.getMap().getBoard().length, game.getMap().getBoard()[0].length);
		view.fillBoard(game.getMap().getBoard());
		
		view.addListeners(this);
		
		view.setVisible(true);
		startGame();
	}

	public void setView(View view) {
		this.view = view;
	}
    @Override
	public int[][] getBoard() {
		return game.getMap().getBoard(); // pide el tablero actual que tiene la clase game
	}
    @Override
	public void startGame() {
		timer = new Timer(400, this); // aqui estoy diciendo que dispare el metodo action Perfomed cada 400 mili segundos
		timer.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			game.moveCar("LEFT");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			game.moveCar("RIGHT");
		}
		view.refreshBoard();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.updateBoard();
		view.updateScore(game.getScore());
		view.refreshBoard();
		
	}
	 public void refreshView() {
	        boardPanel.updateBoard(game.getMap().getBoard());
	    }

}