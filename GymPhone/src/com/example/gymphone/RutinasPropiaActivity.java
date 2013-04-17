package com.example.gymphone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RutinasPropiaActivity  extends Activity
{
	private DataSourceService rutina_sistema;
	ListView listamenu;
	ListView listarutina;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	private DataSourceService rutina_propia;
	int idRutinaSelec;
	List<Lista_ejercicios> listaEjercicios;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rutinas_creadas);
		
		datos = new ArrayList<String>();
		llenarlista();
		listamenu = (ListView) findViewById(R.id.listaRutinaPropia);		
		
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listamenu.setAdapter(adaptador);
		rutina_propia = new DataSourceService(this);			
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){
	
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View vista, int pos,
					long arg3) {
				String seleccionado = ((String) parent.getItemAtPosition(pos)).toString();
				//Toast.makeText(getApplication(), "Clic en: "+seleccionado, Toast.LENGTH_LONG ).show();
				
				rutina_propia.open();
				idRutinaSelec = rutina_propia.getidRutina(seleccionado);
				//Toast.makeText(getApplication(), "Clic en: "+idRutinaSelec, Toast.LENGTH_LONG ).show();
				listaEjercicios = rutina_propia.getListaEjercicios(idRutinaSelec);
				rutina_propia.close();				
				//Toast.makeText(getApplication(), "Clic en: "+idRutinaSelec, Toast.LENGTH_LONG ).show();
				//Toast.makeText(getApplication(), "Clic en: "+listaEjercicios.get(0).getejercicio(), Toast.LENGTH_LONG ).show();
								
				pasarInfo(idRutinaSelec);								
			}
			
		});	
	}
	
	public void llenarlista(){
		List<Rutinas> rutina_db;		
		DataSourceService rutina_sistema = new DataSourceService(this);
		rutina_sistema.open();
		rutina_db = rutina_sistema.getRutinas();
		rutina_sistema.close();
		
		for (int i=0; i < rutina_db.size(); i++ ){
			if(rutina_db.get(i).getpertenece().equals("propia"))
				datos.add(rutina_db.get(i).rutina_name);
		}
		rutina_sistema.close();		
	}
	
	public void ircrearRutina(View view){
		Intent ircrear = new Intent(this, CrearRutinaActivity.class);
		startActivity(ircrear);
		finish();
	}
	
	public void pasarInfo(int rutina_id){					 
		 Intent irRealizarRutina = new Intent(this, RealizarRutinaActivity.class);
		 irRealizarRutina.putExtra("pos", 0);
		 irRealizarRutina.putExtra("rutina_id", rutina_id);
		 startActivity(irRealizarRutina);
		
	}
}
