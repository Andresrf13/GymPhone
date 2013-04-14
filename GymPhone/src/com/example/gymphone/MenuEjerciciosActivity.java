package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
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

        	
		   Resources res = getResources();
	        
	        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
	        tabs.setup();
	        
	        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
	        spec.setContent(R.id.listafuerza);
	        spec.setIndicator("CARDIO", 
	        res.getDrawable(android.R.drawable.ic_btn_speak_now));
	        tabs.addTab(spec);
	       
	        datos1 = new ArrayList<String>();
			listacardio = (ListView) findViewById(R.id.listacardio);
			adaptador1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos1);
			listacardio.setAdapter(adaptador1);
			llenarlistacardio();
			
			spec=tabs.newTabSpec("mitab2");
	        spec.setContent(R.id.listacardio);
	        spec.setIndicator("FUERZA", 
	        res.getDrawable(android.R.drawable.ic_dialog_map));
	        tabs.addTab(spec);
	        
	        tabs.setCurrentTab(0);
	        
	        tabs.setOnTabChangedListener(new OnTabChangeListener() {
				public void onTabChanged(String tabId) {
					Log.i("AndroidTabsDemo", "Pulsada pestaña: " + tabId);
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
		/*datos.add("Banco");
		datos.add("Columna de Cable");
		datos.add("Doble polea");
		datos.add("Estante energia");
		datos.add("Pesas");*/
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

