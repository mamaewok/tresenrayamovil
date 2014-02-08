package com.example.tresenrayamovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class TresEnRayaActivity extends Activity implements View.OnClickListener {

	RadioButton rb1;
	RadioButton rb2;
	Button jugar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tresenraya_activity);

		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		System.out.println("Creo radio button");
		jugar = (Button) findViewById(R.id.buttonJugar);
		System.out.println("Creo boton jugar");
		jugar.setOnClickListener(this);
		System.out.println("Añado listener");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		PanelJuegoActivity p = new PanelJuegoActivity();
		if (rb1.isChecked() == true) {
			p.setTipoJuego(true);
			startActivity(new Intent(
					"com.example.tresenrayamovil.PanelJuegoActivity"));
		} else if (rb2.isChecked() == true) {
			p.setTipoJuego(true);
			startActivity(new Intent(
					"com.example.tresenrayamovil.PanelJuegoActivity"));
		}
	}

	// TODO Auto-generated method stub

}
