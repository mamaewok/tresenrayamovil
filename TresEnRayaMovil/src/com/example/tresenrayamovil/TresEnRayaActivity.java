package com.example.tresenrayamovil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class TresEnRayaActivity extends Activity implements View.OnClickListener{
	
	private RadioButton rb1, rb2;
	private Button jugar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tresenraya_activity);
		
		rb1 = (RadioButton)findViewById(R.id.radioButton1);
		rb2 = (RadioButton)findViewById(R.id.radioButton2);
		
		jugar = (Button)findViewById(R.id.buttonJugar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
