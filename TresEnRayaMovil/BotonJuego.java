package tresEnRaya;

import javax.swing.JButton;

/**
 * Implementa las coordenadas que van a tener cada bot�n que ponemos en el tablero para su posterior comprobaci�n
 * @author MAMAEWOK
 *
 */
public class BotonJuego extends JButton {

	private static final long serialVersionUID = 1L;
	private int y; // coordenadas del boton en vwrtical
	private int x; // coordenadas del boton en horizontal
	
	/**
	 * Crea las coordenadas de cada bot�n al instanciarse
	 * @param y
	 * @param x
	 */
	public BotonJuego(int y, int x) {
		this.y = y;
		this.x = x;
	}

	// returns las coordenadas
	public int getCoordenadaY() {
		return y;
	}

	public int getCoordenadaX() {
		return x;
	}

}
