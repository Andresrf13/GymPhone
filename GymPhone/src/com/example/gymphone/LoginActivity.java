package com.example.gymphone;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private DataSourceService login_bd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void ir_Menu (View v)
	{		
		login_bd = new DataSourceService(this);
		login_bd.open();
		List<Usuarios> user_db = login_bd.getUsuarios();		
		login_bd.close();
		EditText user = (EditText)findViewById(R.id.username_space);
		EditText pass = (EditText)findViewById(R.id.password_space);		
		
		RijndaelCrypt enc = new RijndaelCrypt("GymPhone");
		
		
		String a3 = user.getText().toString();
		String a4 = pass.getText().toString();
		boolean x = false;
				
				
		for (int i=0; i < user_db.size(); i++ )
		{
			Usuarios usuario = new Usuarios();	
			usuario = user_db.get(i);
			String a1 = usuario.getnombre();
			String a2 = usuario.getcontrasena();
			String res = enc.decrypt(a2);						
			if((res.equals(a4))&&(a1.equals(a3)))
			{				
				Intent irmenu = new Intent (this, MenuActivity.class);
				irmenu.putExtra("pase1", a1);
				startActivity(irmenu);
				x=true;
				user.setText("");
				pass.setText("");
				break;			
			}			
		}
		if(x==false)
			Toast.makeText(getApplication(),"ERROR al iniciar Sesión", Toast.LENGTH_LONG).show();
	}

}