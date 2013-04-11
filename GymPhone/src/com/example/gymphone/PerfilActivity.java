package com.example.gymphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerfilActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_perfil);
	}

	public void irMenu(View view)
	{
		Intent irmenu = new Intent(this, MenuActivity.class);
		startActivity(irmenu);
	}
}
