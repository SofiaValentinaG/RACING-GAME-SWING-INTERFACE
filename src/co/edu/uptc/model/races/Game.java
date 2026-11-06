package co.edu.uptc.model.races;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	private String user;
	private Car car;
	private Map map;
	private List<Obstacle> obstacle;
	private List<Reward> reward;
	private Random random = new Random();

	private int score = 0; // aqui puse una variable que acumule los pusos por pisar un obstaculo o un
							// premmio
	private static final int EMPTY = 0; // atributo estatico que representa una posicion de la matriz vacia, con el
										// codigo 0
	private static final int OBSTACLE = 2;// atributo estatico que representa una pocision ocupada por un obstaculo
	private static final int REWARD = 3; // atributo estatico que representa una posicion ocupada por un premio
	private static final int CAR = 4; // atributo estatico que representa una posicion ocupada por un carro

	public String getUser() {
		return user;
	}
	

	public Game() {
		super();
      map = new Map();
		
		setCar(new Car());

		startCar();
		generateObstacles(5);
		generateRewards(3);
	}


	public void startCar() {
		car.setRow(map.getBoard().length - 1);
		car.setColumn(map.getBoard()[0].length / 2); // empieza en el centro del tablero

	}
	
	
	public int[][] getMatriz() {
		return map.getBoard();
	}

	// ESTE STRING VA A HACER UN CODIGO PARA QUE EL PRESENTER LO TOME Y LO RELACIONE
	// CON LAS TECLAS DE ARRIBA, ABAJO, DERECHA E IZQUIERDA
	public boolean moveCar(String direction) {
		int newRow = car.getRow();
		int newColumn = car.getColumn();
		int[][] board = map.getBoard();

		switch (direction) {

		case "LEFT":
			newColumn--;
			break;

		case "RIGHT":
			newColumn++;
			break;
		}
		// entonces si la linea se sale de la posicion 0 y de la posicion 4 que es la
		// del borde del tablero retorna false
		// y si se sale del limite de las filas tambien retorna false
		boolean salir = newRow < 0 || newRow >= board.length || newColumn < 0 || newColumn >= board[0].length;

		if (salir) {
			return false; // no se mueve, no se toca el tablero
		}

		board[car.getRow()][car.getColumn()] = EMPTY; // aqui le dice que la posicion vieja va estar vacia
		car.setRow(newRow);
		car.setColumn(newColumn); // aqui pone las nuevas posiciones del carro
		board[newRow][newColumn] = CAR; // pone la nueva posicion del carroy retorna true
		return true;

	}

	public void generateObstacles(int quantity) {
		int[][] board = map.getBoard(); // crea una instancia de lo que es hacer board
		List<Obstacle> obstacles = new ArrayList<>(); // crea una lsita nueva de obstacles
		int rows = map.getBoard().length; // accede a cuanstas filas tiene el mapa
		int columns = map.getBoard()[0].length; // accede a la primera fila y dice cuantas columnas tiene

		for (int i = 0; i < quantity; i++) { // entonces por cada obstacle que quiere generar el usuario se crea una
												// instancia dle mismo
			Obstacle o = new Obstacle();
			o.setRow(random.nextInt(rows)); // luego a la fila le agrega un valor random y lo mismo hace con la columna
			o.setColumn(random.nextInt(columns));
			o.setColor(Color.RED);
			o.setTipo("rock");

			if (map.getBoard()[o.getRow()][o.getColumn()] == 0) { // si esta fila y columna se encuentra vacia pone el
																	// obstaculo

				obstacles.add(o);
				board[o.getRow()][o.getColumn()] = OBSTACLE; // luego se le asigna el codigo a la posicion que se va a
																// poner
				// en la matriz
			}

		}
		this.obstacle = obstacles; // aqui le asigna el valor de la lista creada en este metodo a la lista
									// instanciada en la clase

	}

	public void generateRewards(int quantity) {
		int[][] board = map.getBoard();
		List<Reward> rewards = new ArrayList<>();
		int rows = map.getBoard().length;
		int columns = map.getBoard()[0].length;
		for (int i = 0; i < quantity; i++) {
			Reward r = new Reward();
			r.setRow(random.nextInt(rows));// genera numero aleatorio de 0 al tamaño de las filas del mapa
			r.setColumn(random.nextInt(columns));
			r.setColor(Color.BLUE);
			r.setPoints(10);// le cambia el valor de los puntos acumulados

			if (map.getBoard()[r.getRow()][r.getColumn()] == 0) { // si esta fila y columna se encuentra vacia pone el
																	// premio

				rewards.add(r);
				board[r.getRow()][r.getColumn()] = REWARD; // aqui se le asigna un reward a la matriz
			}

		}

		this.reward = rewards;

	}

	private void points() {
		int rowCar = car.getRow();
		int columnCar = car.getColumn();

		Reward rewardFind = null;
		for (Reward r : reward) {
			if (r.getRow() == rowCar && r.getColumn() == columnCar) {
				rewardFind = r;
				break;
			}
		}
		if (rewardFind != null) {
			score += rewardFind.getPoints();
			reward.remove(rewardFind);
		}

		Obstacle obstacleFind = null;
		for (Obstacle o : obstacle) {
			if (o.getRow() == rowCar && o.getColumn() == columnCar) {
				obstacleFind = o;
				break;
			}
		}
		if (obstacleFind != null) {
			score -= 5;
			obstacle.remove(obstacleFind);
		}
	}

	public void updateBoard() {
		map.moveBoard();
		updateObstacles();
		updateRewards();
		map.getBoard()[car.getRow()][car.getColumn()] = CAR;
		points();
		if (random.nextInt(100) < 20) { // llama a generar kobstaculos
	        generateObstacles(1);
	    }
	    if (random.nextInt(100) < 10) { // llama a generar rewards
	        generateRewards(1);
	    }

	}

	private void updateObstacles() {
		for (Obstacle o : obstacle) {
			o.setRow(o.getRow() + 1);
		}
	}

	private void updateRewards() {
		for (Reward r : reward) {
			r.setRow(r.getRow() + 1);
		}
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Obstacle> getObstacle() {
		return obstacle;
	}

	public void setObstacle(List<Obstacle> obstacle) {
		this.obstacle = obstacle;
	}

	public List<Reward> getReward() {
		return reward;
	}

	public void setReward(List<Reward> reward) {
		this.reward = reward;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
