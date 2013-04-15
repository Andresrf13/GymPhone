package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class EstadisticasActivity extends Activity implements OnItemSelectedListener {
	private DataSourceService estadisticas_db;
	Spinner spinner_rutinas;
	Spinner spinner_ejercicios;
	String[] elementos = {"suave", "lunes", "martes"};
	ArrayList<String> rutina_spinner;
	ArrayList<String> ejercicios_spinner;
	List<Lista_Rutinas> listarutina_db;
	List<Lista_ejercicios> listaejercicios_db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estadisticas);
		
		rutina_spinner = new ArrayList<String>();
		llenarSpinner();
		
		ArrayAdapter<String> adapter_rutina = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, rutina_spinner);
		spinner_rutinas = (Spinner)findViewById(R.id.spinner1);
		spinner_rutinas.setAdapter(adapter_rutina);
		spinner_rutinas.setOnItemSelectedListener(this);
		
		Resources res = getResources();
        
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Rutina", 
        res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
				
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Ejercicio", 
        res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        
        tabs.setCurrentTab(0);
	}

	private void llenarSpinner() {
		// TODO Auto-generated method stub
		//List<Lista_Rutinas> listarutina_db;		
		estadisticas_db = new DataSourceService(this);
		estadisticas_db.open();
		listarutina_db = estadisticas_db.getListaRutinas();
		estadisticas_db.close();
		
		for (int i=0; i < listarutina_db.size(); i++ ){			
			rutina_spinner.add(listarutina_db.get(i).getname());
		}		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {		
		if(parent.getId()==R.id.spinner1)
		{
			String seleccionado = ((String) parent.getItemAtPosition(pos)).toString();
			configurar_pantalla(seleccionado);
		}
		if(parent.getId()==R.id.spinner2)
		{		
			if(listaejercicios_db.size()>0){
				TextView tiempo_estadistica = (TextView) findViewById(R.id.tiempo2_estadistica);
				tiempo_estadistica.setText("tiempo en ejercicio:  "+listaejercicios_db.get(pos).gettiempo()+"min");
				
				TextView calorias_estadistica = (TextView) findViewById(R.id.calorias2_estadistica);
				calorias_estadistica.setText("calorias quemadas:  "+listaejercicios_db.get(pos).getcalorias()+" calorias");				
			}
			else{
				TextView tiempo_estadistica = (TextView) findViewById(R.id.tiempo2_estadistica);
				tiempo_estadistica.setText("tiempo en ejercicio:  0"+"min");
				
				TextView calorias_estadistica = (TextView) findViewById(R.id.calorias2_estadistica);
				calorias_estadistica.setText("calorias quemadas:  0"+" calorias");
			}
			
		}
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	private void configurar_pantalla(String Rutina) {
		Lista_Rutinas rutina_selec = new Lista_Rutinas();
		for (int i=0; i < listarutina_db.size(); i++ ){
			if(listarutina_db.get(i).getname().equals(Rutina))
			{
				rutina_selec = listarutina_db.get(i);
				//Toast.makeText(getApplication(), listarutina_db.get(i).getname() +" "+ listarutina_db.get(i).getid() , Toast.LENGTH_LONG ).show();
				break;
			}
		}
		long x = rutina_selec.getid();		

		//Toast.makeText(getApplication(), "id_lista:"+x, Toast.LENGTH_LONG ).show();
		
		ejercicios_spinner = new ArrayList<String>();
		llenarSpinner2(x);
		if(listaejercicios_db.size()==0)
		{
			TextView tiempo_estadistica = (TextView) findViewById(R.id.tiempo2_estadistica);
			tiempo_estadistica.setText("tiempo en ejercicio:  0"+"min");
			
			TextView calorias_estadistica = (TextView) findViewById(R.id.calorias2_estadistica);
			calorias_estadistica.setText("calorias quemadas:  0"+" calorias");
		}
		
		ArrayAdapter<String> adapter_ejercicio = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ejercicios_spinner);
		spinner_ejercicios = (Spinner)findViewById(R.id.spinner2);
		spinner_ejercicios.setAdapter(adapter_ejercicio);
		spinner_ejercicios.setOnItemSelectedListener(this);
		
		setRutina();		
	}

	private void setRutina() {
		long calorias = 0;
		long tiempo = 0;
		long numero_ejercicios=listaejercicios_db.size();
		
		for (int i=0; i < listaejercicios_db.size(); i++ ){					
			calorias += listaejercicios_db.get(i).getcalorias();
			tiempo += listaejercicios_db.get(i).gettiempo();
		}
		TextView avance_estadistica = (TextView) findViewById(R.id.avance_estadistica);
		avance_estadistica.setText("Numero de ejercicios: "+numero_ejercicios+" ejercicios");
		
		TextView tiempo_estadistica = (TextView) findViewById(R.id.tiempo_estadistica);
		tiempo_estadistica.setText("tiempo total de rutina: "+tiempo+" min");
		
		TextView calorias_estadistica = (TextView) findViewById(R.id.calorias_estadistica);
		calorias_estadistica.setText("calorias quemadas: "+calorias+" calorias");		
		
	}

	private void llenarSpinner2(long x) { 
		estadisticas_db = new DataSourceService(this);
		estadisticas_db.open();
		listaejercicios_db = estadisticas_db.getListaEjercicios(x);
		estadisticas_db.close();		
		for (int i=0; i < listaejercicios_db.size(); i++ ){					
			ejercicios_spinner.add(listaejercicios_db.get(i).getejercicio());
		}		
	}
}

