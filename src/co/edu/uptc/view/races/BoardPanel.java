package co.edu.uptc.view.races;



import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BoardPanel extends JPanel {

	private int cellSize;

	private Image imgCar;
	private Image imgObstacle;
	private Image imgReward;
	private int[][] board;
	

	public BoardPanel( int cellSize) {
		this.cellSize = cellSize;

		setBackground(Color.WHITE);// metodo que viene incluido con JPANEL
		loadingImages();
	}

	private void loadingImages() {
		try {
			imgCar = new ImageIcon(getClass().getResource("car.png")).getImage();
			imgObstacle = new ImageIcon(getClass().getResource("obstacle.png")).getImage();
			imgReward = new ImageIcon(getClass().getResource("reward.png")).getImage();
		} catch (Exception e) {
			System.out.println("no se pudieron cargar las imagenes: " + e.getMessage());
		}
	}
	// El Presenter llama a este metodo para actualizar el tablero
	public void updateBoard(int[][] board) {
		this.board = board;
		repaint(); // esto vuelve a llamar paintComponent automáticamente
	}

	@Override
	protected void paintComponent(Graphics g) { // este metodo recibe un objeto de tipo Graphic
		super.paintComponent(g);// super se refiere a la clase padre que trae JPANEL y trae el metodo original escrito en esa clase
		drawingBoard(g);
	}

	private void drawingBoard(Graphics g) {
		if (board == null) return;

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[row].length; column++) { // este for anidado recorre la matrizz

				int x = column * cellSize; // convierte los indeces de la matriz en la posicion de la pantalla en pixeles
				int y = row * cellSize;
				int value = board[row][column];

				Image imagen = null;
				if (value == 2) {
					imagen = imgObstacle;
				}
				if (value == 3) {
					imagen = imgReward;
				}
				if (value== 4) {
					imagen = imgCar;
				}

				if (imagen != null) {
					// entonces aca pasa la imagen el metodo drawImage
					// pasa la posicion donde se va a poner el pincel para dibujar la imagen esto se hace por pixeles
					// y cell size es el tamaño que se va a ver la imagen
					
					g.drawImage(imagen, x, y, cellSize, cellSize, this);
				} else if (value == 2) 
				{
					g.setColor(Color.PINK);
					g.fillRect(x, y, cellSize, cellSize);// aqui dibuja un cuadrado
				} 
				else if (value == 3) {
					g.setColor(Color.MAGENTA);
					g.fillRect(x, y, cellSize, cellSize); // esto es un reward
					
				} else if (value == 4) {
					g.setColor(Color.GREEN);
					g.fillRect(x, y, cellSize, cellSize);
				}

				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(x, y, cellSize, cellSize); // aqui dibuja el contorno del cuadrado
			}
		}
	}

}