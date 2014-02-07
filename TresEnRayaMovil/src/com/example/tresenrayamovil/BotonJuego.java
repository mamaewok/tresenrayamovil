package com.example.tresenrayamovil;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * Implementa las coordenadas que van a tener cada botón que ponemos en el tablero para su posterior comprobación
 * @author MAMAEWOK
 *
 */
public class BotonJuego extends Button {

	private int y; // coordenadas del boton en vwrtical
	private int x; // coordenadas del boton en horizontal
	
	/**
	 * Crea las coordenadas de cada botón al instanciarse
	 * @param y
	 * @param x
	 */
	public BotonJuego(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTextColor(Color.DKGRAY);
	
	}
	// returns las coordenadas
	public int getCoordenadaY() {
		return y;
	}

	public int getCoordenadaX() {
		return x;
	}
	
	// setters
	public void setXY(int x, int y) {
		this.y = y;
		this.x = x;
	}

}
