package com.example.practica_sl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Handler_sqlite help = new Handler_sqlite(this);
		TextView text = (TextView) findViewById(R.id.text);
		help.abrir();
		//help.insertarReg("Larisa", "larica");
		String x[] = help.leer();
        text.setText(x[1]);
		help.cerrar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
