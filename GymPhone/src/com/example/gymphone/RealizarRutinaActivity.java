package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RealizarRutinaActivity extends Activity implements OnClickListener {
	private DataSourceService ejercicio_db;
	Ejercicios esta_pagina;
	Button iniciar;
	Button detener;
	ImageButton atras;
	ImageButton adelante;
	Chronometer cronometro;
	TextView titulo;
	TextView instrucciones;
	Ejercicios Ejercicio_aqui;
	int posicion;
	ListView lista;
	String Name_ejercicio;
	List<Lista_ejercicios> listaEjercicios;
	int idRutinaSelec;
	
	long time = 0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realizarrutina);
		
		Bundle ejercicio = getIntent().getExtras();
		posicion = ejercicio.getInt("pos");
		idRutinaSelec = ejercicio.getInt("rutina_id");		
		
		// Toast.makeText(getApplication(),"ITEM "+Name_ejercicio, Toast.LENGTH_LONG).show();
		
		
		
		
		ejercicio_db = new DataSourceService(this);
		ejercicio_db.open();
		listaEjercicios = ejercicio_db.getListaEjercicios(idRutinaSelec);
		adelante = (ImageButton)findViewById(R.id.Boton_adelante);
		atras = (ImageButton)findViewById(R.id.Boton_atras);
		if(posicion == 0)
		{				
			atras.setVisibility(View.GONE);
		}
		if(posicion == listaEjercicios.size()-1)
		{			
			adelante.setVisibility(View.GONE);
		}
		Name_ejercicio = listaEjercicios.get(posicion).getejercicio();
		esta_pagina = ejercicio_db.getEjercicio(Name_ejercicio);
		ejercicio_db.close();		
		
		titulo = (TextView) findViewById(R.id.Title_ejercicioR);
		titulo.setText(Name_ejercicio);
		iniciar= (Button)findViewById(R.id.botoninicioR);
		detener= (Button)findViewById(R.id.botondetenerR);
		cronometro=(Chronometer)findViewById(R.id.chronometer1R);
		iniciar.setOnClickListener(this);
		detener.setOnClickListener(this);
		
		instrucciones = (TextView) findViewById(R.id.instruccionesR);
		final String text = esta_pagina.getinstrucciones();
		instrucciones.setText(text);
		int imagen_cont =  (int) esta_pagina.getid();		
		imagen(imagen_cont); 
	}
	
	
	public void irsiguiente(View v){
		Intent irRealizarRutina = new Intent(this, RealizarRutinaActivity.class);
		 irRealizarRutina.putExtra("pos", posicion+1);
		 irRealizarRutina.putExtra("rutina_id", idRutinaSelec);
		 startActivity(irRealizarRutina);
		 finish();
	}
	
	public void iratras(View v){
		Intent irRealizarRutina = new Intent(this, RealizarRutinaActivity.class);
		 irRealizarRutina.putExtra("pos", posicion-1);
		 irRealizarRutina.putExtra("rutina_id", idRutinaSelec);
		 startActivity(irRealizarRutina);
		 finish();
	}

	public void imagen(int x){
		ImageView img= (ImageView)findViewById(R.id.cargarimagenR);

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
		
		case R.id.botoninicioR:
			cronometro.setBase(SystemClock.elapsedRealtime()+time);
			cronometro.start();
			break;
		case R.id.botondetenerR:
			time=cronometro.getBase()-SystemClock.elapsedRealtime();
			//String chronoText = cronometro.getText().toString();
			//String array[] = chronoText.split(":");		
			//String time1 = -1*(time/1000)+"";
			//int calorias = (int) (time * 0.0002);
			//calorias = calorias * -1;
			
			//Toast.makeText(getApplication(),"Tiempo "+time1, Toast.LENGTH_LONG).show();
			//long id_rutina = listaEjercicios.get(posicion).getid_lista();
			
			//ejercicio_db.open();
			//esta_pagina = ejercicio_db.actualizarEjercicio(id_rutina, time1, calorias);
			//ejercicio_db.close();
			
			cronometro.stop();
			break;
		}
	
	}
}

