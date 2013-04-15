package com.example.gymphone;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class RankingActivity extends Activity {
	private DataSourceService estadisticas_db;
	List<Lista_ejercicios> listaejercicios_db;
	Array listaordenar;
	GridView listamenu;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);
		
		datos = new ArrayList<String>();		
		listaejercicios_db = new ArrayList<Lista_ejercicios>();
		llenarlista();
		listamenu = (GridView) findViewById(R.id.gridPosiciones);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, datos);
		listamenu.setAdapter(adaptador);
		
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
					long arg3) {
				// Toast.makeText(getApplication(), "Clic en: "+(String)((TextView)vista).getText(),Toast.LENGTH_LONG ).show();				// 
				
			}
			
		});	
	}
	
	public void llenarlista(){		
		estadisticas_db = new DataSourceService(this);
		estadisticas_db.open();
		listaejercicios_db = estadisticas_db.getListaEjercicios();		
		Lista_ejercicios []listaordenar = new Lista_ejercicios[listaejercicios_db.size()];
		//Toast.makeText(getApplication(), "largo: "+listaordenar.length  , Toast.LENGTH_LONG ).show();
		estadisticas_db.close();
		
		if(listaejercicios_db.size() > 0){
			listaordenar[0] = listaejercicios_db.get(0);
			listaordenar[0].setcalorias(1);
			for(int x = 1; x<listaejercicios_db.size(); x++)
			{
				boolean esta = false;
				for(int i=0; i<listaordenar.length; i++)
				{
					if(listaordenar[i]!= null && listaordenar[i].getejercicio().equals(listaejercicios_db.get(x).getejercicio()))
					{
						listaordenar[i].setcalorias(listaordenar[i].getcalorias()+1);						
						esta=true;
						break;
					}					
				}
				if(esta == false)
				{
					listaordenar[x]= listaejercicios_db.get(x);
					listaordenar[x].setcalorias(1);
				}
			}		

			// ---------------------- CREAR ARREGLO NUEVO -------------------- //
			int d = 0, w=0;
			for (int g= 0; g<listaordenar.length; g++)
			{				
				if(listaordenar[g] != null){
					d++;
				}								
			}			
			Lista_ejercicios []listaordenar1 = new Lista_ejercicios[d];			
			for (int g= 0; g<listaordenar.length; g++)
			{
				if(listaordenar[g] != null){					
					listaordenar1[w]=listaordenar[g];
					w++;
					// Toast.makeText(getApplication(), "largo: "+listaordenar[w].getejercicio() , Toast.LENGTH_LONG ).show();
				}
			}
			//---------------------ORDENAMIENTO BURBUJA---------------------//
			Lista_ejercicios aux = new Lista_ejercicios();
			boolean cambios = false;
			while(true)
			{
				cambios=false;
				for (int j=1; j<listaordenar1.length; j++){
					if( listaordenar1[j].getcalorias() < listaordenar1[j-1].getcalorias() )
					{
						aux = listaordenar1[j];
						listaordenar1[j] = listaordenar1[j-1];
						listaordenar1[j-1] = aux;
						cambios=true;
					}
					
				}
				if(cambios ==false){
					break;
				}
			}		
			//---------------------------INSERTAR EN GRID----------------------------//
			int ident = 1;
			datos.add("#");
			datos.add("Ejercicio");
			datos.add("Veces Realizadas");
			for (int g = listaordenar1.length-1; g!=-1; g--)
			{			
				
				if(listaordenar1[g] != null){
					datos.add(ident+"");
					datos.add(" "+listaordenar1[g].getejercicio());
					datos.add(" "+listaordenar1[g].getcalorias()+"");
					ident++;
				}
				else{
					break;
				}
			}
			
		}
		
	}
}
