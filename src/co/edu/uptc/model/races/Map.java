package co.edu.uptc.model.races;

import java.awt.Color;

public class Map {
	private int[] size = new int[2];
	private int laneBorders = 10; // filas
	private int laneAmount = 10;
	private Color color;
	private int[][] board;

	public Map() {
		generateBoard();
		this.color = Color.BLACK;

	}

	public void generateBoard() {
		this.board = new int[laneBorders][laneAmount];
	}

	// este metodo es el que va a llamar el presneter
	public void moveBoard() {

		// bueno en este for se introduce la variable fila la cual se inicializa con el
		// valor del tablero-1 osea
		// si el tablero tiene 5 posiciones se le resta 1 para que recorra[ 4,3,2,1,0]
		// ahora eso se va hacer si la fila sea mayor a 0
		// y se le va restando al valor de row

		for (int row = board.length - 1; row > 0; row--) {
			board[row] = board[row - 1]; // aca el valor de la fila[row] se le asigna la fila row-1
			// entonces lo que era row va a hacer la copia de row -1
		}

		// generar una nueva fila arriba vacia, o con obstaculos
		board[0] = new int[board[0].length]; // fila nueva, todo en 0 de nuevo para crear el efecto de una matriz
												// infinita
	}
	


	public int[] getSize() {
		return size;
	}

	public void setSize(int[] size) {
		this.size = size;
	}

	public int getLaneAmount() {
		return laneAmount;
	}

	public void setLaneAmount(int laneAmount) {
		this.laneAmount = laneAmount;
	}

	public int getLaneBorders() {
		return laneBorders;
	}

	public void setLaneBorders(int laneBorders) {
		this.laneBorders = laneBorders;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

}
