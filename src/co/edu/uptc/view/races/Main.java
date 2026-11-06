package co.edu.uptc.view.races;


import co.edu.uptc.presenter.racer.Presenter;

// implementar interfaz de presnetador y de vista
// utilizar una clase herencia para la vista
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {



		SwingUtilities.invokeLater(() -> { // debe ejecutarse en un hilo especial llamado Event Dispatch Thread, estoe es una expresion lambda
			Presenter presenter = new Presenter();

		});
	}
}