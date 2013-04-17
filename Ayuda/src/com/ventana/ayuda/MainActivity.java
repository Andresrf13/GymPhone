package com.ventana.ayuda;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button siguiente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		siguiente= (Button)findViewById(R.id.imagensiguiente);				
		Toast.makeText(getApplication(),"Prueba!!!!!! ", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
		
	}
	
	public void irsiguiente(View v)
	{
		Toast.makeText(getApplication(),"Adelante!!!!!! ", Toast.LENGTH_SHORT).show();	
	}

	public void iratras(View v)
	{
		Toast.makeText(getApplication(),"Atras!!!!!! ", Toast.LENGTH_SHORT).show();	
	}
}
