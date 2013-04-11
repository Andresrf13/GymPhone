package com.example.gymphone;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TiposRutinasActivity  extends Activity{
	
	ListView listamenu;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rutinas);
		
		datos = new ArrayList<String>();
		llenarlista();
		listamenu = (ListView) findViewById(R.id.listaRutinasGeneral);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listamenu.setAdapter(adaptador);
		
		final Intent irpropias = new Intent(this, RutinasPropiaActivity.class);
		final Intent irsistema = new Intent(this, RutinasSistemaActivity.class);
		
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
					long arg3) {
				
				switch (posicion)
				{
				case 0:
				{					
					startActivity(irsistema);
					break;
				}
				case 1:
				{					
					startActivity(irpropias);
					break;
				}
				
				}
				
			}
			
		});
	}
	
	public void llenarlista(){
		datos.add("Rutinas del Sistema");
		datos.add("Rutinas creadas");
		
	}
}
