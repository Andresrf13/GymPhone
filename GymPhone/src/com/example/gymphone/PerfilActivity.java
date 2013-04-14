package com.example.gymphone;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PerfilActivity extends Activity{
	private DataSourceService perfil_db;
	private Usuarios User;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
		
		User = new Usuarios();
		Bundle usuario = getIntent().getExtras();
		String usuario_name = usuario.getString("usuario1");
		perfil_db = new DataSourceService(this);
		perfil_db.open();
		List<Usuarios> user_db = perfil_db.getUsuarios();		
		perfil_db.close();
		
		set_ventana(user_db, usuario_name);		
	}

	

	private void set_ventana(List<Usuarios> user_db, String usuario_name) {
		// TODO Auto-generated method stub
		for (int i=0; i < user_db.size(); i++ )
		{
			Usuarios bus = new Usuarios();	
			bus = user_db.get(i);
			String text = bus.getnombre();
			//Toast.makeText(getApplication(),"db: "+text+" param: "+usuario_name, Toast.LENGTH_LONG).show();
			if(text.equals(usuario_name))
			{					
				//Toast.makeText(getApplication()," altura_db: "+bus.getaltura(), Toast.LENGTH_LONG).show();
				User = bus;
				break;
			}
		}
		
		EditText edad = (EditText)findViewById(R.id.edad_perfil);
		String edad_get = Long.toString(User.getedad());
		edad.setText(edad_get);
		EditText estatura = (EditText)findViewById(R.id.estura_perfil);
		String estatura_get = String.valueOf(User.getaltura());
		estatura.setText(estatura_get);
		EditText peso = (EditText)findViewById(R.id.peso_perfil);
		String peso_get = String.valueOf(User.getpeso());
		peso.setText(peso_get);
	}



	public void irMenu(View view)
	{		
		EditText edad = (EditText)findViewById(R.id.edad_perfil);
		EditText estatura = (EditText)findViewById(R.id.estura_perfil);
		EditText peso = (EditText)findViewById(R.id.peso_perfil);
		String edad1 = edad.getText().toString();
		String estatura1 = estatura.getText().toString();
		String peso1 = peso.getText().toString();
		String nomb = User.getnombre();
		
		//perfil_db = new DataSourceService(this);
		//perfil_db.open();
		int funciono= 0;
		funciono = perfil_db.update_perfil(nomb, edad1, estatura1, peso1);	
		//perfil_db.close();		
		if(funciono != 0){
			Intent irmenu = new Intent(this, MenuActivity.class);
			irmenu.putExtra("pase1", nomb);
			startActivity(irmenu);
		}
		else
		{						
			Toast.makeText(getApplication(),"error al actualizar", Toast.LENGTH_LONG).show();
		}
		
		
		
	}
}
