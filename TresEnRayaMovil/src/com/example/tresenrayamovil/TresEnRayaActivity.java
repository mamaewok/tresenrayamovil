package com.example.tresenrayamovil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class TresEnRayaActivity extends Activity implements View.OnClickListener {

	private RadioButton rb1;
	private RadioButton rb2;
	private Button jugar, salir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tresenraya_activity);

		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		jugar = (Button) findViewById(R.id.buttonJugar);
		jugar.setOnClickListener(this);
		salir = (Button) findViewById(R.id.buttonSalir);
		salir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View view) {
		if (rb1.isChecked() == true) {
			Intent intent = new Intent("com.example.tresenrayamovil.PanelJuegoActivity");
			intent.putExtra("tipoJuego", true);
			startActivity(intent);
		} else if (rb2.isChecked() == true) {
			Intent intent = new Intent("com.example.tresenrayamovil.PanelJuegoActivity");
			intent.putExtra("tipoJuego", false);
			startActivity(intent);
		}
	} 

}
