package com.example.gymphone;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;



public class AyudaformatActivity extends Activity{
	private DataSourceService ayuda_db;
	TextView titulo_pagina;
	TextView get_instrucciones;
	String instrucciones;
	int opcion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ayudaformat);
		
		Bundle title = getIntent().getExtras();
		String titulo = title.getString("title");
		
		titulo_pagina = (TextView)findViewById(R.id.titulo_ayuda);
		titulo_pagina.setText(titulo);		
		ayuda_db = new DataSourceService(this);
		ayuda_db.open();
		instrucciones = ayuda_db.getAyuda(titulo);
		opcion = ayuda_db.getAyuda1(titulo); // -------------- para escoger la imagen para poner
		ayuda_db.close();			
		get_instrucciones = (TextView)findViewById(R.id.instrucciones_text);
		get_instrucciones.setText(instrucciones);
	}
	
	
}
