package com.crono.cronometro;


import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity {

	Chronometer cronometro;
	Button BotonIniciar;
	Button BotonDetener;
	Long memoCronometro;
	String estado="inactivo";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "old_stamper.ttf");
		
		cronometro = (Chronometer)findViewById(R.id.chronometro);
		cronometro.setTypeface(font);
		BotonDetener = (Button)findViewById(R.id.btn_detener);
		BotonIniciar = (Button)findViewById(R.id.btn_iniciar);
		
		BotonIniciar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (estado == "inactivo"){
					cronometro.setBase(SystemClock.elapsedRealtime());
					cronometro.start();
					estado = "activo";
					BotonIniciar.setText("Pausado");
					return;
				}
				if (estado == "activo"){
					
					memoCronometro = SystemClock.elapsedRealtime();
					cronometro.stop();
					estado = "pausado";
					BotonDetener.setText("Continuar");
					return;
				}
				else{
					cronometro.setBase(cronometro.getBase() + SystemClock.elapsedRealtime() - 
					memoCronometro);
					cronometro.start();
					estado = "activo";
					BotonIniciar.setText("Pausar");
				}
			}
		});		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
