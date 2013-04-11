package com.example.gymphone;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuActivity extends Activity{
	ListView listamenu;
	ArrayAdapter<String> adaptador;
	ArrayList<String> datos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		datos = new ArrayList<String>();
		llenarlista();
		listamenu = (ListView) findViewById(R.id.listaMenu);
		adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listamenu.setAdapter(adaptador);	
		
		final Intent irperfil = new Intent(this, PerfilActivity.class);
		final Intent irrutina = new Intent(this, TiposRutinasActivity.class);
		final Intent irayuda = new Intent(this, AyudaActivity.class);
		final Intent irranking = new Intent(this, RankingActivity.class);
		final Intent iractualizar = new Intent(this, ActualizarActivity.class);
		listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View vista, int posicion,
					long arg3) {
				
				switch (posicion)
				{
				case 0:
				{					
					startActivity(irperfil);
					break;
				}
				case 2:
				{					
					startActivity(irrutina);
					break;
				}
				case 3:
				{					
					startActivity(irranking);
					break;
				}
				case 5:
				{					
					startActivity(iractualizar);
					break;
				}
				case 6:
				{					
					startActivity(irayuda);
					break;
				}
				}
				
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
		datos.add("Perfil");
		datos.add("Ejercicios");
		datos.add("Rutina");
		datos.add("Ranking");
		datos.add("Estadisticas");
		datos.add("Actualizar");
		datos.add("Ayuda");
	}
		
}
