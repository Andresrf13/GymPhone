package com.example.gymphone;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RealizarEjercicioActivity extends Activity implements OnClickListener {
	private DataSourceService ejercicio_db;
	Ejercicios esta_pagina;
	Button iniciar;
	Button detener;
	Chronometer cronometro;
	TextView titulo;
	TextView instrucciones;
	
	long time = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realizarejercicio);
		
		Bundle ejercicio = getIntent().getExtras();
		final String name_ejercicio = ejercicio.getString("ejercicio1");
		//Toast.makeText(getApplication(),"ITEM "+name_ejercicio, Toast.LENGTH_LONG).show();
		ejercicio_db = new DataSourceService(this);
		ejercicio_db.open();
		esta_pagina = ejercicio_db.getEjercicio(name_ejercicio);
		ejercicio_db.close();
		
		titulo = (TextView) findViewById(R.id.Title_ejercicio);
		titulo.setText(name_ejercicio);
		iniciar= (Button)findViewById(R.id.botoninicio);
		detener= (Button)findViewById(R.id.botondetener);
		cronometro=(Chronometer)findViewById(R.id.chronometer1);
		iniciar.setOnClickListener(this);
		detener.setOnClickListener(this);
		
		instrucciones = (TextView) findViewById(R.id.instrucciones);
		final String text = esta_pagina.getinstrucciones();
		instrucciones.setText(text);
		int imagen_cont =  (int) esta_pagina.getid();
		//Toast.makeText(getApplication(),"instrucciones "+esta_pagina.getinstrucciones(), Toast.LENGTH_LONG).show();
		imagen(imagen_cont);
	}
	
	
	public void imagen(int x){
		ImageView img= (ImageView)findViewById(R.id.cargarimagen);

		switch(x){
		case 1:		
			img.setImageResource(R.drawable.bicicleta);
			break;
		case 2:
			img.setImageResource(R.drawable.caminadora);
			break;
		case 3:
			img.setImageResource(R.drawable.cross_trainer);
			break;
		case 4:
			img.setImageResource(R.drawable.stepper);
			break;
		case 5:
			img.setImageResource(R.drawable.mancuerna);
			break;
		case 6:
			img.setImageResource(R.drawable.polea);
			break;
		case 7:
			img.setImageResource(R.drawable.polea_invertida);
			break;
		case 8:
			img.setImageResource(R.drawable.barra);
			break;
		case 9:
			img.setImageResource(R.drawable.banco);
			break;
		case 10:
			img.setImageResource(R.drawable.banco_inclinado);
			break;
		case 11:
			img.setImageResource(R.drawable.remo);
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	
	public void onClick(View arg0){
		switch (arg0.getId()){
		
		case R.id.botoninicio:
			cronometro.setBase(SystemClock.elapsedRealtime()+time);
			cronometro.start();
			break;
		case R.id.botondetener:
			time=cronometro.getBase()-SystemClock.elapsedRealtime();
			cronometro.stop();
			break;
		}
	
	}
}
