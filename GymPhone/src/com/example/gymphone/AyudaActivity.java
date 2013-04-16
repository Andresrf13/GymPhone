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

public class AyudaActivity extends Activity{

	ListView listamenu;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ayuda);
		
		datos = new ArrayList<String>();
		llenarlista();
		listamenu = (ListView) findViewById(R.id.listaAyuda);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listamenu.setAdapter(adaptador);
		final Intent irayudaformat = new Intent(this, AyudaformatActivity.class);
		
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
					long arg3) {
				//Toast.makeText(getApplication(), "Clic en: "+(String)((TextView)vista).getText(),Toast.LENGTH_LONG ).show();				//
				String funcion = (String)((TextView)vista).getText();
				irayudaformat.putExtra("title", funcion);
				startActivity(irayudaformat);
			}
			
		});	
	}
	
	public void llenarlista(){
		datos.add("El perfil");
		datos.add("Hacer un ejercicio");
		datos.add("Hacer una rutina");
		datos.add("Ver el ranking");
		datos.add("Entender Estadisticas");
		datos.add("Actualización");
	}
}
