package com.example.gymphone;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class CrearRutinaActivity extends Activity implements OnItemSelectedListener{
	private DataSourceService crear_db;
	List<Lista_ejercicios>	newejercicios_Agregar;
	List<Rutinas>	rutinas_db;
	List<Lista_Rutinas> listarutinas_db;
	List<Ejercicios> ejercicios_db;
	ListView lista_add;
	ArrayList<String> ejercicios_array;
	ArrayList<String> lista_array;
	Spinner listajercicios;
	ArrayAdapter<String> adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crearrutina);
		
		crear_db = new DataSourceService(this);
		crear_db.open();
		rutinas_db = crear_db.getRutinas();
		listarutinas_db = crear_db.getListaRutinas();
		ejercicios_db = crear_db.getEjercicios();
		crear_db.close();
				
				
		ejercicios_array = new ArrayList<String>();
		lista_array = new ArrayList<String>();
		newejercicios_Agregar = new ArrayList<Lista_ejercicios>(); 
		llenarspinner();
		ArrayAdapter<String> adapter_crear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ejercicios_array);
		listajercicios = (Spinner) findViewById(R.id.ejercicios_spinner);
		listajercicios.setAdapter(adapter_crear);
		
		lista_add = (ListView) findViewById(R.id.lista_ejercicios1);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista_array);
		lista_add.setAdapter(adaptador);		
		
	}
	private void llenarspinner() {
		for (int i=0; i < ejercicios_db.size(); i++ ){			
			ejercicios_array.add(ejercicios_db.get(i).getejercicio_name());			
		}
		
	}

		//-------------------------AGREGAR UN EJERCICIO A LA LISTA ------------------//
	public void add_ejercicio(View view){		
		String seleccionado = (String) listajercicios.getSelectedItem();
		boolean esta= false;
		boolean esta1= false;
		if (0 != newejercicios_Agregar.size())
		{
			for(int r=0; r<newejercicios_Agregar.size(); r++){
				if(newejercicios_Agregar.get(r).getejercicio().equals(seleccionado)){
					esta = true;
					break;
				}
			}
		}
		else{
			Lista_ejercicios nuevo = new Lista_ejercicios();
			nuevo.setejercicio(seleccionado);
			nuevo.setcalorias(0);
			nuevo.settiempo(0);
			newejercicios_Agregar.add(nuevo);
			lista_array.add(seleccionado);
			lista_add.refreshDrawableState();
			Toast.makeText(getApplication(),"La rutina "+seleccionado+" Insertado", Toast.LENGTH_SHORT).show();
			esta1= true;
		}
		if(esta== true && esta1==false){
			Toast.makeText(getApplication(),"La rutina "+seleccionado+" ya esta agregada.", Toast.LENGTH_SHORT).show();
		}
		else{
			Lista_ejercicios nuevo = new Lista_ejercicios();
			nuevo.setejercicio(seleccionado);
			nuevo.setcalorias(0);
			nuevo.settiempo(0);
			newejercicios_Agregar.add(nuevo);
			lista_array.add(seleccionado);	
			lista_add.refreshDrawableState();
			Toast.makeText(getApplication(),"La rutina "+seleccionado+" Insertado", Toast.LENGTH_SHORT).show();
		}
		
	}
	//------------------------ CREAR RUTINA -------------------------------//
	public void crear_rutina(View view){
		EditText nombnew = (EditText)findViewById(R.id.newname_rutina);
		String nombre_nueva = nombnew.getText().toString();
		boolean esta = false;
		if(rutinas_db.size() > 0 && newejercicios_Agregar.size()>0)
		{
			for(int h = 0; h < rutinas_db.size(); h++){
				if(rutinas_db.get(h).getname().equals(nombre_nueva)){
					esta = true;
					break;
				}
			}
			if(esta== true){
				Toast.makeText(getApplication(),"La rutina "+nombre_nueva+" ya esta en la base de datos.", Toast.LENGTH_LONG).show();
			}
			else{
				crear_db = new DataSourceService(this);
				crear_db.open();
				int id_insert = crear_db.insertarrutina(nombre_nueva);
				int id_listarutina = crear_db.insertarlistarutina(id_insert, nombre_nueva);
				crear_db.close();
				//Toast.makeText(getApplication(),"ID "+id_lista, Toast.LENGTH_LONG).show();
				crear_db.open();
				for(int k =0; k<newejercicios_Agregar.size(); k++){
					crear_db.insertarejercicio(newejercicios_Agregar.get(k), id_listarutina);
				}
				crear_db.close();
				
				final Intent iratras = new Intent(this, TiposRutinasActivity.class);
				startActivity(iratras);
				finish();
			}
		}
		
	}
	// -----------------------METODOS DEL SPINNER--------------------------//
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
