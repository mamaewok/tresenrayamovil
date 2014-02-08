package com.example.tresenrayamovil;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Crea el panel con los botones y añade la funcionalidad básica dejuego
 * @author MAMAEWOK
 *
 */
public class PanelJuegoActivity extends Activity implements View.OnClickListener{

	// Constantes
	public static final String JUGADOR1 = "X";
	public static final String JUGADOR2 = "O";
//	public static final ImageButton JUGADOR1_ICON = new ImageButton(context);
//	public static final ImageButton JUGADOR2_ICON = new ImageButton(context);
	
	// Declaro mis atributos
	
	// matriz que rellenaremos con las teclas de tipo BotonJuego
	private BotonJuego[][] panelJuego;
	//mensaje que cambiaremos según gane un jugador u otro, o se produzca un empate
	private TextView mensaje; 
	// acumula mov. y cuando llega a 9 sabe que el juego ha terminado
	private int movimientos; 
	// true=turno1; false=turno2
	private boolean turno; 
	//Sirve para decir si esta el panel de juego bloqueado
	private boolean bloqueado; 
	//guarda todos los botones de mi tablero y los borra según se usan
	private ArrayList<BotonJuego> posibilidades; 
	//dice el tipo de juego en el que nos encontramos
	private boolean tipoJuego; 
	//botones de juego
	private BotonJuego btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	//Boton de reinicio y para volver 
	private Button reiniciar, atras;
	/**
	 * Crea el panel de juego e inicializa los atributos
	 * 
	 * @param mensaje emnsaje que irá cambiando según las circunstancias
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paneldejuego_activity);
		
		panelJuego = new BotonJuego[3][3];
		posibilidades = new ArrayList<BotonJuego>();
		movimientos = 0;
		turno = true;
		bloqueado = false;
		
		mensaje = (TextView)findViewById(R.id.textView1);
		reiniciar = (Button)findViewById(R.id.BtnReinicio);
		atras = (Button)findViewById(R.id.BtnAtras);
		
		reiniciar.setOnClickListener(this);
		atras.setOnClickListener(this);
		
		btn1 = (BotonJuego)findViewById(R.id.Btn1_1);
		btn2 = (BotonJuego)findViewById(R.id.Btn1_2);
		btn3 = (BotonJuego)findViewById(R.id.Btn1_3);
		btn4 = (BotonJuego)findViewById(R.id.Btn2_1);
		btn5 = (BotonJuego)findViewById(R.id.Btn2_2);
		btn6 = (BotonJuego)findViewById(R.id.Btn2_3);
		btn7 = (BotonJuego)findViewById(R.id.Btn3_1);
		btn8 = (BotonJuego)findViewById(R.id.Btn3_2);
		btn9 = (BotonJuego)findViewById(R.id.Btn3_3);
		
		btn1.setXY(0, 0);
		btn2.setXY(1, 0); 
		btn3.setXY(2, 0);
		btn4.setXY(0, 1);
		btn5.setXY(1, 1);
		btn6.setXY(2, 1);
		btn7.setXY(0, 2);
		btn8.setXY(1, 2);
		btn9.setXY(2, 2);
		
		panelJuego[0][0] = btn1;
		panelJuego[1][0] = btn2;
		panelJuego[2][0] = btn3;
		panelJuego[0][1] = btn4;
		panelJuego[1][1] = btn5;
		panelJuego[2][1] = btn6;
		panelJuego[0][2] = btn7;
		panelJuego[1][2] = btn8;
		panelJuego[2][2] = btn9;
		
		bloquear();
		
		for (int x = 0; x < panelJuego.length; x++) {
			for (int y = 0; y < panelJuego[x].length; y++) {
				panelJuego[x][y].setOnClickListener(this);
				posibilidades.add(panelJuego[x][y]);
			}
		}
	}

		

	// Métodos.
	/**
	 * Establece true para Humano vs Ordenador o false para Humano vs Humano
	 * 
	 * @param tipoJuego
	 */
	public void setTipoJuego(boolean tipoJuego) {
		this.tipoJuego = tipoJuego;
	}

	/**
	 * Establece el símbolo según si el turno esta en true(X) o false(O)
	 * 
	 * @param botonJuego
	 */
	private void ponerSimbolo(BotonJuego botonJuego) {
		if (turno == true) {
			botonJuego.setText(JUGADOR1);
			if (tipoJuego == false)
				mensaje.setText("Turno de JUGADOR 1");
			// botonJuego.setIcon(JUGADOR1_ICON); --> Debería establecer el
			// icono como imagen pero no lo hace :(
			turno = false;
		} else {
			botonJuego.setText(JUGADOR2);
			if (tipoJuego == false)
				mensaje.setText("Turno de JUGADOR 2");
			// botonJuego.setIcon(JUGADOR2_ICON);
			turno = true;
		}
	}

	/**
	 * Reinicia el tablero vaciando las posibilidades que quedaran, rellenandolas de nuevo con los botones sin tocar, reiniciando los movimientos
	 * y bloqueando las teclas hasta que se acepte un nuevo tipo de juego
	 */
	public void reiniciar() {
		posibilidades.removeAll(posibilidades);
		//El error se encontraba en que vaciaba las posibilidades pero no volvia a llenarlas con los posibles botones. Así ya funciona!! :D:D
		for (int x = 0; x < panelJuego.length; x++) {  
			for (int y = 0; y < panelJuego[x].length; y++) {
				posibilidades.add(panelJuego[x][y]);
			}
		}
		movimientos = 0;
		bloquear();
	}

	/**
	 * Desbloquea el tablero de juego 
	 */
	public void desbloquear() {
		for (int x = 0; x < panelJuego.length; x++) {
			for (int y = 0; y < panelJuego[x].length; y++) {
				panelJuego[x][y].setText("");
				panelJuego[x][y].setEnabled(true);
			}
		}
		bloqueado = false;
	}

	/**
	 * Bloquea el tablero de juego 
	 */
	private void bloquear() {
		for (int x = 0; x < panelJuego.length; x++) {
			for (int y = 0; y < panelJuego[x].length; y++) {
				panelJuego[x][y].setText("");
				panelJuego[x][y].setEnabled(false);
			}
		}
		bloqueado = true;
	}

	/**
	 * Calcula el movimiento que realizará el ordenador de manera aleatoria
	 * @return un boton elegido de manera aleatoria entre las posibilidades que queden todavia
	 */
	private BotonJuego calcularMovimiento() {
		int mov = (int) Math.random() * (posibilidades.size());
		return (BotonJuego) posibilidades.get(mov);
	}

	/**
	 * Comprueba la matriz si gana o no el boton introducido como parámetro
	 * @param botonJuego
	 * @return true si el boton gana o false si pierde
	 */
	private boolean comprobarGanador(BotonJuego botonJuego) {
		int y = botonJuego.getCoordenadaY();
		int x = botonJuego.getCoordenadaX();
		String simbolo = botonJuego.getText().toString();

		if (y == 0) {
			if (x == 0) {
				// Horizontal
				if (panelJuego[y][x + 1].getText().equals(simbolo)
						&& panelJuego[y][x + 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y + 1][x].getText().equals(simbolo)
						&& panelJuego[y + 2][x].getText().equals(simbolo))
					return true;
				// Diagonal
				if (panelJuego[y + 1][x + 1].getText().equals(simbolo)
						&& panelJuego[y + 2][x + 2].getText().equals(simbolo))
					return true;
			} else if (x == 2) {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x - 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y + 1][x].getText().equals(simbolo)
						&& panelJuego[y + 2][x].getText().equals(simbolo))
					return true;
				// Diagonal
				if (panelJuego[y + 1][x - 1].getText().equals(simbolo)
						&& panelJuego[y + 2][x - 2].getText().equals(simbolo))
					return true;
			} else {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x + 1].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y + 1][x].getText().equals(simbolo)
						&& panelJuego[y + 2][x].getText().equals(simbolo))
					return true;
			}
		} else if (y == 2) {
			if (x == 0) {
				// Horizontal
				if (panelJuego[y][x + 1].getText().equals(simbolo)
						&& panelJuego[y][x + 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y - 2][x].getText().equals(simbolo))
					return true;
				// Diagonal
				if (panelJuego[y - 1][x + 1].getText().equals(simbolo)
						&& panelJuego[y - 2][x + 2].getText().equals(simbolo))
					return true;
			} else if (x == 2) {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x - 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y - 2][x].getText().equals(simbolo))
					return true;
				// Diagonal
				if (panelJuego[y - 1][x - 1].getText().equals(simbolo)
						&& panelJuego[y - 2][x - 2].getText().equals(simbolo))
					return true;
			} else {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x + 1].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y - 2][x].getText().equals(simbolo))
					return true;
			}
		} else {
			if (x == 0) {
				// Horizontal
				if (panelJuego[y][x + 1].getText().equals(simbolo)
						&& panelJuego[y][x + 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y + 1][x].getText().equals(simbolo))
					return true;
			} else if (x == 2) {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x - 2].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y + 1][x].getText().equals(simbolo))
					return true;
			} else {
				// Horizontal
				if (panelJuego[y][x - 1].getText().equals(simbolo)
						&& panelJuego[y][x + 1].getText().equals(simbolo))
					return true;
				// Vertical
				if (panelJuego[y - 1][x].getText().equals(simbolo)
						&& panelJuego[y + 1][x].getText().equals(simbolo))
					return true;
				// Diagonal
				if (panelJuego[y - 1][x - 1].getText().equals(simbolo)
						&& panelJuego[y + 1][x + 1].getText().equals(simbolo))
					return true;
				if (panelJuego[y - 1][x + 1].getText().equals(simbolo)
						&& panelJuego[y + 1][x - 1].getText().equals(simbolo))
					return true;
			}
		}
		return false;
	}

	/**
	 * Gestiona el evento de pulsado de los botones del tablero de juego
	 */
	@Override
	public void onClick(View v) {
		BotonJuego botonJuego = (BotonJuego)findViewById(v.getId());
		if (botonJuego.getText().equals("")) {  
			ponerSimbolo(botonJuego);					//Si el boton esta vacio pon el simbolo correspondiente y suma el movimiento
			movimientos++;
			if (comprobarGanador(botonJuego) == true) {
				mensaje.setText("GANADOR: " + botonJuego.getText());		//Si ese boton gana dilo y reinicia
				reiniciar();
			} else if (movimientos == 9) {									//Si los mov = 9 hay empate y reinicia
				mensaje.setText("EMPATE");
				reiniciar();
			}
			if (tipoJuego == true && !bloqueado) {							//Si se juega HvsO quito la posibilidad del boton pulsado por el H y lo convierto
				posibilidades.remove(botonJuego);							//en boton pulsado por el Ordenador y borro la posilidad del botón del ordenador, pone el simbolo
				botonJuego = calcularMovimiento();							//y suma movimiento
				posibilidades.remove(botonJuego);
				ponerSimbolo(botonJuego);
				movimientos++;
				if (comprobarGanador(botonJuego) == true) {								//Comprueba ganador o empate HvsO
					mensaje.setText("GANADOR: " + botonJuego.getText());
					reiniciar();
				} else if (movimientos == 9) {
					mensaje.setText("Final de juego. Tablas.");
					reiniciar();
				}
			}
		}
		Button boton = (Button)findViewById(v.getId());
		if(boton.getText().equals("Reiniciar")){
			mensaje.setText("");
			reiniciar();
		}
		if(boton.getText().equals("Atrás")){
			finish();
			startActivity(new Intent(
            		"com.example.tresenrayamovil.TresEnRayaActivity"));
		}
	}

}
