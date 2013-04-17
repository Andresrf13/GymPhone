package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class MenuEjerciciosActivity extends Activity {	
	private DataSourceService menu_ejercicios;
	ListView listafuerza;
	ListView listacardio;
	ArrayAdapter<String> adaptador;
	ArrayAdapter<String> adaptador1;
	ArrayList<String> datos;
	ArrayList<String> datos1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menuejercicios);
		
		
	    datos = new ArrayList<String>();
		llenarlista();
		listafuerza = (ListView) findViewById(R.id.listafuerza);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listafuerza.setAdapter(adaptador);
		
		final Intent irejercicio = new Intent(this, RealizarEjercicioActivity.class);
        	
		   Resources res = getResources();
	        
	        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
	        tabs.setup();
	        
	        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
	        spec.setContent(R.id.listafuerza);
	        spec.setIndicator("CARDIO", 
	        res.getDrawable(android.R.drawable.ic_dialog_alert));
	        tabs.addTab(spec);
	       
	        datos1 = new ArrayList<String>();
			listacardio = (ListView) findViewById(R.id.listacardio);
			adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos1);
			listacardio.setAdapter(adaptador1);
			llenarlistacardio();
			
			spec=tabs.newTabSpec("mitab2");
	        spec.setContent(R.id.listacardio);
	        spec.setIndicator("FUERZA", 
	        res.getDrawable(android.R.drawable.ic_dialog_info));
	        tabs.addTab(spec);
	        
	        tabs.setCurrentTab(0);
	        
	        tabs.setOnTabChangedListener(new OnTabChangeListener() {
				public void onTabChanged(String tabId) {
					Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
				}
			});
	        
	        //-----------------------ESTAN AL REVES Y NADIE SABE PORQUE -----------------------//
		listacardio.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long arg3) {
					String seleccionado = ((String) parent.getItemAtPosition(pos)).toString();
					irejercicio.putExtra("ejercicio1", seleccionado);
					//Toast.makeText(getApplication(),"ITEM "+seleccionado, Toast.LENGTH_LONG).show();
					startActivity(irejercicio);
			}					
		});		
		listafuerza.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long arg3) {
					String seleccionado = ((String) parent.getItemAtPosition(pos)).toString();
					//Toast.makeText(getApplication(),"ITEM "+seleccionado, Toast.LENGTH_LONG).show();
					irejercicio.putExtra("ejercicio1", seleccionado);
					startActivity(irejercicio);
			}					
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void llenarlista(){		
		List<Ejercicios> ejercicios_db;		
		menu_ejercicios = new DataSourceService(this);
		menu_ejercicios.open();
		ejercicios_db = menu_ejercicios.getEjercicios();
		menu_ejercicios.close();
		
		for (int i=0; i < ejercicios_db.size(); i++ ){
			if(ejercicios_db.get(i).gettipo().equals("cardio"))
				datos.add(ejercicios_db.get(i).ejercicio_name);
		}
		menu_ejercicios.close();
	}
	public void llenarlistacardio(){		
		List<Ejercicios> ejercicios_db;		
		menu_ejercicios = new DataSourceService(this);
		menu_ejercicios.open();
		ejercicios_db = menu_ejercicios.getEjercicios();
		menu_ejercicios.close();
		
		for (int i=0; i < ejercicios_db.size(); i++ ){
			if(ejercicios_db.get(i).gettipo().equals("fuerza"))
				datos1.add(ejercicios_db.get(i).ejercicio_name);
		}
		menu_ejercicios.close();
	}
	
	
}

