package co.edu.uptc.view.races;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class GameView extends JFrame {

	public GameView(String titulo, int width, int height) {

		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// esto es para cerrar la ventana
		setLayout(new BorderLayout());// es para manejar comos se van a poner los elementos por dentro
		setSize(width,height);
		setLocationRelativeTo(null);// esta linea es cuando se abre la ventana y queda en el centro
		setFocusable(true);// es para enfocar el teclado en nuestra ventana
		// aqui se pasa un objeto que se comporte como un keyListener;

	}
	public abstract void initComponents();

}
