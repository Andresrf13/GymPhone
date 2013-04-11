package com.example.gymphone;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class RankingActivity extends Activity {
	GridView listamenu;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
		datos = new ArrayList<String>();
		llenarlista();
		listamenu = (GridView) findViewById(R.id.gridPosiciones);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, datos);
		listamenu.setAdapter(adaptador);
		
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
					long arg3) {
				Toast.makeText(getApplication(), "Clic en: "+(String)((TextView)vista).getText(),Toast.LENGTH_LONG ).show();				// 
				
			}
			
		});	
	}
	
	public void llenarlista(){
		datos.add("1");
		datos.add("Solo Fuerza");
		datos.add("80%");
		datos.add("2");
		datos.add("Ritmo Lento");
		datos.add("30%");
	}
}
