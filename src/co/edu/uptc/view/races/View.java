package co.edu.uptc.view.races;

import co.edu.uptc.presenter.racer.Presenter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;

public class View extends GameView implements ViewInterface {

	private BoardPanel boardPanel;
	private JLabel scoreLabel;

	private static final int CELL_SIZE = 40;
	
	
	public View(int rows, int columns) {
		super("Racing Game", columns * CELL_SIZE, rows * CELL_SIZE + 40);
		initComponents();
	}

	
	@Override
	public void refreshBoard() {
		boardPanel.repaint();
	}

	@Override
	public void updateScore(int puntos) {
		scoreLabel.setText("Puntos: " + puntos);
	}

	@Override
	public void addListeners(KeyListener listener) {
		addKeyListener(listener);
	}

	@Override
	public void fillBoard(int[][] board) {
		boardPanel.updateBoard(board);
	}

	@Override
	public void initComponents() {
		boardPanel = new BoardPanel(CELL_SIZE);
		add(boardPanel, BorderLayout.CENTER);

		scoreLabel = new JLabel("Puntos: 0");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(scoreLabel, BorderLayout.SOUTH);
		
	}

}